package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.mpclient.entity.JobGrade;
import net.herdao.hdp.mpclient.mapper.JobGradeMapper;
import net.herdao.hdp.mpclient.service.JobGradeService;
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

    public List<Map> jobGradeList() {
        return baseMapper.jobGradeList();
    }

    @Override
    public boolean saveOrUpdate(JobGrade jobGrade) {
        if (baseMapper.chkDuplicateJobGradeCode(jobGrade))
            throw new RuntimeException("职级编码重复了");
        if (baseMapper.chkDuplicateJobGradeName(jobGrade))
            throw new RuntimeException("职级名称重复了");
        return super.saveOrUpdate(jobGrade);
    }
}
