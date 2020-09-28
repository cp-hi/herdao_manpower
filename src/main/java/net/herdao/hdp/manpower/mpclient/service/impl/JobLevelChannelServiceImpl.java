package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.JobLevelChannel;
import net.herdao.hdp.manpower.mpclient.mapper.JobLevelChannelMapper;
import net.herdao.hdp.manpower.mpclient.mapper.JobLevelMapper;
import net.herdao.hdp.manpower.mpclient.service.JobLevelChannelService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName JobLevelChannelServiceImpl
 * @Description JobLevelChannelServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/27 10:38
 * @Version 1.0
 */
@Service
public class JobLevelChannelServiceImpl extends ServiceImpl<JobLevelChannelMapper, JobLevelChannel> implements JobLevelChannelService {
    @Override
    public List<Map> jobLevelChannelList() {
        return baseMapper.jobLevelChannelList();
    }


    @Override
    public boolean saveOrUpdate(JobLevelChannel jobLevelChannel) {
        if (baseMapper.chkDuplicateJobLevelChannelCode(jobLevelChannel))
            throw new RuntimeException("职级通道编码重复了");
        if (baseMapper.chkDuplicateJobLevelChannelName(jobLevelChannel))
            throw new RuntimeException("职级通道名称重复了");
        return super.saveOrUpdate(jobLevelChannel);
    }
}
