package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostStaffDTO;
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
    public IPage<PostDTO> page(Page<PostDTO> page, String searchTxt) {
        IPage<PostDTO> p = baseMapper.page(page, searchTxt);
        return p;
    }

    @Override
    public List<Map> postList(Long groupId) {
        return baseMapper.postList(groupId);
    }

    @Override
    public Page page(Page page, Map<String, String> params) {
//        String searchText = params.get("searchText");
//        /*TODO 问清楚 group 与 section 及  pipeline 之间关联查询方式
//         * 1.条件是否都可以多选，集团与板块 管线之间存在从属关系，
//         *      是否存在联动选择，从属条件是否根据集团排序
//         * 2.是否传groupIds 过来 就只查 集团所属的板块与管线下的岗位
//         * 3.是否允许选择非从属关系的条件
//         */
//        String groupId = params.get("groupId");
//        String jobLevelId = params.get("jobLevelId");
//        String sectionId = params.get("sectionId");
//        String pipelineId = params.get("pipelineId");
//
//        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(StringUtils.isNotBlank(searchText), "POST_NAME", searchText);
//        if (StringUtils.isNotBlank(groupId)) {
//            queryWrapper.eq("GROUP_ID", Long.valueOf(groupId));
//        }
//        if (StringUtils.isNotBlank(jobLevelId)) {
//            queryWrapper.eq("JOB_LEVEL_ID", Long.valueOf(jobLevelId));
//        }
//        if (StringUtils.isNotBlank(sectionId)) {
//            queryWrapper.eq("SECTION_ID", Long.valueOf(sectionId));
//        }
//        if (StringUtils.isNotBlank(pipelineId)) {
//            queryWrapper.eq("PIPELINE_ID", Long.valueOf(pipelineId));
//        }
//        Page p = super.page(page, queryWrapper);
//        return p;
        return null;
    }

    @Override
    public void saveVerify(Post post) {
        if (baseMapper.chkDuplicatePostCode(post)) {
            throw new RuntimeException("岗位编码重复了");
        }
        if (baseMapper.chkDuplicatePostName(post)) {
            throw new RuntimeException("岗位名称重复了");
        }
    }

    @Override
    public Map getPostStaffInfo(Long postId) {
        Map map = new LinkedHashMap();
        Integer onJobs = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and p.id =" + postId);
        Integer fullTimes = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and s.JOB_TYPE = '1' and p.id = " + postId);
        Integer partTimes = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and s.JOB_TYPE = '2' and p.id = " + postId);
        Integer exchanges = baseMapper.getPostStaffCount("and s.STAFF_SCOPE = '1' and s.JOB_TYPE = '4' and p.id = " + postId);

        //TODO 未实现以下3个统计
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
            if (StringUtils.isBlank(size)) {
                size = "10";
            }
            limit = "limit " + size;
        }
        return baseMapper.getPostDetails(postId, limit);
    }

    @Override
    public List<PostStaffDTO> getPostStaffs(Long postId, String operation, String size) {
        String limit = "";
        if (!"download".equals(operation)) {
            if (StringUtils.isBlank(size)) {
                size = "10";
            }
            limit = "limit " + size;
        }
        return baseMapper.getPostStaffs(postId, limit);
    }
}
