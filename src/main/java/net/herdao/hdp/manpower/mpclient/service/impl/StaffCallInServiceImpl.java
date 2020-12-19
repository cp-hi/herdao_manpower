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
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.StaffBasicVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.in.StaffCallInInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferInfoVO;
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
    private StaffCallInAndCallOutService callInAndCallOutService;
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
    private StaffService staffService;

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
        return callInAndCallOutService.affirm(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateInfo(Long id, SaveStaffCallInDTO dto) throws Exception {
//        dtoValidityCheck(id, dto);
        QueryWrapper<StaffTransferApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffTransferApprove entity = mapper.selectOne(queryWrapper);

        if (entity == null) {
            throw new Exception("该记录不可更改");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setTransJobLevelId(dto.getTransJobLevel());
        entity.setFundUnitsId(dto.getFundUnit());
        entity.setPaidUnitsId(dto.getPaidUnit());
        entity.setSecurityUnitsId(dto.getSecurityUnit());
        entity.setTransStartDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getTransStartDate()));
        mapper.updateById(entity);
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
        jobLevelService.validityCheck(dto.getTransJobLevel(), "调动后职级信息有误，请再次确认");

        if (dto.getFundUnit() != null) {
            companyService.validityCheck(dto.getFundUnit(), "公积金缴纳单位信息有误，请再次确认");
        }

        if (dto.getPaidUnit() != null) {
            companyService.validityCheck(dto.getPaidUnit(), "工资发放单位信息有误，请再次确认");
        }

        if (dto.getSecurityUnit() != null) {
            companyService.validityCheck(dto.getSecurityUnit(), "社保发放单位信息有误，请再次确认");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveInfo(SaveStaffCallInDTO dto) throws Exception {
//        dtoValidityCheck(null, dto);

        StaffTransferApprove entity = new StaffTransferApprove();
        BeanUtils.copyProperties(dto, entity);
        entity.setTransJobLevelId(dto.getTransJobLevel());
        entity.setFundUnitsId(dto.getFundUnit());
        entity.setPaidUnitsId(dto.getPaidUnit());
        entity.setSecurityUnitsId(dto.getSecurityUnit());
        LocalDateTime transStartDate = LocalDateTimeUtils.convert2LocalDateTime(dto.getTransStartDate());
        entity.setTransStartDate(transStartDate);
        entity.setTransferType(StaffChangesApproveTypeConstants.CALL_IN);
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        entity.setDelFlag(false);
        mapper.insert(entity);

        // 插入一条对应的调出数据
        staffCallOutService.saveInfo(getOutDTO(entity));

        return entity.getId();
    }

    private SaveStaffCallOutDTO getOutDTO(StaffTransferApprove callInEntity) {
        SaveStaffCallOutDTO outDTO = new SaveStaffCallOutDTO();
        BeanUtils.copyProperties(callInEntity, outDTO);

        // 调出生效时间比调入时间提前一天
        LocalDateTime callOutDate = callInEntity.getTransStartDate().plusDays(-1L);
        outDTO.setTransStartDate(LocalDateTimeUtils.convert2Long(callOutDate));

        // 调出记录要保存对应的调入记录 id
        outDTO.setTransApproveId(callInEntity.getId());
        return outDTO;
    }

    @Override
    public StaffCallInInfoVO getDetail(Long id) throws Exception {
        StaffTransferApprove staffTransferApprove = mapper.selectById(id);
        if (staffTransferApprove != null) {
            return staffChangesConvert2StaffTransferInfoVo(staffTransferApprove);
        }
        return null;
    }

    private StaffCallInInfoVO staffChangesConvert2StaffTransferInfoVo(StaffTransferApprove from) throws Exception {
        StaffCallInInfoVO to = new StaffCallInInfoVO();
        BeanUtils.copyProperties(from, to);

        to.setTransStartDate(LocalDateTimeUtils.convert2Long(from.getTransStartDate()));
        Post nowPost = postService.getById(from.getNowPostId());
        if (nowPost != null) {
            to.setNowPostName(nowPost.getPostName());
        }

        Post transPost = postService.getById(from.getTransPostId());
        if (transPost != null) {
            to.setTransPostName(transPost.getPostName());
        }

        Organization nowOrg = orgService.getById(from.getNowOrgId());
        if (nowOrg != null) {
            to.setNowOrgName(nowOrg.getOrgName());
        }

        Organization transOrg = orgService.getById(from.getTransOrgId());
        if (transOrg != null) {
            to.setTransOrgName(transOrg.getOrgName());
        }

        JobLevel nowJobLevel = jobLevelService.getById(from.getNowJobLevelId());
        if (nowJobLevel != null) {
            to.setNowJobLevelName(nowJobLevel.getJobLevelName());
        }

        JobLevel transJobLevel = jobLevelService.getById(from.getTransJobLevelId());
        if (transJobLevel != null) {
            StaffCallInInfoVO.Dictionary dict = new StaffCallInInfoVO.Dictionary();
            dict.setLabel(transJobLevel.getJobLevelName());
            dict.setValue(transJobLevel.getId());
            to.setTransJobLevel(dict);
        }

        Company paidUnits = companyService.getById(from.getPaidUnitsId());
        if (paidUnits != null) {
            StaffTransferInfoVO.Dictionary payUnit = new StaffTransferInfoVO.Dictionary();
            payUnit.setLabel(paidUnits.getCompanyName());
            payUnit.setValue(paidUnits.getId());
            to.setPayUnit(payUnit);
        }

        Company fundUnits = companyService.getById(from.getFundUnitsId());
        if (fundUnits != null) {
            StaffTransferInfoVO.Dictionary fundUnit = new StaffTransferInfoVO.Dictionary();
            fundUnit.setLabel(fundUnits.getCompanyName());
            fundUnit.setValue(fundUnits.getId());
            to.setFundUnit(fundUnit);
        }

        Company securityUnits = companyService.getById(from.getSecurityUnitsId());
        if (securityUnits != null) {
            StaffTransferInfoVO.Dictionary securityUnit = new StaffTransferInfoVO.Dictionary();
            securityUnit.setValue(securityUnits.getId());
            securityUnit.setLabel(securityUnits.getCompanyName());
            to.setSecurityUnit(securityUnit);
        }

        StaffBasicVO staffBasicVO = staffService.selectBasicByUserId(from.getUserId());
        if (staffBasicVO != null) {
            BeanUtils.copyProperties(staffBasicVO, to);
        }
        return to;
    }


    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
