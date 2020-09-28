package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.JobLevelChannel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface JobLevelChannelMapper extends BaseMapper<JobLevelChannel> {

    List<Map> jobLevelChannelList();

    Boolean chkDuplicateJobLevelChannelName(JobLevelChannel jobLevelChannel);

    Boolean chkDuplicateJobLevelChannelCode(JobLevelChannel jobLevelChannel);
}
