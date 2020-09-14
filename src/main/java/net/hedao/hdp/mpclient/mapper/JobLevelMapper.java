package net.hedao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.hedao.hdp.mpclient.entity.JobLevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface JobLevelMapper extends BaseMapper<JobLevel> {
    List<Map> jobLevelList();

    Boolean chkDuplicateJobLevelName(JobLevel jobLevel);

    Boolean chkDuplicateJobLevelCode(JobLevel jobLevel);
}
