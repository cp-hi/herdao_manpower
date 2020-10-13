package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.entity.JobGrade;

import java.util.List;
import java.util.Map;

/**
 * @author ljan
 */
public interface JobGradeService extends EntityService<JobGrade> {
    List<Map> jobGradeList();
}
