package net.herdao.hdp.manpower.mpclient.service.impl;

import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobGradeShortVO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.mapper.JobGradeMapper;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<JobGradeShortVO> jobGradeList(Long groupId) {
        return baseMapper.jobGradeList(groupId);
    }

}
