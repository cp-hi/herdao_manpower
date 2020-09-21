

package net.herdao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.mpclient.entity.OrgSituation;

import java.util.Map;

/**
 * 组织概况报表
 * @author andy
 * @date 2020-09-21 15:10:10
 */
public interface OrgSituationService extends IService<OrgSituation> {
    /**
     * 获取组织概况
     * @param orgSituation
     * @return orgSituation
     */
    OrgSituation fetchOrgSituation(OrgSituation orgSituation);
}
