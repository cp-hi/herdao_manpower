package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SavaStaffPromoteDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPromoteApprove;
import net.herdao.hdp.manpower.mpclient.entity.StaffTransferApprove;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import net.herdao.hdp.manpower.mpclient.mapper.StaffPromoteApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.in.StaffCallInInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromoteInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromotePageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private JobLevelService jobLevelService;

    @Autowired
    private StaffPromoteApproveMapper mapper;

    @Override
    public Long affirmStart(Long id, SavaStaffPromoteDTO dto) throws Exception {
        // 确保在没有保存数据，直接发起申请时数据正确
        if (id != null) {
            updateInfo(id, dto);
        } else {
            id = saveInfo(dto);
        }
        StaffPromoteApprove changes = mapper.selectById(id);
        // 更新状态为：填报中
        changes.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        mapper.updateById(changes);
        return id;
    }

    @Override
    public Long updateInfo(Long id, SavaStaffPromoteDTO dto) throws Exception {
//        dtoValidityCheck(id, dto);
        QueryWrapper<StaffPromoteApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("status", StaffChangesApproveStatusConstants.FILLING_IN);
        StaffPromoteApprove promoteApprove = mapper.selectOne(queryWrapper);
        if (promoteApprove == null) {
            throw new Exception("该记录不可更新");
        }
        BeanUtils.copyProperties(dto, promoteApprove);
        mapper.updateById(promoteApprove);
        return id;
    }

    @Override
    public Long saveInfo(SavaStaffPromoteDTO dto) throws Exception {
        // dtoValidityCheck(null, dto);
        StaffPromoteApprove promoteApprove = new StaffPromoteApprove();
        BeanUtils.copyProperties(dto, promoteApprove);
        promoteApprove.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        promoteApprove.setDelFlag(false);
        mapper.insert(promoteApprove);
        return promoteApprove.getId();
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
                if (!dto.getNowPostId().equals(userpost.getPostId())) {
                    throw new Exception("原岗位信息有误，请再次确认");
                }
            }
        }

        // 校验部门有效性
        orgService.validityCheck(dto.getNowOrgId(), "原部门信息有误，请再次确认");
        orgService.validityCheck(dto.getPromoteOrgId(), "调动后部门信息有误，请再次确认");

        // 校验岗位有效性
        postService.validityCheck(dto.getNowPostId(), "原岗位信息有误，请再次确认");
        postService.validityCheck(dto.getPromotePostId(), "调动后岗位信息有误，请再次确认");

        // 校验职级有效性
        jobLevelService.validityCheck(dto.getNowJobLevelId(), "原职级信息有误，请再次确认");
        jobLevelService.validityCheck(dto.getPromoteJobLevelId(), "调动后职级信息有误，请再次确认");
    }


    @Override
    public StaffPromoteInfoVO getDetail(Long id) {
        StaffPromoteApprove promoteApprove = mapper.selectById(id);
        if (promoteApprove != null) {
            StaffPromoteInfoVO vo = new StaffPromoteInfoVO();
            BeanUtils.copyProperties(promoteApprove, vo);
            return vo;
        }
        return null;
    }


    @Override
    public Page<StaffPromotePageVO> pageStaffPromote(Page page, String searchText, Long orgId, String status) {
        return mapper.findStaffPromotePage(page, searchText, orgId, status);
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
