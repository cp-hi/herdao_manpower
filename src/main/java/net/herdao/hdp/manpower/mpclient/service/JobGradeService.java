package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.JobGradeShortDTO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;

import java.util.List;
import java.util.Map;

/**
 * @author ljan
 */
public interface JobGradeService extends  EntityService<JobGrade> {

    List<JobGradeShortDTO> jobGradeList(Long groupId);
}
