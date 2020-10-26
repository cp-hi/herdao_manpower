package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.dto.post.PostDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostStaffDTO;
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
    List<PostDetailDTO> getPostDetails(Long postId, String operation, String size);

    /**
     * 岗位员工
     *
     * @return
     */
    List<PostStaffDTO> getPostStaffs(Long postId, String operation, String size);

}
