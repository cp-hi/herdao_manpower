package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobGradeShortVO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;

import java.util.List;

/**
 * @author ljan
 */
public interface JobGradeService extends  EntityService<JobGrade> {

    List<JobGradeShortVO> jobGradeList(Long groupId);
}
