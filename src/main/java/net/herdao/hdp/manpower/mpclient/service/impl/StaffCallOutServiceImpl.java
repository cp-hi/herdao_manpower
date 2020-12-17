package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveTypeConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallOutDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffTransferApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.StaffBasicVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.out.StaffCallOutInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Liu Chang
 * @Date 2020/11/27 4:41 下午
 */
@Service
public class StaffCallOutServiceImpl extends ServiceImpl<StaffTransferApproveMapper, StaffTransferApprove> implements StaffCallOutService {
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
    private StaffService staffService;

    @Autowired
    private StaffTransferApproveMapper mapper;

    @Override
    public Long saveInfo(SaveStaffCallOutDTO dto) throws Exception {
//        dtoValidityCheck(null, dto);
        StaffTransferApprove entity = new StaffTransferApprove();
        BeanUtils.copyProperties(dto, entity);

        entity.setTransStartDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getTransStartDate()));
        entity.setTransferType(StaffChangesApproveTypeConstants.CALL_OUT);
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        entity.setDelFlag(false);
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public Long updateInfo(Long id, SaveStaffCallOutDTO dto) throws Exception {
//        dtoValidityCheck(id, dto);
        QueryWrapper<StaffTransferApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffTransferApprove entity = mapper.selectOne(queryWrapper);
        if (entity == null) {
            throw new Exception("该记录不可更改");
        }

        BeanUtils.copyProperties(dto, entity);
        entity.setTransStartDate(LocalDateTimeUtils.convert2LocalDateTime(dto.getTransStartDate()));
        mapper.updateById(entity);
        return id;
    }

    @Override
    public Long affirmStart(Long id, SaveStaffCallOutDTO dto) throws Exception {
        if (id != null) {
            updateInfo(id, dto);
        } else {
            id = saveInfo(dto);
        }
        return callInAndCallOutService.affirm(id);
    }

    @Override
    public StaffCallOutInfoVO getDetail(Long id) throws Exception {
        StaffTransferApprove staffTransferApprove = mapper.selectById(id);
        if (staffTransferApprove != null) {
            return staffChangesConvert2StaffCallOutInfoVo(staffTransferApprove);
        }
        return null;
    }

    private StaffCallOutInfoVO staffChangesConvert2StaffCallOutInfoVo(StaffTransferApprove from) throws Exception {
        StaffCallOutInfoVO to = new StaffCallOutInfoVO();
        BeanUtils.copyProperties(from, to);

        to.setTransStartDate(LocalDateTimeUtils.convert2Long(from.getTransStartDate()));
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

        StaffBasicVO staffBasicVO = staffService.selectBasicByUserId(from.getUserId());
        BeanUtils.copyProperties(staffBasicVO, to);

        return to;
    }

    private void dtoValidityCheck(Long id, SaveStaffCallOutDTO dto) throws Exception {
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

    }


    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
