package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostStaffDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    List<Map> postList(Long groupId);

    IPage<PostDTO> page(Page<PostDTO> page, @Param("searchTxt") String searchTxt);

    Boolean chkDuplicatePostName(Post post);

    Boolean chkDuplicatePostCode(Post post);

    Integer getPostStaffCount(String condition);

    List<Map<String, BigDecimal>> getPostStaffAges(Long postId);

    List<PostDetailDTO> getPostDetails(@Param("postId") Long postId, @Param("limit") String limit);

    List<PostStaffDTO> getPostStaffs(@Param("postId") Long postId, @Param("limit") String limit);

}
