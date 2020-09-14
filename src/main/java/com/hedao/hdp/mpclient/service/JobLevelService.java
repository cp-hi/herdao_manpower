package com.hedao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hedao.hdp.mpclient.entity.JobLevel;

import java.util.List;
import java.util.Map;

public interface JobLevelService extends IService<JobLevel> {
    List<Map> jobLevelList();

    Page page(Page page, Map<String, String> params);

    void addOrUpdate(JobLevel jobLevel) throws Exception;

}
