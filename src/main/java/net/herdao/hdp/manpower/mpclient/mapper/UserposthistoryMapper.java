
package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffUserpost.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    List<UserpostDTO> findUserPostHistoryPage(Map<String, Object> map);

    /**
     * 历史任职情况分页
     * @param searchText
     * @return
     */
    List<UserpostDTO> findUserPostHistory(@Param("query") UserpostDTO userpostDTO,@Param("searchText") String searchText);

}
