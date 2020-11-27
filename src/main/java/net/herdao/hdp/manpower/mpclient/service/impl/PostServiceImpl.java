package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysStation;
import net.herdao.hdp.admin.api.feign.RemoteStationService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.mapper.PostMapper;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.vo.post.PostBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostBatchUpdateVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostStaffVO;
import net.herdao.hdp.manpower.sys.cache.DictCache;
import net.herdao.hdp.manpower.sys.cache.GroupCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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

    final GroupService groupService;
    final SectionService sectionService;
    final PipelineService pipelineService;
    final PostSeqService postSeqService;
    final JobLevelService jobLevelService;
    final RemoteStationService remoteStationService;
    @Override
    public List<PostDTO> postList(Long groupId) {
        return baseMapper.postList(groupId);
    }

    //region 报表
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
    public List<PostDTO> getPostDetails(Long postId, String operation, String size) {
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

    //endregion

    //region 批量新增编辑校验
    @Override
    public void batchAddVerify(Post post, Object excelObj, StringBuffer buffer) {
        PostBatchAddVO excel = (PostBatchAddVO) excelObj;
        Group group = GroupCache.getGroupByName(excel.getGroupName(), true);
        if (null != group) post.setGroupId(group.getId());//第一步是先设置集团
        chkEntityExists(excel.getPostName(), group.getId(), false, buffer);

        Section section = sectionService.chkEntityExists(excel.getSectionName(), group.getId(), true, buffer);
        Pipeline pipeline = pipelineService.chkEntityExists(excel.getPipelineName(), group.getId(), true, buffer);
        PostSeq postSeq = postSeqService.chkEntityExists(excel.getPostSeqName(), group.getId(), true, buffer);
        JobLevel jobLevel = jobLevelService.chkEntityExists(excel.getJobLevelName(), group.getId(), true, buffer);

        if (StringUtils.isBlank(buffer)) {
            post.setSectionId(section.getId());
            post.setPipelineId(pipeline.getId());
            post.setPostSeqId(postSeq.getId());
            post.setJobLevelId1(jobLevel.getId());
        }
    }

    @Override
    public void batchUpdateVerify(Post post, Object excelObj, StringBuffer buffer) {
        PostBatchUpdateVO excel = (PostBatchUpdateVO) excelObj;

        Group group = GroupCache.getGroupByName(excel.getGroupName(), true);
        if (null != group) post.setGroupId(group.getId());//第一步是先设置集团
        Post tmp = chkEntityExists(excel.getPostName(), group.getId(), true, buffer);

        Section section = sectionService.chkEntityExists(excel.getSectionName(), group.getId(), true, buffer);
        Pipeline pipeline = pipelineService.chkEntityExists(excel.getPipelineName(), group.getId(), true, buffer);
        PostSeq postSeq = postSeqService.chkEntityExists(excel.getPostSeqName(), group.getId(), true, buffer);
        JobLevel jobLevel = jobLevelService.chkEntityExists(excel.getJobLevelName(), group.getId(), true, buffer);

        post.setOrgType(DictCache.getDictVal("GWZZLX", excel.getOrgType(), buffer));
        post.setPostLevel(DictCache.getDictVal("XCJB", excel.getPostLevel(), buffer));
        post.setYearPayRatio(DictCache.getDictVal("XCBL", excel.getYearPayRatio(), buffer));
        post.setPerforSalaryRatio(DictCache.getDictVal("YDJXGZBL", excel.getPerforSalaryRatio(), buffer));

        if (StringUtils.isBlank(buffer)) {
            post.setSectionId(section.getId());
            post.setPipelineId(pipeline.getId());
            post.setPostSeqId(postSeq.getId());
            post.setJobLevelId1(jobLevel.getId());
            post.setId(tmp.getId());
        }
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



    @Override
    public Long saveSync(Post post) {
        SysStation station = converterValue(post);
        R<Long> r = remoteStationService.save(station);
        return checkData(r);
    }

    @Override
    public Boolean updateSync(Post post) {
        SysStation station = converterValue(post);
        station.setId(post.getId());
        R<Boolean> r = remoteStationService.update(station);
        return checkData(r);
    }

    @Override
    public Boolean deleteSync(Serializable id) {

        R<Boolean> r = remoteStationService.delete(id);
        return checkData(r);
    }

    @Override
    public Boolean saveOrUpdateBatchSync(Collection<Post> collection) {
        List<SysStation> list = new ArrayList<>();
        collection.forEach(post -> {
            SysStation station = converterValue(post);
            station.setId(post.getId());
        });
        R<Boolean> r = remoteStationService.saveOrUpdateBatch(list);
        return checkData(r);
    }
    private SysStation converterValue(Post post){
        SysStation station = new SysStation();
        station.setCode(post.getPostCode());
        station.setName(post.getPostName());
        station.setGroupId(post.getGroupId());
        station.setBlockId(post.getSectionId());
        station.setPipeId(post.getPipelineId());
        station.setSeqId(post.getPostSeqId());
        station.setBeginGrade(post.getJobLevelId1());
        station.setEndGrade(post.getJobLevelId2());
        station.setSingleGrade(post.getSingleJobLevle().toString());
        return station;
    }

    @Autowired
    private PostMapper mapper;
    @Override
    public void validityCheck(Long id, String msg) throws Exception {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        if (mapper.selectOne(queryWrapper) == null) {
            throw new Exception(msg);
        }
    }

}
