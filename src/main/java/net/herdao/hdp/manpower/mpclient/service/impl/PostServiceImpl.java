package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.*;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.manpower.mpclient.mapper.PostMapper;
import net.herdao.hdp.manpower.mpclient.mapper.SectionMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
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
    final GroupService groupService;
    final SectionService sectionService;
    final PipelineService pipelineService;
    final PostSeqService postSeqService;
    final JobLevelService jobLevelService;
    final SysDictItemService sysDictItemService;

    @Override
    public IPage page(Page page, Post post) {
        IPage<PostListDTO> p = baseMapper.page(page, post);
        return p;
    }

    @Override
    public List<Map> postList(Long groupId) {
        return baseMapper.postList(groupId);
    }


    @Override
    public void saveVerify(Post post) {
//        if (baseMapper.chkDuplicatePostCode(post))
//            throw new RuntimeException("岗位编码重复了");
        if (baseMapper.chkDuplicatePostName(post))
            throw new RuntimeException("岗位名称重复了");
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

        List<Map> staffInfo = new ArrayList<>();
        staffInfo.add(new HashMap() {{
            put("name", "在职");
            put("value", onJobs);
        }});
        staffInfo.add(new HashMap() {{
            put("name", "实习");
            put("value", interns);
        }});
        staffInfo.add(new HashMap() {{
            put("name", "全职");
            put("value", fullTimes);
        }});
        staffInfo.add(new HashMap() {{
            put("name", "兼职");
            put("value", partTimes);
        }});
        staffInfo.add(new HashMap() {{
            put("name", "交流");
            put("value", exchanges);
        }});
        staffInfo.add(new HashMap() {{
            put("name", "试用");
            put("value", probation);
        }});
        staffInfo.add(new HashMap() {{
            put("name", "编制");
            put("value", authorized);
        }});

        map.put("staffInfo", staffInfo);

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


    @Override
    public void importVerify(Post post, Object excelObj, int type) {
        boolean add = (0 == type);
        if (add) addPost(post, excelObj);
        else updatePost(post, excelObj);
        //这个验证要放 最后，因为前面要给ID赋值
        this.saveVerify(post);
    }

    private void addPost(Post post, Object excelObj) {
        PostBatchAddDTO excel = (PostBatchAddDTO) excelObj;
        getPostByName(excel.getPostName(), true);
        Group group = getGroupByName(excel.getGroupName());
        Section section = getEntityByName(sectionService, "SECTION_NAME", excel.getSectionName());
        Pipeline pipeline = getEntityByName(pipelineService, "PIPELINE_NAME", excel.getPipelineName());
        PostSeq postSeq = getEntityByName(postSeqService, "POST_SEQ_NAME", excel.getPostSeqName());
        JobLevel jobLevel = getEntityByName(jobLevelService, "JOB_LEVEL_NAME", excel.getJobLevelName());

        post.setGroupId(group.getId());
        post.setSectionId(section.getId());
        post.setPipelineId(pipeline.getId());
        post.setPostSeqId(postSeq.getId());
        post.setJobLevelId1(jobLevel.getId());
    }

    private void updatePost(Post post, Object excelObj) {
        PostBatchUpdateDTO excel = (PostBatchUpdateDTO) excelObj;
        Post tmp = getPostByName(excel.getPostName(), false);
        Group group = getGroupByName(excel.getGroupName());
        Section section = getEntityByName(sectionService, "SECTION_NAME", excel.getSectionName());
        Pipeline pipeline = getEntityByName(pipelineService, "PIPELINE_NAME", excel.getPipelineName());
        PostSeq postSeq = getEntityByName(postSeqService, "POST_SEQ_NAME", excel.getPostSeqName());
        JobLevel jobLevel = getEntityByName(jobLevelService, "JOB_LEVEL_NAME", excel.getJobLevelName());

        post.setGroupId(group.getId());
        post.setSectionId(section.getId());
        post.setPipelineId(pipeline.getId());
        post.setPostSeqId(postSeq.getId());
        post.setJobLevelId1(jobLevel.getId());
        post.setId(tmp.getId());

        post.setOrgType(getDictItem("GWZZLX",excel.getOrgType()).getValue());
        post.setPostLevel(getDictItem("XCJB",excel.getPostLevel()).getValue());
        post.setYearPayRatio(getDictItem("XCBL",excel.getYearPayRatio()).getValue());
        post.setPerforSalaryRatio(getDictItem("YDJXGZBL",excel.getPerforSalaryRatio()).getValue());
    }

    private Post getPostByName(String postName, boolean add) {
        Post post = this.baseMapper.selectOne(new QueryWrapper<Post>().eq("POST_NAME", postName));
        if (add && null != post)
            throw new RuntimeException("已存在此岗位：" + post.getPostName());
        if (!add && null == post)
            throw new RuntimeException("不存在此岗位：" + post.getPostName());
        return post;
    }

    private Group getGroupByName(String groupName) {
        Group group = groupService.getOne(new QueryWrapper<Group>()
                .eq("GROUP_NAME", groupName));
        if (null == group)
            throw new RuntimeException("不存在此集团：" + groupName);
        return group;
    }

    private <T> T getEntityByName(EntityService<T> service, String field, String name) {
        T t = (T) service.getEntityByField(field, name);
        if (null == t) throw new RuntimeException("不存在：" + name);
        return (T) t;
    }

    private SysDictItem getDictItem(String type, String label) {
        SysDictItem dictItem = sysDictItemService.getOne(
                new QueryWrapper<SysDictItem>().eq("type", type).eq("label", label));
        if (null == dictItem) throw new RuntimeException("不存在此字典值：" + label);
        return dictItem;
    }
}
