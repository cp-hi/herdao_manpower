

package net.herdao.hdp.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.mpclient.entity.OrgSituation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 组织概况报表
 * @author andy
 * @date 2020-09-21 15:10:10
 */
@Mapper
public interface OrgSituationMapper extends BaseMapper<OrgSituation> {
    /**
     * 获取组织概况
     * @param orgSituation
     * @return orgSituation
     */
    OrgSituation fetchOrgSituation(OrgSituation orgSituation);
}
