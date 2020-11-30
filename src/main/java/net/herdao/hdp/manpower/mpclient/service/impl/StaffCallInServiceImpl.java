package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveTypeConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallInDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallOutDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffTransferApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.in.StaffCallInInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/27 3:36 下午
 */
@Service
public class StaffCallInServiceImpl extends ServiceImpl<StaffTransferApproveMapper, StaffTransferApprove> implements StaffCallInService {

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
    private StaffTransferApproveMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long affirmStart(Long id, SaveStaffCallInDTO dto) throws Exception {
        if (id != null) {
            updateInfo(id, dto);
        } else {
            id = saveInfo(dto);
        }
        StaffTransferApprove changes = mapper.selectById(id);
        changes.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        mapper.updateById(changes);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateInfo(Long id, SaveStaffCallInDTO dto) throws Exception {
//        dtoValidityCheck(id, dto);
        QueryWrapper<StaffTransferApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN)
                .eq("transfer_type", StaffChangesApproveTypeConstants.CALL_IN_AND_CALL_OUT);
        StaffTransferApprove staffTransferApprove = mapper.selectOne(queryWrapper);

        QueryWrapper<StaffTransferApprove> callInQueryWrapper = new QueryWrapper<>();
        if (staffTransferApprove == null) {
            throw new Exception("该记录不可更改");
        }
        BeanUtils.copyProperties(dto, staffTransferApprove);
        mapper.updateById(staffTransferApprove);
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

        StaffTransferApprove staffTransferApprove = new StaffTransferApprove();
        BeanUtils.copyProperties(dto, staffTransferApprove);
        staffTransferApprove.setTransferType(StaffChangesApproveTypeConstants.CALL_IN_AND_CALL_OUT);
        staffTransferApprove.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        staffTransferApprove.setDelFlag(false);
        mapper.insert(staffTransferApprove);

        // 都造 "调出" 对象
        SaveStaffCallOutDTO outDTO = new SaveStaffCallOutDTO();
        BeanUtils.copyProperties(dto, outDTO);
        // 调出生效时间比调入时间提前一天
        LocalDateTime transStartDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dto.getTransStartDate()), ZoneId.systemDefault());
        LocalDateTime callOutDate = transStartDate.plusDays(-1L);
        outDTO.setTransStartDate(callOutDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        outDTO.setTransStartDate(dto.getTransStartDate());
        // 调出记录要保存对应的调入记录 id
        outDTO.setTransApproveId(staffTransferApprove.getId());
        staffCallOutService.saveInfo(outDTO);

        return staffTransferApprove.getId();
    }

    @Override
    public StaffCallInInfoVO getDetail(Long id) {
        StaffTransferApprove staffTransferApprove = mapper.selectById(id);
        if (staffTransferApprove != null) {
            return staffChangesConvert2StaffTransferInfoVo(staffTransferApprove);
        }
        return null;
    }

    private StaffCallInInfoVO staffChangesConvert2StaffTransferInfoVo(StaffTransferApprove from) {
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
