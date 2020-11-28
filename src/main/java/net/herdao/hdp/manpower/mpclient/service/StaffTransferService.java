package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffTransferApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferPageVO;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 4:16 下午
 */
public interface StaffTransferService extends HdpService<StaffTransferApprove> {

    public Long saveInfo(SaveStaffTransferInfoDTO dto) throws Exception;

    public StaffTransferInfoVO getDetail(Long id);

    public Long updateInfo(Long id, SaveStaffTransferInfoDTO dto) throws Exception;

    public Long affirmStart(Long id, SaveStaffTransferInfoDTO dto) throws Exception;

    public Page<StaffTransferPageVO> pageTransfer(Page page, String searchText, Long orgId, String status);
}
