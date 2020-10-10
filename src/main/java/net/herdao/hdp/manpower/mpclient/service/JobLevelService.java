package net.herdao.hdp.manpower.mpclient.service;

import com.alibaba.excel.converters.Converter;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;

import java.util.List;
import java.util.Map;

public interface JobLevelService extends EntityService<JobLevel> {

    List<Map> jobLevelList(Long groupId);

    @Override
    boolean saveOrUpdate(JobLevel jobLevel);
}
