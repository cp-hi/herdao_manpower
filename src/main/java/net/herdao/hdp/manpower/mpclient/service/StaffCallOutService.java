package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallOutDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffTransferApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.out.StaffCallOutInfoVO;

/**
 * @Author Liu Chang
 * @Date 2020/11/27 4:34 下午
 */
public interface StaffCallOutService extends HdpService<StaffTransferApprove> {
    Long saveInfo(SaveStaffCallOutDTO dto) throws Exception;

    Long updateInfo(Long id, SaveStaffCallOutDTO dto) throws Exception;

    Long affirmStart(Long id, SaveStaffCallOutDTO dto) throws Exception;

    StaffCallOutInfoVO getDetail(Long id);
}
