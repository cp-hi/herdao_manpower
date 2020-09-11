package com.hedao.hdp.mpclient.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hedao.hdp.mpclient.post.entity.Post;
import com.hedao.hdp.mpclient.post.mapper.PostMapper;
import com.hedao.hdp.mpclient.post.service.PostService;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PostServiceImpl
 * @Description PostServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:00
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    public List<Post> findAll() {
        return baseMapper.findAll();
    }

    private final RemoteUserService remoteUserService;

    public void addOrUpdate(Post post) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();

        if (null == post.getId()) {
            post.setCreatedTime(new Date());
            if (null != userInfo) {
                post.setCreatorCode(userInfo.getSysUser().getUsername());
//                post.setCreatorCode(UserInfo.getSysUser().);
            }
            this.save(post);
        } else {
            post.setModifiedTime(new Date());
            if (null != userInfo) {
                post.setModifierCode(userInfo.getSysUser().getUsername());
//                post.setCreatorCode(UserInfo.getSysUser().);
            }
            this.updateById(post);
        }
    }

}
