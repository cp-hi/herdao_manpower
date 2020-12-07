package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.entity.StaffTransferApprove;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.StaffCallInAndCallOutPageVO;

/**
 * @Author Liu Chang
 * @Date 2020/11/27 2:36 下午
 */

public interface StaffCallInAndCallOutService extends HdpService<StaffTransferApprove> {
    Long affirmStart(Long id) throws Exception;

    Page<StaffCallInAndCallOutPageVO> pageStaffCallInAndCallOut(Page page, String searchText, Long orgId, String status, String type);
}
