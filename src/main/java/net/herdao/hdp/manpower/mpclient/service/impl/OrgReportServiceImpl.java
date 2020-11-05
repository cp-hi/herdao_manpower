
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.JobLevelReport;
import net.herdao.hdp.manpower.mpclient.entity.OrgReport;
import net.herdao.hdp.manpower.mpclient.mapper.OrgReportMapper;
import net.herdao.hdp.manpower.mpclient.service.OrgReportService;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrgReportDetailVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrgReportVO;
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
    public List<OrgReportVO> findOrgReportView(OrgReport condition) {
        List<OrgReportVO> list = this.baseMapper.findOrgReportView(condition);
        return list;
    }

    @Override
    public List<OrgReportVO> exportOrg(OrgReport condition) {
        List<OrgReportVO> list = this.baseMapper.exportOrg(condition);
        return list;
    }

    @Override
    public List<OrgReportDetailVO> exportDetailsOrg(OrgReport condition) {
        List<OrgReportDetailVO> list = this.baseMapper.exportDetailsOrg(condition);
        return list;
    }

    @Override
    public List<JobLevelReport> exportOrgJobLevel(JobLevelReport condition) {
        List<JobLevelReport > list = this.baseMapper.exportOrgJobLevel(condition);
        return list;
    }

    @Override
    public List<JobLevelReport> fetchOrgJobLevel(JobLevelReport condition) {
        List<JobLevelReport> list = this.baseMapper.fetchOrgJobLevel(condition);
        return list;
    }

    @Override
    public List<OrgReportDetailVO> findOrgDetailsView(OrgReport condition) {
        List<OrgReportDetailVO> list = this.baseMapper.findOrgDetailsView(condition);
        return list;
    }
}
