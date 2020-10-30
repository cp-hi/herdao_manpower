

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftransactionDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrans.StafftransDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;

import java.util.List;

/**
 * 异动情况
 *
 * @author andy
 * @date 2020-09-24 16:00:18
 */
public interface StafftransactionService extends HdpService<Stafftransaction> {
    /**
     * 员工异动情况分页
     * @param page 分页对象
     * @param searchText 关键字搜索
     * @param staffId 员工ID
     * @return
     */
    Page<StafftransDTO> findStaffTransPage(Page<StafftransDTO> page, String searchText,String staffId);

    /**
     * 员工异动情况分页
     * @param searchText 关键字搜索
     * @param staffId 员工ID
     * @return
     */
    List<StafftransDTO> findStaffTrans(String searchText,String staffId);

    /**
     * @author lift
     * 员工详情-员工异动情况DTO
     * @param staffid
     * @return
     */
    List<StafftransactionDTO> findStafftransactionDto(Long staffid);


}
