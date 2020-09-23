package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.mpclient.entity.Post;
import net.herdao.hdp.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.mpclient.mapper.PostMapper;
import net.herdao.hdp.mpclient.mapper.SectionMapper;
import net.herdao.hdp.mpclient.service.PostService;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    final PipelineMapper pipelineMapper;
    final SectionMapper sectionMapper;

    @Override
    public List<Map> postList() {
        return baseMapper.postList();
    }

    public Page page(Page page, Map<String, String> params) {
        String searchText = params.get("searchText");
        /*TODO 问清楚 group 与 section 及  pipeline 之间关联查询方式
         * 1.条件是否都可以多选，集团与板块 管线之间存在从属关系，
         *      是否存在联动选择，从属条件是否根据集团排序
         * 2.是否传groupIds 过来 就只查 集团所属的板块与管线下的岗位
         * 3.是否允许选择非从属关系的条件
         */
        String groupId = params.get("groupId");
        String jobLevel = params.get("jobLevel");
        String sectionCode = params.get("sectionCodes");
        String pipelineCode = params.get("pipelineCodes");
        boolean hasGroupId = StringUtils.isNotBlank(groupId);
        List<String> sectionCodes = new ArrayList<>(), pipelineCodes = new ArrayList<>();
        if (hasGroupId) {
            sectionCodes = sectionMapper.getSectionCodesByGroupId(Long.valueOf(groupId));
            pipelineCodes = pipelineMapper.getPipelineCodesByGroupId(Long.valueOf(groupId));
        }

        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(StringUtils.isNotBlank(jobLevel), "JOB_LEVEL", jobLevel)
                .eq(StringUtils.isNotBlank(sectionCode), "SECTION_CODE", sectionCode)
                .eq(StringUtils.isNotBlank(pipelineCode), "PIPELINE_CODE", pipelineCode)
                .in(StringUtils.isBlank(sectionCode) && sectionCodes.size() > 0, "SECTION_CODE", sectionCodes)
                .in(StringUtils.isBlank(pipelineCode) && pipelineCodes.size() > 0, "SECTION_CODE", pipelineCodes)
                .like(StringUtils.isNotBlank(searchText), "POST_NAME", searchText);

        Page p = this.page(page, queryWrapper);
        return p;
    }

    @Override
    public boolean saveOrUpdate(Post post) {
        if (baseMapper.chkDuplicatePostCode(post)) {
            throw new RuntimeException("岗位编码重复了");
        }
        if (baseMapper.chkDuplicatePostName(post)) {
            throw new RuntimeException("岗位名称重复了");
        }
        return super.saveOrUpdate(post);
    }
}
