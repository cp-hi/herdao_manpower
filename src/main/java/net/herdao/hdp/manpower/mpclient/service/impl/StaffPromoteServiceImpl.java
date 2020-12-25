package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SavaStaffPromoteDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffPromoteApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.StaffBasicVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromoteInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromotePage;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromotePageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/28 5:59 下午
 */
@Service
public class StaffPromoteServiceImpl extends ServiceImpl<StaffPromoteApproveMapper, StaffPromoteApprove> implements StaffPromoteService {

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
    private StaffService staffService;
    @Autowired
    private GroupService groupService;

    @Autowired
    private StaffPromoteApproveMapper mapper;

    @Override
    @Transactional
    public Long affirmStart(Long id, SavaStaffPromoteDTO dto) throws Exception {
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
        QueryWrapper<StaffPromoteApprove> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffPromoteApprove entity = mapper.selectOne(wrapper);
        if(entity == null) {
            throw new Exception("该晋升/降级审批记录已发起审批，请勿重复操作");
        }
        entity.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        mapper.updateById(entity);
        return id;
    }

    @Override
    public Long updateInfo(Long id, SavaStaffPromoteDTO dto) throws Exception {
//        dtoValidityCheck(id, dto);
        QueryWrapper<StaffPromoteApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffPromoteApprove entity = mapper.selectOne(queryWrapper);
        if (entity == null) {
            throw new Exception("该记录不可更新");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setPromoteDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getPromoteDate()));
        mapper.updateById(entity);
        return id;
    }

    @Override
    public Long saveInfo(SavaStaffPromoteDTO dto) throws Exception {
        // dtoValidityCheck(null, dto);

        StaffPromoteApprove entity = initStaffPromoteData(dto);

        mapper.insert(entity);
        return entity.getId();
    }

    private StaffPromoteApprove initStaffPromoteData(SavaStaffPromoteDTO dto) {
        StaffPromoteApprove entity = new StaffPromoteApprove();
        BeanUtils.copyProperties(dto, entity);

        // 前端应传 postOrgId，但现在组件传值为 postId，为了调通，需要先注释掉
        // 根据 post_org_id(与页面上岗位组件传入的"岗位"对应) 获取到 post_id 保存
        // 从语义上来讲，post_org_id 才是员工就职的岗位 id，而 post_id 是标准岗位 id，需要注意
//        PostOrg nowPostOrg = postOrgService.getById(dto.getNowPostOrgId());
//        PostOrg promotePostOrg = postOrgService.getById(dto.getPromotePostOrgId());

//        if (nowPostOrg == null) {
//            throw new Exception("无法找到原岗位信息");
//        }
//        if (transPostOrg == null) {
//            throw new Exception("无法找到调动后的应岗位");
//        }
//        entity.setNowPostId(nowPostOrg.getPostId());
//        entity.setPromotePostId(promotePostOrg.getPostId());
        entity.setPromoteDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getPromoteDate()));
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        entity.setDelFlag(false);
        return entity;
    }


    private void dtoValidityCheck(Long id, SavaStaffPromoteDTO dto) throws Exception {
        if (id != null) {
            Userpost userpost = userPostService.getById(id);
            if (userpost == null) {
                throw new Exception("该员工的职位信息有误，请再次确认");
            } else {
                if (!dto.getNowOrgId().equals(userpost.getOrgId())) {
                    throw new Exception("原部门信息有误，请再次确认");
                }
                if (!dto.getNowPostOrgId().equals(userpost.getPostId())) {
                    throw new Exception("原岗位信息有误，请再次确认");
                }
            }
        }

        // 校验部门有效性
        orgService.validityCheck(dto.getNowOrgId(), "原部门信息有误，请再次确认");
        orgService.validityCheck(dto.getPromoteOrgId(), "调动后部门信息有误，请再次确认");

        // 校验岗位有效性
        postOrgService.validityCheck(dto.getNowPostOrgId(), "原岗位信息有误，请再次确认");
        postOrgService.validityCheck(dto.getPromotePostOrgId(), "调动后岗位信息有误，请再次确认");

        // 校验职级有效性
        jobLevelService.validityCheck(dto.getNowJobLevelId(), "原职级信息有误，请再次确认");
        jobLevelService.validityCheck(dto.getPromoteJobLevelId(), "调动后职级信息有误，请再次确认");
    }


    @Override
    public StaffPromoteInfoVO getDetail(Long id) throws Exception {
        StaffPromoteApprove promoteApprove = mapper.selectById(id);
        if (promoteApprove != null) {
            return StaffPromoteApprove2StaffPromoteInfoVO(promoteApprove);
        }
        return null;
    }

    private StaffPromoteInfoVO StaffPromoteApprove2StaffPromoteInfoVO(StaffPromoteApprove from) throws Exception {
        StaffPromoteInfoVO to = new StaffPromoteInfoVO();
        BeanUtils.copyProperties(from, to);

        to.setPromoteDate(LocalDateTimeUtils.convert2Long(from.getPromoteDate()));

        Group group = groupService.getGroupByOrgId(from.getNowOrgId());
        if (group != null) {
            to.setGroupId(group.getId());
        }

        PostOrg nowPostOrg = postOrgService.getById(from.getNowPostOrgId());
        if (nowPostOrg != null) {
            to.setNowPostOrgName(nowPostOrg.getPostName());
        }

        PostOrg promotePostOrg = postOrgService.getById(from.getPromotePostOrgId());
        if (promotePostOrg != null) {
            to.setPromotePostOrgName(promotePostOrg.getPostName());
        }

        Organization nowOrg = orgService.getById(from.getNowOrgId());
        if (nowOrg != null) {
            to.setNowOrgName(nowOrg.getOrgName());
        }

        Organization promoteOrg = orgService.getById(from.getPromoteOrgId());
        if (promoteOrg != null) {
            to.setPromoteOrgName(promoteOrg.getOrgName());
        }

        JobLevel nowJobLevel = jobLevelService.getById(from.getNowJobLevelId());
        if (nowJobLevel != null) {
            to.setNowJobLevelName(nowJobLevel.getJobLevelName());
        }

        JobLevel promoteJobLevel = jobLevelService.getById(from.getPromoteJobLevelId());
        if (promoteJobLevel != null) {
          to.setPromoteJobLevelName(promoteJobLevel.getJobLevelName());
        }

        StaffBasicVO staffBasicVO = staffService.selectBasicByUserId(from.getUserId());
        if (staffBasicVO != null) {
            BeanUtils.copyProperties(staffBasicVO, to);
        }

        return to;
    }


    @Override
    public Page<StaffPromotePageVO> pageStaffPromote(Page page, String searchText, Long orgId, String status) {
        Page<StaffPromotePage> promotePage = mapper.findStaffPromotePage(page, searchText, orgId, status);
        return convert2PageVO(promotePage);
    }

    private Page<StaffPromotePageVO> convert2PageVO(Page<StaffPromotePage> page) {
        Page<StaffPromotePageVO> pageVO = new Page<>();
        List<StaffPromotePageVO> list = new ArrayList<>();
        for (StaffPromotePage record : page.getRecords()) {
            StaffPromotePageVO vo = new StaffPromotePageVO();
            BeanUtils.copyProperties(record, vo);
            if (record.getPromoteStartDate() != null) {
                vo.setPromoteStartDate(LocalDateTimeUtils.convert2Long(record.getPromoteStartDate()));
            }
            vo.setUpdateInfo(MessageFormat.format("{0} 于 {1} 更新", record.getModifierName(), record.getModifierTime()));
            list.add(vo);
        }
        BeanUtils.copyProperties(page, pageVO);
        pageVO.setRecords(list);
        return pageVO;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
