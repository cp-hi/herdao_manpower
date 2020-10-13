package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.mapper.JobGradeMapper;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName JobGradeServiceImpl
 * @Description JobGradeServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 16:28
 * @Version 1.0
 */
@Service
public class JobGradeServiceImpl extends ServiceImpl<JobGradeMapper, JobGrade> implements JobGradeService {

    @Override
    public List<Map> jobGradeList() {
        return baseMapper.jobGradeList();
    }

    @Override
    public void saveVerify(JobGrade jobGrade) {
        if (baseMapper.chkDuplicateJobGradeCode(jobGrade))
            throw new RuntimeException("职级编码重复了");
        if (baseMapper.chkDuplicateJobGradeName(jobGrade))
            throw new RuntimeException("职级名称重复了");
    }

}
