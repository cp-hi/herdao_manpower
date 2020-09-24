
package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.mpclient.entity.JobLevel;
import net.herdao.hdp.mpclient.entity.JobLevelReport;
import net.herdao.hdp.mpclient.entity.OrgReport;
import net.herdao.hdp.mpclient.mapper.OrgReportMapper;
import net.herdao.hdp.mpclient.service.OrgReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织报表
 * @author andy
 * @date 2020-09-22 10:13:05
 */
@Service
public class OrgReportServiceImpl extends ServiceImpl<OrgReportMapper, OrgReport> implements OrgReportService {

    @Override
    public List<OrgReport> findOrgReportView(OrgReport condition) {
        List<OrgReport> list = this.baseMapper.findOrgReportView(condition);
        return list;
    }

    @Override
    public List<OrgReport> exportOrg(OrgReport condition) {
        List<OrgReport> list = this.baseMapper.exportOrg(condition);
        return list;
    }

    @Override
    public List<OrgReport> exportDetailsOrg(OrgReport condition) {
        List<OrgReport> list = this.baseMapper.exportDetailsOrg(condition);
        return list;
    }

    @Override
    public List<JobLevel> exportOrgJobLevel(JobLevelReport condition) {
        List<JobLevel> list = this.baseMapper.exportOrgJobLevel(condition);
        return list;
    }
}