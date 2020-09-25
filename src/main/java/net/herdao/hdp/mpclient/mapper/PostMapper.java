package net.herdao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.mpclient.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    List<Map> postList();

    Boolean chkDuplicatePostName(Post post);

    Boolean chkDuplicatePostCode(Post post);

    Integer getPostStaffCount(String condition);

    List<Map<String, Integer>> getPostStaffAges(Long postId);
}
