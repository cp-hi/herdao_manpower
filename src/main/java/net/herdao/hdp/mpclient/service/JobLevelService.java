package net.herdao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.mpclient.entity.JobLevel;

import java.util.List;
import java.util.Map;

public interface JobLevelService extends IService<JobLevel> {
    List<Map> jobLevelList();

    boolean saveOrUpdate(JobLevel jobLevel);
}
