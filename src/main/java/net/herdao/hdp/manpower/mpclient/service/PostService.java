package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.vo.post.PostDetailVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostStaffVO;
import net.herdao.hdp.manpower.mpclient.entity.Post;

import java.util.List;
import java.util.Map;

public interface PostService extends  EntityService<Post> {

    /**
     * 岗位列表
     *
     * @return
     */
    List<Map> postList(Long groupId);


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
    List<PostDetailVO> getPostDetails(Long postId, String operation, String size);

    /**
     * 岗位员工
     *
     * @return
     */
    List<PostStaffVO> getPostStaffs(Long postId, String operation, String size);

}
