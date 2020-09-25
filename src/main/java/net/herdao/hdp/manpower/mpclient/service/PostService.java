package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.Post;

import java.util.List;
import java.util.Map;

public interface PostService extends IService<Post> {
    List<Map> postList();
    Page page (Page page,Map<String, String> params);
    @Override
    boolean saveOrUpdate(Post post);

    Map getPostStaffInfo(Long postId);

}
