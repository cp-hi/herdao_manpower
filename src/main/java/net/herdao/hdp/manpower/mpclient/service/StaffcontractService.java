

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffContract.StaffcontractDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;

import java.util.List;

/**
 * 员工合同签订
 *
 * @author liang
 * @date 2020-09-27 09:15:28
 */
public interface StaffcontractService extends HdpService<Staffcontract> {
    /**
     * 员工合同签订分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    Page<StaffcontractDTO> findStaffContractPage(Page<StaffcontractDTO> page, String searchText);

    /**
     * 员工合同签订
     * @param searchText
     * @return
     */
    List<StaffcontractDTO> findStaffContract(String searchText);
}
