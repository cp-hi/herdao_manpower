package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.vo.post.PostDetailVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostListVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostStaffVO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.sys.annotation.IgnoreLogic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
@IgnoreLogic
public interface PostMapper extends EntityMapper<Post> {

    List<Map> postList(Long groupId);

    IPage<PostListVO> page(Page<PostListVO> page, @Param("post") Post post);

    Boolean chkDuplicatePostName(Post post);

    Boolean chkDuplicatePostCode(Post post);

    Integer getPostStaffCount(String condition);

    List<Map<String, BigDecimal>> getPostStaffAges(Long postId);

    List<PostDetailVO> getPostDetails(@Param("postId") Long postId, @Param("limit") String limit);

    List<PostStaffVO> getPostStaffs(@Param("postId") Long postId, @Param("limit") String limit);

}
