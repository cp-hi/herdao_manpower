package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffChanges;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferPageVO;

import java.nio.file.Path;

/**
 * @Author Liu Chang
 * @Date 2020/11/25 4:16 下午
 */
public interface StaffTransferService extends HdpService<StaffChanges> {

    public Long save(SaveStaffTransferInfoDTO dto) throws Exception;

    public StaffTransferInfoVO getDetail(Long id);

    public Long updateInfo(Long id, SaveStaffTransferInfoDTO dto) throws Exception;

    public Long affirmStart(Long id, SaveStaffTransferInfoDTO dto) throws Exception;

    public Page<StaffTransferPageVO> pageTransfer(Page page, String searchText, Long orgId, String status);
}
