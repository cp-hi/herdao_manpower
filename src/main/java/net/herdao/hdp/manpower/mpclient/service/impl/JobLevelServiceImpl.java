package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelImportVO;
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
        return baseMapper.page(page,jobLevel);
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
    public void importVerify(Object excelObj, JobLevel jobLevel, int type) {
        boolean add = (type == 0);

        //TODO 添加校验方法
        JobLevelImportVO excel = (JobLevelImportVO) excelObj;

        JobGrade jobGrade = jobGradeService.getOne(new QueryWrapper<JobGrade>()
                .eq("JOB_GRADE_NAME", excel.getJobGrade()));

        if (null == jobGrade)
            throw new RuntimeException("查不到此职等：" + excel.getJobGrade());

        List<JobLevel> tmp = this.baseMapper.selectList(new QueryWrapper<JobLevel>()
                .eq("GROUP_ID", jobGrade.getGroupId())
                .eq("JOB_LEVEL_NAME", excel.getJobLevelName()));

        //TODO 通过add区分新增修改的不同处理

        if (null != tmp) {
            if (!tmp.get(0).getGroupId().equals(jobGrade.getGroupId()))
                throw new RuntimeException("导入的职等中的集团与原先保存的集团不一致");
            jobLevel.setId(tmp.get(0).getId());
        }

        if (null == jobGrade.getGroupId())
            throw new RuntimeException("集团ID为空");

        jobLevel.setJobLevelName(excel.getJobLevelName());
        jobLevel.setJobGradeId(jobGrade.getId());
        jobLevel.setGroupId(jobGrade.getGroupId());

        //这个验证要放 最后，因为前面要给ID赋值
        this.saveVerify(jobLevel);
    }
}
