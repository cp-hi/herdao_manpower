

package net.herdao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.mpclient.entity.OrgReport;
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
    List<OrgReport> findOrgReportView(OrgReport condition);

    /**
     * 组织架构表下载
     * @param condition
     * @return
     */
    List<OrgReport> exportOrg(OrgReport condition);

}
