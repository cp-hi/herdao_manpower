package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.JobLevelChannel;

import java.util.List;
import java.util.Map;

public interface JobLevelChannelService extends EntityService<JobLevelChannel> {

    List<Map> jobLevelChannelList();

    @Override
    boolean saveOrUpdate(JobLevelChannel jobLevelChannel);
}
