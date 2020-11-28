package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesStatusConstants;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesType;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallOutDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.StaffTransferApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
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
    private UserpostService userPostService;
    @Autowired
    private OrganizationService orgService;
    @Autowired
    private PostService postService;
    @Autowired
    private JobLevelService jobLevelService;

    @Autowired
    private StaffTransferApproveMapper mapper;

    @Override
    public Long saveInfo(SaveStaffCallOutDTO dto) throws Exception {
//        dtoValidityCheck(null, dto);
        StaffTransferApprove staffTransferApprove = new StaffTransferApprove();
        BeanUtils.copyProperties(dto, staffTransferApprove);
        staffTransferApprove.setTransferType(StaffChangesType.CALL_IN_AND_CALL_OUT);
        staffTransferApprove.setStatus(StaffChangesStatusConstants.FILLING_IN);
        staffTransferApprove.setDelFlag(false);
        mapper.insert(staffTransferApprove);
        return staffTransferApprove.getId();
    }

    @Override
    public Long updateInfo(Long id, SaveStaffCallOutDTO dto) throws Exception {
//        dtoValidityCheck(id, dto);
        StaffTransferApprove staffTransferApprove = mapper.selectById(id);
        if (staffTransferApprove != null) {
            if (!staffTransferApprove.getStatus().equals(StaffChangesStatusConstants.FILLING_IN)) {
                throw new Exception("该记录已发起审批，不可更新");
            }
            if (staffTransferApprove.getTransferType() != StaffChangesType.CALL_IN_AND_CALL_OUT) {
                throw new Exception("该记录不是调入/调出类型，请再次确认更新信息");
            }

            BeanUtils.copyProperties(dto, staffTransferApprove);
            mapper.updateById(staffTransferApprove);
            return id;
        }
        return null;
    }

    @Override
    public Long affirmStart(Long id, SaveStaffCallOutDTO dto) throws Exception {
        if (id != null) {
            updateInfo(id, dto);
        } else {
            id = saveInfo(dto);
        }
        StaffTransferApprove changes = mapper.selectById(id);
        changes.setStatus(StaffChangesStatusConstants.APPROVING);
        mapper.updateById(changes);
        return id;
    }

    @Override
    public StaffCallOutInfoVO getDetail(Long id) {
        StaffTransferApprove staffTransferApprove = mapper.selectById(id);
        if (staffTransferApprove != null) {
            return staffChangesConvert2StaffCallOutInfoVo(staffTransferApprove);
        }
        return null;
    }

    private StaffCallOutInfoVO staffChangesConvert2StaffCallOutInfoVo(StaffTransferApprove from) {
        StaffCallOutInfoVO to = new StaffCallOutInfoVO();
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
