package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
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
    JobGradeService jobGradeService;

    @Autowired
    GroupService groupService;

    @Override
    public List<Map> jobLevelList(Long groupId) {
        return baseMapper.jobLevelList(groupId);
    }

    @Override
    public void saveVerify(JobLevel jobLevel) {
        StringBuffer buffer = new StringBuffer();
        this.saveVerify(jobLevel, buffer);
        handleErrMsg(buffer.toString());
    }

    @Override
    public void saveVerify(JobLevel jobLevel, StringBuffer buffer) {
        Boolean result = baseMapper.checkDuplicateNameInJobGrade(jobLevel);
        if (result) buffer.append("；该职等下已经有相同名称的职级");
        super.saveVerify(jobLevel, buffer);
    }

    @Override
    public void addEntity(JobLevel jobLevel, Object excelObj) {
        JobLevelBatchVO excel = (JobLevelBatchVO) excelObj;
        StringBuffer buffer = new StringBuffer();
        Group group = groupService.selectByName(excel.getGroupName(), true);
        chkEntityExists(excel.getJobLevelName(), group.getId(), false);
        JobGrade jobGrade = jobGradeService.chkEntityExists(excel.getJobGrade(), group.getId(), true);

        if (!group.getId().equals(jobGrade.getGroupId()))
            buffer.append("根据名称查找的集团与职等所属集团不匹配");

        if (StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());

        jobLevel.setJobGradeId(jobGrade.getId());
        jobLevel.setGroupId(group.getId());
    }

    @Override
    public void updateEntity(JobLevel jobLevel, Object excelObj) {
        JobLevelBatchVO excel = (JobLevelBatchVO) excelObj;
        StringBuffer buffer = new StringBuffer();
        Group group = groupService.selectByName(excel.getGroupName(), true);
        JobLevel tmp = chkEntityExists(excel.getJobLevelName(), group.getId(), true);
        JobGrade jobGrade = jobGradeService.chkEntityExists(excel.getJobGrade(), group.getId(), true);

        if (!group.getId().equals(jobGrade.getGroupId()))
            buffer.append("根据名称查找的集团与职等所属集团不匹配");

        if (StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());

        jobLevel.setJobGradeId(jobGrade.getId());
        jobLevel.setGroupId(group.getId());
        jobLevel.setId(tmp.getId());
    }

}
