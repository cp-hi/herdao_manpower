

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffcontractDTO;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 员工合同签订
 *
 * @author liang
 * @date 2020-09-27 09:15:28
 */
public interface StaffcontractService extends EntityService<Staffcontract> {
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
