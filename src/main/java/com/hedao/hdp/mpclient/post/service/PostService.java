package com.hedao.hdp.mpclient.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hedao.hdp.mpclient.post.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService extends IService<Post> {
    List<Post> findAll();

}
