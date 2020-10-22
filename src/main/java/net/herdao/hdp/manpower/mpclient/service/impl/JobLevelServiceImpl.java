package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.JobLevelBatchDTO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.mapper.JobLevelMapper;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
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
@AllArgsConstructor
public class JobLevelServiceImpl extends ServiceImpl<JobLevelMapper, JobLevel> implements JobLevelService {

    JobGradeService jobGradeService;

    @Override
    public IPage page(Page page, JobLevel jobLevel) {
        return baseMapper.page(page, jobLevel);
    }

    @Override
    public List<Map> jobLevelList(Long groupId) {
        return baseMapper.jobLevelList(groupId);
    }

    @Override
    public void saveVerify(JobLevel jobLevel) {
//        if (baseMapper.chkCodeAndName(jobLevel))
//            throw new RuntimeException("请检查职级的名称和编码");
//        if (baseMapper.chkDuplicateJobLevelCode(jobLevel))
//            throw new RuntimeException("职级编码重复了");
        if (baseMapper.chkDuplicateJobLevelName(jobLevel))
            throw new RuntimeException("职级名称重复了");
    }


    @Override
    public void addEntity(JobLevel jobLevel, Object excelObj) {
        JobLevelBatchDTO excel = (JobLevelBatchDTO) excelObj;
        chkEntityExists("JOB_LEVEL_NAME", excel.getJobLevelName(), false);
        JobGrade jobGrade = jobGradeService.chkEntityExists("JOB_GRADE_NAME", excel.getJobGrade(), true);
        if (null == jobGrade.getGroupId()) throw new RuntimeException("集团ID为空");

        jobLevel.setJobLevelName(excel.getJobLevelName());
        jobLevel.setJobGradeId(jobGrade.getId());
        jobLevel.setGroupId(jobGrade.getGroupId());
    }

    @Override
    public void updateEntity(JobLevel jobLevel, Object excelObj) {
        JobLevelBatchDTO excel = (JobLevelBatchDTO) excelObj;
        JobGrade jobGrade = jobGradeService.chkEntityExists("JOB_GRADE_NAME", excel.getJobGrade(), true);
        JobLevel tmp = chkEntityExists("JOB_LEVEL_NAME", excel.getJobLevelName(), true);
        if (null == jobGrade.getGroupId()) throw new RuntimeException("集团ID为空");

        jobLevel.setJobLevelName(excel.getJobLevelName());
        jobLevel.setJobGradeId(jobGrade.getId());
        jobLevel.setGroupId(jobGrade.getGroupId());
        jobLevel.setId(tmp.getId());
    }

}
