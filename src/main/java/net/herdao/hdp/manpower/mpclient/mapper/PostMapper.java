package net.herdao.hdp.manpower.mpclient.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;

import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.vo.post.PostListVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostStaffVO;

@Mapper
public interface PostMapper extends EntityMapper<Post> {
	
	/**
	 * 分页查询
	 */
	IPage<PostListVO> page(IPage page, @Param("t") Post t);

    List<PostDTO> postList(Long groupId);

    Integer getPostStaffCount(String condition);

    List<Map<String, BigDecimal>> getPostStaffAges(Long postId);

    List<PostDTO> getPostDetails(@Param("postId") Long postId, @Param("limit") String limit);

    List<PostStaffVO> getPostStaffs(@Param("postId") Long postId, @Param("limit") String limit);

    /**
     * 校验重复名称
     * @param post
     * @return
     */
//    @Override
//    Boolean checkDuplicateName(@Param("post")Post post);
}
