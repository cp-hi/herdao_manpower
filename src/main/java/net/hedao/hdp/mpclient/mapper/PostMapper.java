package net.hedao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.hedao.hdp.mpclient.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper extends BaseMapper<Post> {
   List<Map> postList();

    Boolean chkDuplicatePostName(Post post);

    Boolean chkDuplicatePostCode(Post post);
}