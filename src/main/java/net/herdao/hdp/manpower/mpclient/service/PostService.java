package net.herdao.hdp.manpower.mpclient.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.vo.post.PostListVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostStaffVO;

public interface PostService extends  EntityService<Post> {
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param post
	 * @return
	 */
	IPage<PostListVO> page(Page page, Post post);
    /**
     * 岗位列表
     *
     * @return
     */
    List<PostDTO> postList(Long groupId);


    /**
     * 岗位员工信息
     *
     * @param postId
     * @return
     */
    Map getPostStaffInfo(Long postId);

    /**
     * 岗位明细
     *
     * @return
     */
    List<PostDTO> getPostDetails(Long postId, String operation, String size);

    /**
     * 岗位员工
     *
     * @return
     */
    List<PostStaffVO> getPostStaffs(Long postId, String operation, String size);

    void validityCheck(Long id, String msg) throws Exception;

}
