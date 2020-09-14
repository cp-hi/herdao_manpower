package net.hedao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.hedao.hdp.mpclient.entity.Post;
import net.hedao.hdp.mpclient.mapper.PostMapper;
import net.hedao.hdp.mpclient.service.PostService;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    private final RemoteUserService remoteUserService;

    public List<Map> postList() {
        return baseMapper.postList();
    }

    public Page page(Page page, Map<String, String> params) {
        String searchText = params.get("searchText");
//        String[] groupIds = StringUtils.split(params.get("groupIds"), ",");
        String[] jobLevels = StringUtils.split(params.get("jobLevels"), ",");
        String[] sectionCodes = StringUtils.split(params.get("sectionCodes"), ",");
        String[] pipelineCodes = StringUtils.split(params.get("pipelineCodes"), ",");
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper
//                .in(null != groupIds, "GROUP_ID", groupIds)
                .in(null != jobLevels, "JOB_LEVEL", jobLevels)
                .in(null != sectionCodes, "SECTION_CODE", sectionCodes)
                .in(null != pipelineCodes, "PIPELINE_CODE", pipelineCodes)
                .like(StringUtils.isNotBlank(searchText), "POST_NAME", searchText);
        return this.page(page, queryWrapper);
    }

    public void addOrUpdate(Post post) throws Exception {
        if (baseMapper.chkDuplicatePostCode(post))
            throw new Exception("岗位编码重复了");
        if (baseMapper.chkDuplicatePostName(post))
            throw new Exception("岗位名称重复了");
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();

        if (null == post.getId()) {
            post.setCreatedTime(new Date());
            if (null != userInfo) {
                post.setCreatorCode(userInfo.getSysUser().getUsername());
//                post.setCreatorCode(userInfo.getSysUser().get());
            }
            this.save(post);
        } else {
            post.setModifiedTime(new Date());
            if (null != userInfo) {
                post.setModifierCode(userInfo.getSysUser().getUsername());
            }
            this.updateById(post);
        }
    }

}
