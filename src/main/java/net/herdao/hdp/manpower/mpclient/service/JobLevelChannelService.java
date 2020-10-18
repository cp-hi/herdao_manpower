package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface JobLevelChannelService extends IService<JobLevelChannel> {
    List<Map> jobLevelChannelList();

}
