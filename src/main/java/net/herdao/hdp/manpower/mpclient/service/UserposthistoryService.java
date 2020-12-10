

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffUserpost.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工岗位历史表
 *
 * @author yangrr
 * @date 2020-09-25 17:24:25
 */
public interface UserposthistoryService extends HdpService<Userposthistory> {
    /**
     * 历史任职情况分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    IPage findUserPostHistoryPage(Page<UserpostDTO> page,UserpostDTO userpostDTO, String searchText);

    /**
     * 历史任职情况分页
     * @param searchText
     * @return
     */
    List<UserpostDTO> findUserPostHistory(UserpostDTO userpostDTO,  String searchText);


}
