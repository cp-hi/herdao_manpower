package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.PostDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.PostStaffDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.manpower.mpclient.mapper.PostMapper;
import net.herdao.hdp.manpower.mpclient.mapper.SectionMapper;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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
    public List<Map> postList(Long groupId) {
        return baseMapper.postList(    groupId);
    }

    @Override
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
    @OperationEntity(operation = "保存", clazz = Post.class)
    public boolean saveOrUpdate(Post post) {
        if (baseMapper.chkDuplicatePostCode(post))
            throw new RuntimeException("岗位编码重复了");
        if (baseMapper.chkDuplicatePostName(post))
            throw new RuntimeException("岗位名称重复了");
        return super.saveOrUpdate(post);
    }


    @Override
    public Map getPostStaffInfo(Long postId) {
        Map map = new LinkedHashMap();
        Integer onJobs = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and p.id =" + postId);
        Integer fullTimes = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and s.JOB_TYPE = '1' and p.id = " + postId);
        Integer partTimes = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and s.JOB_TYPE = '2' and p.id = " + postId);
        Integer exchanges = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and s.JOB_TYPE = '4' and p.id = " + postId);

        Integer interns = 0; //实习
        Integer probation = 0;//试用
        Integer authorized = 0;//编制

        map.put("onJobs", onJobs);//在职
        map.put("interns", interns);//实习
        map.put("fullTimes", fullTimes);//全职
        map.put("partTimes", partTimes);//兼职
        map.put("exchanges", exchanges);//交流
        map.put("probation", probation);//试用
        map.put("authorized", authorized);//编制

        //年龄分布
        List<Map<String, BigDecimal>> ages = baseMapper.getPostStaffAges(postId);
        map.put("age1", ages.size() > 0 ? ages.get(0).get("age1") : 0);
        map.put("age2", ages.size() > 0 ? ages.get(0).get("age2") : 0);
        map.put("age3", ages.size() > 0 ? ages.get(0).get("age3") : 0);
        map.put("age4", ages.size() > 0 ? ages.get(0).get("age4") : 0);
        map.put("age5", ages.size() > 0 ? ages.get(0).get("age5") : 0);
        map.put("age6", ages.size() > 0 ? ages.get(0).get("age6") : 0);

        return map;
    }

    @Override
    public List<PostDetailDTO> getPostDetails(Long postId, String operation, String size) {
        String limit = "";
        if (!"download".equals(operation)) {
            if (StringUtils.isBlank(size))
                size = "10";
            limit = "limit " + size;
        }
        return baseMapper.getPostDetails(postId, limit);
    }

    @Override
    public List<PostStaffDTO> getPostStaffs(Long postId, String operation, String size) {
        String limit = "";
        if (!"download".equals(operation)) {
            if (StringUtils.isBlank(size))
                size = "10";
            limit = "limit " + size;
        }
        return baseMapper.getPostStaffs(postId, limit);
    }
}
