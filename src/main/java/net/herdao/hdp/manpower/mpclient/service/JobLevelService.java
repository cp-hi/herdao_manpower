package net.herdao.hdp.manpower.mpclient.service;

import com.alibaba.excel.converters.Converter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;

import java.util.List;
import java.util.Map;

public interface JobLevelService extends NewEntityService<JobLevel> {


    List<Map> jobLevelList(Long groupId);
}
