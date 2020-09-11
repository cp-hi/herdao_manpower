package com.hedao.hdp.ehrclient.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hedao.hdp.ehrclient.post.entity.Post;
import com.hedao.hdp.ehrclient.post.mapper.PostMapper;
import com.hedao.hdp.ehrclient.post.service.PostService;
import org.springframework.stereotype.Service;

/**
 * @ClassName PostServiceImpl
 * @Description PostServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:00
 * @Version 1.0
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
}
