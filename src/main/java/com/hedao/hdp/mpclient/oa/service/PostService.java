package com.hedao.hdp.mpclient.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hedao.hdp.mpclient.oa.entity.Post;

import java.util.List;

public interface PostService extends IService<Post> {
    void addOrUpdate(Post post)throws Exception;
}
