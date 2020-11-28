package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesStatusConstants;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesType;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallInDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallOutDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffChangesMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.in.StaffCallInInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.RollbackException;
import java.sql.Date;
import java.time.*;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/27 3:36 下午
 */
@Service
public class StaffCallInServiceImpl extends ServiceImpl<StaffChangesMapper, StaffChanges> implements StaffCallInService {

    @Autowired
    private StaffCallOutService staffCallOutService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private UserpostService userPostService;
    @Autowired
    private OrganizationService orgService;
    @Autowired
    private PostService postService;
    @Autowired
    private JobLevelService jobLevelService;
    @Autowired
    private CompanyService companyService;

    @Autowired
    private StaffChangesMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long affirmStart(Long id, SaveStaffCallInDTO dto) throws Exception {
        if (id != null) {
            updateInfo(id, dto);
        } else {
            id = saveInfo(dto);
        }
        StaffChanges changes = mapper.selectById(id);
        changes.setStatus(StaffChangesStatusConstants.APPROVING);
        mapper.updateById(changes);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateInfo(Long id, SaveStaffCallInDTO dto) throws Exception {
//        dtoValidityCheck(id, dto);
        QueryWrapper<StaffChanges> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesStatusConstants.FILLING_IN)
                .eq("transfer_type", StaffChangesType.CALL_IN_AND_CALL_OUT);
        StaffChanges staffChanges = mapper.selectOne(queryWrapper);

        QueryWrapper<StaffChanges> callInQueryWrapper = new QueryWrapper<>();
        if (staffChanges == null) {
            throw new Exception("该记录不可更改");
        }
        BeanUtils.copyProperties(dto, staffChanges);
        mapper.updateById(staffChanges);
        return id;
    }

    private void dtoValidityCheck(Long id, SaveStaffCallInDTO dto) throws Exception {
        if (id != null) {
            Userpost userpost = userPostService.getById(id);
            if (userpost == null) {
                throw new Exception("该员工的职位信息有误，请再次确认");
            } else {
                if (!dto.getNowOrgId().equals(userpost.getOrgId())) {
                    throw new Exception("原部门信息有误，请再次确认");
                }
                if (!dto.getNowPostId().equals(userpost.getPostId())) {
                    throw new Exception("原岗位信息有误，请再次确认");
                }
            }
        }

        // 校验部门有效性
        orgService.validityCheck(dto.getNowOrgId(), "原部门信息有误，请再次确认");
        orgService.validityCheck(dto.getTransOrgId(), "调动后部门信息有误，请再次确认");

        // 校验岗位有效性
        postService.validityCheck(dto.getNowPostId(), "原岗位信息有误，请再次确认");
        postService.validityCheck(dto.getTransPostId(), "调动后岗位信息有误，请再次确认");

        // 校验职级有效性
        jobLevelService.validityCheck(dto.getNowJobLevelId(), "原职级信息有误，请再次确认");
        jobLevelService.validityCheck(dto.getTransJobLevelId(), "调动后职级信息有误，请再次确认");

        if (dto.getFundUnitsId() != null) {
            companyService.validityCheck(dto.getFundUnitsId(), "公积金缴纳单位信息有误，请再次确认");
        }

        if (dto.getPaidUnitsId() != null) {
            companyService.validityCheck(dto.getPaidUnitsId(), "工资发放单位信息有误，请再次确认");
        }

        if (dto.getSecurityUnitId() != null) {
            companyService.validityCheck(dto.getSecurityUnitId(), "社保发放单位信息有误，请再次确认");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveInfo(SaveStaffCallInDTO dto) throws Exception {
//        dtoValidityCheck(null, dto);

        StaffChanges staffChanges = new StaffChanges();
        BeanUtils.copyProperties(dto, staffChanges);
        staffChanges.setTransferType(StaffChangesType.CALL_IN_AND_CALL_OUT);
        staffChanges.setStatus(StaffChangesStatusConstants.FILLING_IN);
        staffChanges.setDelFlag(false);
        mapper.insert(staffChanges);

        // 都造 "调出" 对象
        SaveStaffCallOutDTO outDTO = new SaveStaffCallOutDTO();
        BeanUtils.copyProperties(dto, outDTO);
        // 调出生效时间比调入时间提前一天
        LocalDateTime transStartDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dto.getTransStartDate()), ZoneId.systemDefault());
        LocalDateTime callOutDate = transStartDate.plusDays(-1L);
        outDTO.setTransStartDate(callOutDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        outDTO.setTransStartDate(dto.getTransStartDate());
        // 调出记录要保存对应的调入记录 id
        outDTO.setTransApproveId(staffChanges.getId());
        staffCallOutService.saveInfo(outDTO);

        return staffChanges.getId();
    }

    @Override
    public StaffCallInInfoVO getDetail(Long id) {
        StaffChanges staffChanges = mapper.selectById(id);
        if (staffChanges != null) {
            return staffChangesConvert2StaffTransferInfoVo(staffChanges);
        }
        return null;
    }

    private StaffCallInInfoVO staffChangesConvert2StaffTransferInfoVo(StaffChanges from) {
        StaffCallInInfoVO to = new StaffCallInInfoVO();
        BeanUtils.copyProperties(from, to);

        Post nowPost = postService.getById(to.getNowPostId());
        if (nowPost != null) {
            to.setNowPostName(nowPost.getPostName());
        }

        Post transPost = postService.getById(to.getTransPostId());
        if (transPost != null) {
            to.setTransPostName(transPost.getPostName());
        }

        Organization nowOrg = orgService.getById(to.getNowOrgId());
        if (nowOrg != null) {
            to.setNowOrgName(nowOrg.getOrgName());
        }

        Organization transOrg = orgService.getById(to.getTransOrgId());
        if (transOrg != null) {
            to.setTransOrgName(transOrg.getOrgName());
        }

        JobLevel nowJobLevel = jobLevelService.getById(to.getNowJobLevelId());
        if (nowJobLevel != null) {
            to.setNowJobLevelName(nowJobLevel.getJobLevelName());
        }

        JobLevel transJobLevel = jobLevelService.getById(to.getTransJobLevelId());
        if (transJobLevel != null) {
            to.setTransJobLevelName(transJobLevel.getJobLevelName() );
        }

        Company paidUnits = companyService.getById(to.getPaidUnitsId());
        if (paidUnits != null) {
            to.setPaidUnitsName(paidUnits.getCompanyName());
        }

        Company fundUnits = companyService.getById(to.getFundUnitsId());
        if (fundUnits != null) {
            to.setFundUnitsName(fundUnits.getCompanyName());
        }

        Company securityUnits = companyService.getById(to.getSecurityUnitsId());
        if (securityUnits != null) {
            to.setSecurityUnitsName(securityUnits.getCompanyName());
        }
        return to;
    }


    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
