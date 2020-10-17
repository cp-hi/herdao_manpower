
package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工岗位历史表
 *
 * @author yangrr
 * @date 2020-09-25 17:24:25
 */
@Mapper
public interface UserposthistoryMapper extends BaseMapper<Userposthistory> {
    /**
     * 历史任职情况分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    Page<UserpostDTO> findUserPostHistoryPage(Page<UserpostDTO> page, @Param("searchText") String searchText);

    /**
     * 历史任职情况分页
     * @param searchText
     * @return
     */
    List<UserpostDTO> findUserPostHistory(@Param("searchText") String searchText);

}
