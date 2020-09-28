

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工岗位历史表
 *
 * @author yangrr
 * @date 2020-09-25 17:24:25
 */
public interface UserposthistoryService extends IService<Userposthistory> {
    /**
     * 历史任职情况分页
     * @param page 分页对象
     * @param orgId
     * @param staffName
     * @param staffCode
     * @return
     */
    Page<UserpostDTO> findUserPostHistoryPage(Page<UserpostDTO> page, @Param("orgId") String orgId, @Param("staffName") String staffName, @Param("staffCode") String staffCode);

    /**
     * 新增员工历史任职情况
     * @param entity
     * @return
     */
    Boolean saveHistory(Userposthistory entity);

    /**
     * 更新员工历史任职情况
     * @param entity
     * @return
     */
    Boolean updateHistory(Userposthistory entity);

    /**
     * 历史任职情况分页
     * @param orgId
     * @param staffName
     * @param staffCode
     * @return
     */
    List<UserpostDTO> findUserPostHistory( @Param("orgId") String orgId, @Param("staffName") String staffName, @Param("staffCode") String staffCode);


}
