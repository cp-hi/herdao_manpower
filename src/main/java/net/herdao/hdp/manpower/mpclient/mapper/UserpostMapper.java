

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author andy
 * @date 2020-09-15 08:57:53
 */
@Mapper
public interface UserpostMapper extends BaseMapper<Userpost> {

    /**
     * 查询用户岗位
     * @param condition
     * @return
     */
    List<Userpost> findUserPost(Userpost condition);

    /**
     * 现任职情况分页
     * @param page 分页对象
     * @param searchText
      * @return
     */
    Page<UserpostDTO> findUserPostNowPage(Page<UserpostDTO> page, @Param("searchText") String searchText);

    /**
     * 现任职情况
     * @param searchText
     * @return
     */
    List<UserpostDTO> findUserPostNow(@Param("searchText") String searchText);

    /**
     * 现任职情况
     * @param staffid
     * @return
     */
    UserpostDTO findCurrentJob(Long staffid);
}
