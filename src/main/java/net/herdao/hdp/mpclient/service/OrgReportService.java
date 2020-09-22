
package net.herdao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.mpclient.entity.OrgReport;
import net.herdao.hdp.mpclient.entity.Organization;

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
}
