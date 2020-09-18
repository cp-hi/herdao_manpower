package net.herdao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.mpclient.entity.JobGrade;

import java.util.List;
import java.util.Map;

public interface JobGradeService extends IService<JobGrade> {
    List<Map> jobGradeList();

    boolean saveOrUpdate(JobGrade jobGrade)  ;

}
