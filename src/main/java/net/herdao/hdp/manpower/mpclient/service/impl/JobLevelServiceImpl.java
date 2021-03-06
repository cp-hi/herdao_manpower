package net.herdao.hdp.manpower.mpclient.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.mapper.JobLevelMapper;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelBatchVO;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelListVO;
import net.herdao.hdp.manpower.sys.service.CacheService;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;

/**
 * @ClassName JobLevelServiceImpl
 * @Description JobLevelServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 16:28
 * @Version 1.0
 */
@Service
public class JobLevelServiceImpl extends EntityServiceImpl<JobLevelMapper, JobLevel> implements JobLevelService {

    @Autowired
    CacheService cacheService;
    @Autowired
    JobGradeService jobGradeService;
    
    public R<IPage<JobLevelListVO>> getPage(Page page, JobLevel jobLevel) {
        IPage p = this.baseMapper.page(page, jobLevel);
        List<JobLevelListVO> vos = DtoConverter.dto2vo(p.getRecords(), JobLevelListVO.class);
        p.setRecords(vos);
        return R.ok(p);
    }
    
    @Override
    public List<Map> jobLevelList(Long groupId) {
        return baseMapper.jobLevelList(groupId);
    }

    /**
     * 与父类的验证方式不同，
     * 除了集团内不能同名，
     * 同职等下也不能同名，
     * 所以要覆盖父验证方法
     *
     * @param jobLevel
     * @param buffer
     */
    @Override
    public void saveVerify(JobLevel jobLevel, StringBuffer buffer) {
        Boolean result = baseMapper.checkDuplicateNameInJobGrade(jobLevel);
        if (result) buffer.append("；该职等下已经有相同名称的职级");
        super.saveVerify(jobLevel, buffer);
    }

    @Override
    public void batchAddVerify(JobLevel jobLevel, Object excelObj, StringBuffer buffer) {
        JobLevelBatchVO excel = (JobLevelBatchVO) excelObj;

        Group group = cacheService.getGroupByName(excel.getGroupName(), true);
        if (null != group) jobLevel.setGroupId(group.getId());

        chkEntityExists(excel.getJobLevelName(), group.getId(), false,buffer);
        JobGrade jobGrade = jobGradeService.chkEntityExists(excel.getJobGrade(), group.getId(), true,buffer);

        if (!group.getId().equals(jobGrade.getGroupId()))
            buffer.append("根据名称查找的集团与职等所属集团不匹配");

        if (StringUtils.isBlank(buffer))
            jobLevel.setGroupId(group.getId());
    }

    @Override
    public void batchUpdateVerify(JobLevel jobLevel, Object excelObj, StringBuffer buffer) {
        JobLevelBatchVO excel = (JobLevelBatchVO) excelObj;
        Group group = cacheService.getGroupByName(excel.getGroupName(), true);
        if (null != group) jobLevel.setGroupId(group.getId());

        JobLevel tmp = chkEntityExists(excel.getJobLevelName(), group.getId(), true,buffer);
        JobGrade jobGrade = jobGradeService.chkEntityExists(excel.getJobGrade(), group.getId(), true,buffer);

        if (!group.getId().equals(jobGrade.getGroupId()))
            buffer.append("根据名称查找的集团与职等所属集团不匹配");

        if (StringUtils.isBlank(buffer)) {
            jobLevel.setGroupId(group.getId());
            jobLevel.setId(tmp.getId());
        }
    }

    @Override
    public Function<JobLevel, String> getNameMapper() {
        return JobLevel::getJobLevelName;
    }

    @Override
    public Function<JobLevel, Long> getGroupIdMapper() {
        return JobLevel::getGroupId;
    }

    @Autowired
    private JobLevelMapper mapper;
    @Override
    public void validityCheck(Long id, String msg) throws Exception {
        QueryWrapper<JobLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        if (mapper.selectOne(queryWrapper) == null) {
            throw new Exception(msg);
        }
    }

}
