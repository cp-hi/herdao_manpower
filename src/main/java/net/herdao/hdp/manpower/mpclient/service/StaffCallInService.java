package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallInDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffTransferApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.in.StaffCallInInfoVO;

/**
 * @Author Liu Chang
 * @Date 2020/11/27 3:30 下午
 */
public interface StaffCallInService extends HdpService<StaffTransferApprove> {
    
    Long affirmStart(Long id, SaveStaffCallInDTO dto) throws Exception;

    Long updateInfo(Long id, SaveStaffCallInDTO dto) throws Exception;

    Long saveInfo(SaveStaffCallInDTO dto) throws Exception;

    StaffCallInInfoVO getDetail(Long id) throws Exception;
}
