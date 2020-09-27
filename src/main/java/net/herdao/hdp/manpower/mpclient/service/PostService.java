package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.PostDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.PostStaffDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface PostService extends IService<Post> {

    /**
     * 岗位列表
     *
     * @return
     */
    List<Map> postList(Long groupId);

    /**
     * 分页数据
     *
     * @param page
     * @param params
     * @return
     */
    Page page(Page page, Map<String, String> params);

    /**
     * 保存岗位
     *
     * @param post
     * @return
     */
    @Override
    boolean saveOrUpdate(Post post);

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
