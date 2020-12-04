package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffLeavePostDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffLeavePostApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostPageVO;

/**
 * @Author Liu Chang
 * @Date 2020/12/3 3:48 下午
 */
public interface StaffLeaveService extends HdpService<StaffLeavePostApprove> {
    Page<StaffLeavePostPageVO> pageStaffLeave(Page page, String searchText, Long orgId, String status);

    Long affirmStart(Long id, SaveStaffTransferInfoDTO dto);

    Long updateStaffLeave(Long id, SaveStaffLeavePostDTO dto);

    StaffLeavePostInfoVO getStaffLeave(Long id);

    Long insert(SaveStaffLeavePostDTO dto);
}
