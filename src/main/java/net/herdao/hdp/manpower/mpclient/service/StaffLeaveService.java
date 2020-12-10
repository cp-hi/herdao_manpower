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
    Page<StaffLeavePostPageVO> pageStaffLeavePost(Page page, String searchText, Long orgId, String status);

    Long affirmStart(Long id, SaveStaffLeavePostDTO dto) throws Exception;

    Long affirm(Long id) throws Exception;

    Long updateStaffLeave(Long id, SaveStaffLeavePostDTO dto) throws Exception;

    StaffLeavePostInfoVO getStaffLeave(Long id);

    Long insert(SaveStaffLeavePostDTO dto);
}
