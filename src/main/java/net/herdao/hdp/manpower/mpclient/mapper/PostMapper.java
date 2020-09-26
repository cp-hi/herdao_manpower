package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    List<Map> postList();

    Boolean chkDuplicatePostName(Post post);

    Boolean chkDuplicatePostCode(Post post);

    Integer getPostStaffCount(String condition);

    List<Map<String, BigDecimal>> getPostStaffAges(Long postId);

    List<Map<String, String>> getPostDetails(String limit);

    List<Map<String, String>> getPostStaffs(String limit);

}
