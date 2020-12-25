package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveTypeConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffTransferApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.StaffBasicVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferPage;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 4:18 下午
 */
@Service
public class StaffTransferServiceImpl extends ServiceImpl<StaffTransferApproveMapper, StaffTransferApprove> implements StaffTransferService {

    @Autowired
    private UserpostService userPostService;
    @Autowired
    private OrganizationService orgService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostOrgService postOrgService;
    @Autowired
    private JobLevelService jobLevelService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private GroupService groupService;

    @Autowired
    private StaffTransferApproveMapper mapper;

    public StaffTransferServiceImpl(UserpostService userPostService) {
        this.userPostService = userPostService;
    }

    /**
     * TODO:: 维护岗位组织关系id
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public Long saveInfo(SaveStaffTransferInfoDTO dto) throws Exception {
//        dtoValidityCheck(null, dto);

        StaffTransferApprove entity = initStaffTransferData(dto);

        mapper.insert(entity);
        return entity.getId();
    }

    private StaffTransferApprove initStaffTransferData(SaveStaffTransferInfoDTO dto) throws Exception {
        StaffTransferApprove entity = new StaffTransferApprove();
        BeanUtils.copyProperties(dto, entity);

        // 为了适配前端
        // 根据 post_org_id(与页面上岗位组件传入的"岗位"对应) 获取到 post_id 保存
        // 从语义上来讲，post_org_id 才是员工就职的岗位 id，而 post_id 是标准岗位 id，需要注意
//        PostOrg nowPostOrg = postOrgService.getById(dto.getNowPostOrgId());
//        PostOrg transPostOrg = postOrgService.getById(dto.getTransPostOrgId());
//
//        if (nowPostOrg == null) {
//            throw new Exception("无法找到原岗位信息");
//        }
//        if (transPostOrg == null) {
//            throw new Exception("无法找到调动后的应岗位");
//        }
//        entity.setNowPostId(nowPostOrg.getPostId());
//        entity.setTransPostId(transPostOrg.getPostId());
        entity.setTransStartDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getTransStartDate()));
        entity.setTransferType(StaffChangesApproveTypeConstants.TRANSFER);
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        entity.setDelFlag(false);
        return entity;
    }

    private void dtoValidityCheck(Long id, SaveStaffTransferInfoDTO dto) throws Exception {
        if (id != null) {
            Userpost userpost = userPostService.getById(id);
            if (userpost == null) {
               throw new Exception("该员工的职位信息有误，请再次确认");
            } else {
                if (!dto.getNowOrgId().equals(userpost.getOrgId())) {
                    throw new Exception("原部门信息有误，请再次确认");
                }
                if (!dto.getNowPostOrgId().equals(userpost.getPostOrgId())) {
                    throw new Exception("原岗位信息有误，请再次确认");
                }
            }
        }

        // 校验部门有效性
        orgService.validityCheck(dto.getNowOrgId(), "原部门信息有误，请再次确认");
        orgService.validityCheck(dto.getTransOrgId(), "调动后部门信息有误，请再次确认");

        // 校验岗位有效性
        postOrgService.validityCheck(dto.getNowPostOrgId(), "原岗位信息有误，请再次确认");
        postOrgService.validityCheck(dto.getTransPostOrgId(), "调动后岗位信息有误，请再次确认");

        // 校验职级有效性
        jobLevelService.validityCheck(dto.getNowJobLevelId(), "原职级信息有误，请再次确认");
        jobLevelService.validityCheck(dto.getTransJobLevelId(), "调动后职级信息有误，请再次确认");

        if (dto.getFundUnitsId() != null) {
            companyService.validityCheck(dto.getFundUnitsId(), "公积金缴纳单位信息有误，请再次确认");
        }

        if (dto.getPaidUnitsId() != null) {
            companyService.validityCheck(dto.getPaidUnitsId(), "工资发放单位信息有误，请再次确认");
        }

        if (dto.getSecurityUnitsId() != null) {
            companyService.validityCheck(dto.getSecurityUnitsId(), "社保发放单位信息有误，请再次确认");
        }

    }

    @Override
    public StaffTransferInfoVO getDetail(Long id) throws Exception {
        StaffTransferApprove staffTransferApprove = mapper.selectById(id);
        if (staffTransferApprove != null) {
            return staffChangesConvert2StaffTransferInfoVo(staffTransferApprove);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateInfo(Long id, SaveStaffTransferInfoDTO dto) throws Exception {
        // 对入参的数据有效性校验
//        dtoValidityCheck(id, dto);

        // 对记录进行状态和类型有效性判断
        QueryWrapper<StaffTransferApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN)
                .eq("transfer_type", StaffChangesApproveTypeConstants.TRANSFER);
        StaffTransferApprove entity = mapper.selectOne(queryWrapper);
        if (entity == null) {
            throw new Exception("该记录不可更新");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setTransStartDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getTransStartDate()));
        mapper.updateById(entity);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long affirmStart(Long id, SaveStaffTransferInfoDTO dto) throws Exception {
        // 编辑未保存直接"发起申请"
        if (id != null) {
            updateInfo(id, dto);
        }
        // 新建数据未保存直接"发起申请"
        else {
            id = saveInfo(dto);
        }
        // 获取已保存数据更新状态为：填报中
        return affirm(id);
    }

    @Override
    @Transactional
    public Long affirm(Long id) throws Exception {
        QueryWrapper<StaffTransferApprove> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffTransferApprove entity = mapper.selectOne(wrapper);
        if(entity == null) {
           throw new Exception("该人事调动审批记录已发起审批，请勿重复操作");
        }
        entity.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        mapper.updateById(entity);
        return id;
    }

    @Override
    public Page<StaffTransferPageVO> pageTransfer(Page page, String searchText, Long orgId, String status) {

        Page<StaffTransferPage> staffTransferPage = this.baseMapper.findStaffTransferPage(page, searchText, orgId, status);
        return convert2PageVO(staffTransferPage);
    }

    /**
     * 适配数据库中获取的原始数据为传给前端的 vo
     *
     * @param page
     * @return
     */
    private Page<StaffTransferPageVO> convert2PageVO(Page<StaffTransferPage> page) {
        Page<StaffTransferPageVO> pageVO = new Page<>();
        List<StaffTransferPageVO> list = new ArrayList<>();
        for (StaffTransferPage record : page.getRecords()) {
            StaffTransferPageVO vo = new StaffTransferPageVO();
            BeanUtils.copyProperties(record, vo);
            if (record.getTransStartDate() != null) {
                vo.setTransStartDate(LocalDateTimeUtils.convert2Long(record.getTransStartDate()));
            }
            vo.setUpdateInfo(MessageFormat.format("{0} 于 {1} 更新", record.getModifierName(), record.getModifierTime()));
            list.add(vo);
        }
        BeanUtils.copyProperties(page, pageVO);
        pageVO.setRecords(list);
        return pageVO;
    }

    private StaffTransferInfoVO staffChangesConvert2StaffTransferInfoVo(StaffTransferApprove from) throws Exception {
        StaffTransferInfoVO to = new StaffTransferInfoVO();
        BeanUtils.copyProperties(from, to);

        to.setTransStartDate(LocalDateTimeUtils.convert2Long(from.getTransStartDate()));

        Group group = groupService.getGroupByOrgId(from.getNowOrgId());
        if (group != null) {
            to.setGroupId(group.getId());
        }

        PostOrg nowPostOrg = postOrgService.getById(from.getNowPostOrgId());
        if (nowPostOrg != null) {
            to.setNowPostOrgName(nowPostOrg.getPostName());
        }

        PostOrg transPostOrg = postOrgService.getById(from.getTransPostOrgId());
        if (transPostOrg != null) {
            to.setTransPostOrgName(transPostOrg.getPostName());
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
            to.setTransJobLevelName(transJobLevel.getJobLevelName());
        }

        Company paidUnit = companyService.getById(from.getPaidUnitsId());
        if (paidUnit != null) {
            to.setPayUnitsName(paidUnit.getCompanyName());
        }

        Company fundUnit = companyService.getById(from.getFundUnitsId());
        if (fundUnit != null) {
            to.setFundUnitsName(fundUnit.getCompanyName());
        }

        Company securityUnit = companyService.getById(from.getSecurityUnitsId());
        if (securityUnit != null) {
            to.setSecurityUnitsName(securityUnit.getCompanyName());
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
