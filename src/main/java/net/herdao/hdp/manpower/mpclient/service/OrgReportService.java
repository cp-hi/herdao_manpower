
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.JobLevelReport;
import net.herdao.hdp.manpower.mpclient.entity.OrgReport;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrgReportDetailVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrgReportVO;

import java.util.List;

/**
 * 组织报表
 *
 * @author andy
 * @date 2020-09-22 10:13:05
 */
public interface OrgReportService extends IService<OrgReport> {
    /**
     * 组织架构预览
     * @param condition
     * @return
     */
    List<OrgReportVO> findOrgReportView(OrgReport condition);

    /**
     * 组织架构表下载
     * @param condition
     * @return
     */
    List<OrgReportVO> exportOrg(OrgReport condition);

    /**
     * 组织架构表明细下载
     * @param condition
     * @return
     */
    List<OrgReportDetailVO> exportDetailsOrg(OrgReport condition);

    /**
     * 组织职级统计下载
     * @param condition
     * @return
     */
    List<JobLevelReport > exportOrgJobLevel(JobLevelReport condition);

    /**
     * 组织职级统计
     * @param condition
     * @return
     */
    List<JobLevelReport> fetchOrgJobLevel(JobLevelReport condition);

    /**
     * 组织架构表明细预览
     * @param condition
     * @return
     */
    List<OrgReportDetailVO> findOrgDetailsView(OrgReport condition);
}
