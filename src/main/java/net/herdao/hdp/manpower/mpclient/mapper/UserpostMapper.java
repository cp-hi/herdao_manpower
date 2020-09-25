

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import org.apache.ibatis.annotations.Mapper;

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

}
