package com.hedao.hdp.mpclient.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hedao.hdp.mpclient.oa.entity.Post;

import java.util.List;
import java.util.Map;

public interface PostService extends IService<Post> {
    List<Map> postList();
    Page page (Page page,Map<String, String> params);
    void addOrUpdate(Post post)throws Exception;
}
