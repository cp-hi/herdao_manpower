package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallOutDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffChanges;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.out.StaffCallOutInfoVO;

/**
 * @Author Liu Chang
 * @Date 2020/11/27 4:34 下午
 */
public interface StaffCallOutService extends HdpService<StaffChanges> {
    public Long saveInfo(SaveStaffCallOutDTO dto) throws Exception;

    public Long updateInfo(Long id, SaveStaffCallOutDTO dto) throws Exception;

    public Long affirmStart(Long id, SaveStaffCallOutDTO dto) throws Exception;

    public StaffCallOutInfoVO getDetail(Long id);
}
