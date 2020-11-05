

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.JobLevelReport;
import net.herdao.hdp.manpower.mpclient.entity.OrgReport;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrgReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 组织报表
 *
 * @author andy
 * @date 2020-09-22 10:13:05
 */
@Mapper
public interface OrgReportMapper extends BaseMapper<OrgReport> {
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
    List<OrgReport> exportDetailsOrg(OrgReport condition);

    /**
     * 组织职级统计下载
     * @param condition
     * @return
     */
    List<JobLevelReport> exportOrgJobLevel(JobLevelReport condition);

    /**
     * 组织职级统计
     * @param condition
     * @return
     */
    List<JobLevelReport> fetchOrgJobLevel(JobLevelReport condition);


}
