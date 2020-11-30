package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SavaStaffPromoteDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPromoteApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.in.StaffCallInInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromoteInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromotePageVO;

/**
 * @Author Liu Chang
 * @Date 2020/11/28 5:54 下午
 */
public interface StaffPromoteService extends HdpService<StaffPromoteApprove>{
    public Long affirmStart(Long id, SavaStaffPromoteDTO dto) throws Exception;

    public Long updateInfo(Long id, SavaStaffPromoteDTO dto) throws Exception;

    public Long saveInfo(SavaStaffPromoteDTO dto) throws Exception;

    public StaffPromoteInfoVO getDetail(Long id);

    public Page<StaffPromotePageVO> pageStaffPromote(Page page, String searchText, Long orgId, String status);
}
