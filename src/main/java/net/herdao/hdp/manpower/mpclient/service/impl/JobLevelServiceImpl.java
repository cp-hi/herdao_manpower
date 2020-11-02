package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelBatchVO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.mapper.JobLevelMapper;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OperationLogServiceImpl
 * @Description OperationLogServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 16:28
 * @Version 1.0
 */
@Service
public class JobLevelServiceImpl extends EntityServiceImpl<JobLevelMapper, JobLevel> implements JobLevelService {

    @Autowired
    JobGradeService jobGradeService;

    @Override
    public List<Map> jobLevelList(Long groupId) {
        return baseMapper.jobLevelList(groupId);
    }

    @Override
    public void addEntity(JobLevel jobLevel, Object excelObj) {
        JobLevelBatchVO excel = (JobLevelBatchVO) excelObj;
        StringBuffer buffer = new StringBuffer();
        chkEntityExists("JOB_LEVEL_NAME", excel.getJobLevelName(), false,buffer);
        JobGrade jobGrade = jobGradeService.chkEntityExists("JOB_GRADE_NAME", excel.getJobGrade(), true);

        if (null == jobGrade.getGroupId())
            buffer.append("；集团ID为空");

        if(StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());

        jobLevel.setJobLevelName(excel.getJobLevelName());
        jobLevel.setJobGradeId(jobGrade.getId());
        jobLevel.setGroupId(jobGrade.getGroupId());
    }

    @Override
    public void updateEntity(JobLevel jobLevel, Object excelObj) {
        JobLevelBatchVO excel = (JobLevelBatchVO) excelObj;
        StringBuffer buffer = new StringBuffer();

        JobLevel tmp = chkEntityExists("JOB_LEVEL_NAME", excel.getJobLevelName(), true,buffer);
        JobGrade jobGrade = jobGradeService.chkEntityExists("JOB_GRADE_NAME", excel.getJobGrade(), true);

        if (null == jobGrade.getGroupId())
            buffer.append("；集团ID为空");

        if(StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());

        jobLevel.setJobLevelName(excel.getJobLevelName());
        jobLevel.setJobGradeId(jobGrade.getId());
        jobLevel.setGroupId(jobGrade.getGroupId());
        jobLevel.setId(tmp.getId());
    }

}
