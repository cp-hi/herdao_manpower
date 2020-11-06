package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.PipelineMapper;
import net.herdao.hdp.manpower.mpclient.mapper.PostMapper;
import net.herdao.hdp.manpower.mpclient.mapper.SectionMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.vo.post.*;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

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
public class PostServiceImpl extends EntityServiceImpl<PostMapper, Post> implements PostService {

    final PipelineMapper pipelineMapper;
    final SectionMapper sectionMapper;
    final GroupService groupService;
    final SectionService sectionService;
    final PipelineService pipelineService;
    final PostSeqService postSeqService;
    final JobLevelService jobLevelService;
    final SysDictItemService sysDictItemService;

    @Override
    public List<PostDTO> postList(Long groupId) {
        return baseMapper.postList(groupId);
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

        Map mapOnJobs = new HashMap();
        mapOnJobs.put("name", "在职");
        mapOnJobs.put("value", onJobs);

        Map mapInterns = new HashMap();
        mapInterns.put("name", "实习");
        mapInterns.put("value", interns);

        Map mapFullTimes = new HashMap();
        mapFullTimes.put("name", "全职");
        mapFullTimes.put("value", fullTimes);

        Map mapPartTimes = new HashMap();
        mapPartTimes.put("name", "兼职");
        mapPartTimes.put("value", partTimes);

        Map mapExchanges = new HashMap();
        mapExchanges.put("name", "交流");
        mapExchanges.put("value", exchanges);

        Map mapProbation = new HashMap();
        mapProbation.put("name", "试用");
        mapProbation.put("value", probation);

        Map mapAuthorized = new HashMap();
        mapAuthorized.put("name", "编制");
        mapAuthorized.put("value", authorized);

        List<Map> staffInfo = new ArrayList<>();
        staffInfo.add(mapOnJobs);
        staffInfo.add(mapInterns);
        staffInfo.add(mapFullTimes);
        staffInfo.add(mapPartTimes);
        staffInfo.add(mapExchanges);
        staffInfo.add(mapProbation);
        staffInfo.add(mapAuthorized);

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
    public List<PostDetailVO> getPostDetails(Long postId, String operation, String size) {
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
    public List<PostStaffVO> getPostStaffs(Long postId, String operation, String size) {
        String limit = "";
        if (!"download".equals(operation)) {
            if (StringUtils.isBlank(size)) {
                size = "10";
            }
            limit = "limit " + size;
        }
        return baseMapper.getPostStaffs(postId, limit);
    }

    //region 批量新增编辑

    @SneakyThrows
    @Override
    public void addEntity(Post post, Object excelObj) {
        PostBatchAddVO excel = (PostBatchAddVO) excelObj;
        StringBuffer buffer = new StringBuffer();
        Group group = groupService.selectByName(excel.getGroupName(), true);
        chkEntityExists(excel.getPostName(), group.getId(), false);
        Section section = sectionService.chkEntityExists(excel.getSectionName(), group.getId(), true, buffer);
        Pipeline pipeline = pipelineService.chkEntityExists(excel.getPipelineName(), group.getId(), true, buffer);
        PostSeq postSeq = postSeqService.chkEntityExists(excel.getPostSeqName(), group.getId(), true, buffer);
        JobLevel jobLevel = jobLevelService.chkEntityExists(excel.getJobLevelName(), group.getId(), true, buffer);

        if (StringUtils.isNotBlank(buffer.toString()))
            throw new Exception(buffer.toString());

        post.setGroupId(group.getId());
        post.setSectionId(section.getId());
        post.setPipelineId(pipeline.getId());
        post.setPostSeqId(postSeq.getId());
        post.setJobLevelId1(jobLevel.getId());
    }

    @SneakyThrows
    @Override
    public void updateEntity(Post post, Object excelObj) {
        PostBatchUpdateVO excel = (PostBatchUpdateVO) excelObj;
        StringBuffer buffer = new StringBuffer();
        Group group = groupService.selectByName(excel.getGroupName(), true);
        Post tmp = chkEntityExists(excel.getPostName(), group.getId(), true, buffer);
        Section section = sectionService.chkEntityExists(excel.getSectionName(), group.getId(), true, buffer);
        Pipeline pipeline = pipelineService.chkEntityExists(excel.getPipelineName(), group.getId(), true, buffer);
        PostSeq postSeq = postSeqService.chkEntityExists(excel.getPostSeqName(), group.getId(), true, buffer);
        JobLevel jobLevel = jobLevelService.chkEntityExists(excel.getJobLevelName(), group.getId(), true, buffer);

        post.setOrgType(sysDictItemService.getDictItemValue("GWZZLX", excel.getOrgType(), buffer));
        post.setPostLevel(sysDictItemService.getDictItemValue("XCJB", excel.getPostLevel(), buffer));
        post.setYearPayRatio(sysDictItemService.getDictItemValue("XCBL", excel.getYearPayRatio(), buffer));
        post.setPerforSalaryRatio(sysDictItemService.getDictItemValue("YDJXGZBL", excel.getPerforSalaryRatio(), buffer));

        if (StringUtils.isNotBlank(buffer.toString()))
            throw new Exception(buffer.toString());

        post.setGroupId(group.getId());
        post.setSectionId(section.getId());
        post.setPipelineId(pipeline.getId());
        post.setPostSeqId(postSeq.getId());
        post.setJobLevelId1(jobLevel.getId());
        post.setId(tmp.getId());
    }

    //endregion



    @Override
    public Function<Post, String> getNameMapper() {
        return Post::getPostName;
    }

    @Override
    public Function<Post, Long> getGroupIdMapper() {
        return Post::getGroupId;
    }
}
