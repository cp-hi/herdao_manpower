package com.hedao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hedao.hdp.mpclient.entity.JobLevel;
import com.hedao.hdp.mpclient.mapper.JobLevelMapper;
import com.hedao.hdp.mpclient.service.JobLevelService;
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
public class JobLevelServiceImpl extends ServiceImpl<JobLevelMapper, JobLevel> implements JobLevelService {

    public List<Map> jobLevelList() {
        return baseMapper.jobLevelList();
    }

    public Page page(Page page, Map<String, String> params) {
        return null;
    }

    public void addOrUpdate(JobLevel jobLevel) throws Exception {
        if (baseMapper.chkDuplicateJobLevelCode(jobLevel))
            throw new Exception("职级编码重复了");
        if (baseMapper.chkDuplicateJobLevelName(jobLevel))
            throw new Exception("职级名称重复了");
        if (null == jobLevel.getId()) {
            this.save(jobLevel);
        } else {
            this.updateById(jobLevel);
        }
    }
}
