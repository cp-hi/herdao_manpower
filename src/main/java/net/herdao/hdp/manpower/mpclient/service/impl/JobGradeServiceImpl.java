package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

//    @Override
//    public String generateEntityCode() {
//        String tabel = getTabelName();
//        String field = getTableCodeField();
//        return baseMapper.generateEntityCode(tabel, field);
//    }

//    @Override
//    public IPage page(Page page, JobGrade jobGrade) {
//        return baseMapper.page(page, jobGrade);
//    }

    @Override
    public List<JobGradeShortVO> jobGradeList(Long groupId) {
        return baseMapper.jobGradeList(groupId);
    }

    @Override
    public void saveVerify(JobGrade jobGrade) {
//        if (baseMapper.chkDuplicateJobGradeCode(jobGrade))
//            throw new RuntimeException("职级编码重复了");
        if (baseMapper.chkDuplicateJobGradeName(jobGrade))
            throw new RuntimeException("职级名称重复了:" + jobGrade.getJobGradeName());
    }
}
