package net.herdao.hdp.manpower.mpclient.mapper;

import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JobLevelMapper extends EntityMapper<JobLevel> {

    List<Map> jobLevelList(Long groupId);

    @Override
    String getLastEntityCode(JobLevel jobLevel);

    Boolean checkDuplicateNameInJobGrade(JobLevel jobLevel);

}
