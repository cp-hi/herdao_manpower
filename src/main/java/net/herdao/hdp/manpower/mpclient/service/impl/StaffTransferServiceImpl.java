package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesStatusConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffChangesMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 4:18 下午
 */
@Service
public class StaffTransferServiceImpl extends ServiceImpl<StaffChangesMapper, StaffChanges> implements StaffTransferService {

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

    /**
     * TODO:: 维护岗位组织关系id
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public Long save(SaveStaffTransferInfoDTO dto) throws Exception {
//        dtoValidityCheck(null, dto);

        StaffChanges staffChanges = new StaffChanges();
        BeanUtils.copyProperties(dto, staffChanges);
        staffChanges.setStatus(StaffChangesStatusConstants.FILLING_IN);
        staffChanges.setDelFlag(false);

        mapper.insert(staffChanges);
        return staffChanges.getId();
    }

    private void dtoValidityCheck(Long id,SaveStaffTransferInfoDTO dto) throws Exception {
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
    public StaffTransferInfoVO getDetail(Long id) {
        StaffChanges staffChanges = mapper.selectById(id);
        return staffChangesConvert2StaffTransferInfoVo(staffChanges);
    }

    @Override
    public Long updateInfo(Long id, SaveStaffTransferInfoDTO dto) throws Exception {
//        dtoValidityCheck(id, dto);
        StaffChanges staffChanges = mapper.selectById(id);
        if (staffChanges.getStatus().equals(StaffChangesStatusConstants.FILLING_IN)) {
            BeanUtils.copyProperties(dto, staffChanges);
            mapper.updateById(staffChanges);
            return id;
        } else {
            throw new Exception("该记录已发起审批，不可更新");
        }

    }

    @Override
    public Long affirmStart(Long id, SaveStaffTransferInfoDTO dto) throws Exception {
        if (id != null) {
            updateInfo(id, dto);
        } else {
           id = save(dto);
        }
        StaffChanges changes = mapper.selectById(id);
        changes.setStatus(StaffChangesStatusConstants.APPROVING);
        mapper.updateById(changes);
        return id;
    }

    @Override
    public Page<StaffTransferPageVO> pageTransfer(Page page, String searchText, Long orgId, String status) {

        return this.baseMapper.findStaffTransferPage(page, searchText, orgId, status);
    }

    private StaffTransferInfoVO staffChangesConvert2StaffTransferInfoVo(StaffChanges from) {
        StaffTransferInfoVO to = new StaffTransferInfoVO();
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
