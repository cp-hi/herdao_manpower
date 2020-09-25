package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;

import java.util.List;
import java.util.Map;

/**
 * @author ljan
 */
public interface JobGradeService extends IService<JobGrade> {
    List<Map> jobGradeList();

    @Override
    boolean saveOrUpdate(JobGrade jobGrade) ;

}
