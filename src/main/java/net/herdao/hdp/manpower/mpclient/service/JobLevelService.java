package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.entity.JobLevel;

import java.util.List;
import java.util.Map;

public interface JobLevelService extends  EntityService<JobLevel> {
    List<Map> jobLevelList(Long groupId);

    public void validityCheck(Long id, String msg) throws Exception;
}
