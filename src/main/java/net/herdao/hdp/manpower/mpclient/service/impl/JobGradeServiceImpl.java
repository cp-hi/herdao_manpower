package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobGradeShortVO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.mapper.JobGradeMapper;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * @ClassName JobGradeServiceImpl
 * @Description JobGradeServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 16:28
 * @Version 1.0
 */
@Service
public class JobGradeServiceImpl extends EntityServiceImpl<JobGradeMapper, JobGrade> implements JobGradeService {

    @Autowired
    private JobLevelService jobLevelService;

    @Override
    public List<JobGradeShortVO> jobGradeList(Long groupId) {
        return baseMapper.jobGradeList(groupId);
    }

    @Override
    public Function<JobGrade, Long> getGroupIdMapper() {
        return JobGrade::getGroupId;
    }

    @Override
    public Function<JobGrade, String> getNameMapper() {
        return JobGrade::getJobGradeName;
    }

    @Override
    public void delVerify(Serializable id) {
        int count = jobLevelService.count(Wrappers.<JobLevel>lambdaQuery().eq(JobLevel::getJobGradeId, id));
        if(count>0){
            throw new RuntimeException("该职等下有职级不可删除");
        }
    }
}
