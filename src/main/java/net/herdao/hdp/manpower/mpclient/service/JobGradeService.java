package net.herdao.hdp.manpower.mpclient.service;

import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobGradeShortVO;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;

/**
 * @author ljan
 */
public interface JobGradeService extends  EntityService<JobGrade> {

    List<JobGradeShortVO> jobGradeList(Long groupId);
    
    boolean removeById(Long id);
}
