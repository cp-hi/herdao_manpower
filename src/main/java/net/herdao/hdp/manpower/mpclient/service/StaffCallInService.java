package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallInDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffTransferApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.in.StaffCallInInfoVO;

/**
 * @Author Liu Chang
 * @Date 2020/11/27 3:30 下午
 */
public interface StaffCallInService extends HdpService<StaffTransferApprove> {
    
    public Long affirmStart(Long id, SaveStaffCallInDTO dto) throws Exception;

    public Long updateInfo(Long id, SaveStaffCallInDTO dto) throws Exception;

    public Long saveInfo(SaveStaffCallInDTO dto) throws Exception;

    public StaffCallInInfoVO getDetail(Long id);
}
