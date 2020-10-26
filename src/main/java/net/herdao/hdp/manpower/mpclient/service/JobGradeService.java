package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.dto.jobLevel.JobGradeShortDTO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;

import java.util.List;

/**
 * @author ljan
 */
public interface JobGradeService extends  EntityService<JobGrade> {

    List<JobGradeShortDTO> jobGradeList(Long groupId);
}
