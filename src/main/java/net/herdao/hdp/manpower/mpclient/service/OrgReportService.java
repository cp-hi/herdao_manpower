
package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.JobLevelReport;
import net.herdao.hdp.manpower.mpclient.entity.OrgReport;

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
    List<OrgReport> findOrgReportView(OrgReport condition);

    /**
     * 组织架构表下载
     * @param condition
     * @return
     */
    List<OrgReport> exportOrg(OrgReport condition);

    /**
     * 组织架构表明细下载
     * @param condition
     * @return
     */
    List<OrgReport> exportDetailsOrg(OrgReport condition);

    /**
     * 组织职级统计下载
     * @param condition
     * @return
     */
    List<JobLevel> exportOrgJobLevel(JobLevelReport condition);


}
