package net.herdao.hdp.manpower.mpclient.service.impl;

import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.dto.JobLevelDTO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.mapper.JobLevelMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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
@Validated
@AllArgsConstructor
public class JobLevelServiceImpl extends ServiceImpl<JobLevelMapper, JobLevel> implements JobLevelService {

    GroupService groupService;

    @Override
    public List<Map> jobLevelList(Long groupId) {
        return baseMapper.jobLevelList(groupId);
    }

    @Override
    public boolean saveOrUpdate(JobLevel jobLevel) {
        saveVerify(jobLevel);
        return super.saveOrUpdate(jobLevel);
    }

    @Override
    @Validated
    public void saveVerify(@Valid JobLevel jobLevel) {
        if (baseMapper.chkCodeAndName(jobLevel))
            throw new RuntimeException("请检查职级的名称和编码");
        if (baseMapper.chkDuplicateJobLevelCode(jobLevel))
            throw new RuntimeException("职级编码重复了");
        if (baseMapper.chkDuplicateJobLevelName(jobLevel))
            throw new RuntimeException("职级名称重复了");
    }

    @Override
    public void importVerify(@Valid JobLevel jobLevel) {
        this.saveVerify(jobLevel);

        //TODO 添加校验方法
        JobLevelDTO dto = (JobLevelDTO) jobLevel;

        JobLevel tmp = this.getOne(new QueryWrapper<JobLevel>()
                .eq("JOB_LEVEL_CODE", dto.getJobLevelCode())
                .eq("JOB_LEVEL_NAME", dto.getJobLevelName()));
        Group group = groupService.getOne(new QueryWrapper<Group>()
                .eq("GROUP_NAME", dto.getGroup()));

        if (null == group)
            throw new RuntimeException("查不到此集团：" + dto.getGroup());

        if (null != tmp) {
            if (tmp.getGroupId().equals(group.getId()))
                throw new RuntimeException("导入的集团与原先保存的集团不一致");
            jobLevel.setId(tmp.getId());
        }
        dto.setGroupId(group.getId());
        if (null == dto.getGroupId())
            throw new RuntimeException("集团ID为空");
//        System.out.println(jobLevel);
    }
}
