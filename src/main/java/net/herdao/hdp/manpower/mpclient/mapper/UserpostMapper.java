

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffUserpost.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    List<UserpostDTO> findUserPostNowPage(Map<String, Object> map);

    /**
     * 现任职情况
     * @param searchText
     * @return
     */
    List<UserpostDTO> findUserPostNow(@Param("query") UserpostDTO userpostDTO, @Param("searchText") String searchText);

    /**
     * 现任职情况
     * @param staffid
     * @return
     */
    UserpostDTO findCurrentJob(Long staffid);
    
    /**
     * 是否存在用户任职
     * @param org_id
     * @return
     */
    Boolean hasUserPost(Long org_id);
}
