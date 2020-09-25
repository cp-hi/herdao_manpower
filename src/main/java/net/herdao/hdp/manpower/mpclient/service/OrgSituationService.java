

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.OrgSituation;

import java.util.List;

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


    /**
     * 获取员工任职类型分布
     * @param orgSituation
     * @return orgSituation
     */
    OrgSituation fetchOrgSituationByJobType(OrgSituation orgSituation);

    /**
     * 获取在职员工年龄分布
     * @param orgSituation
     * @return orgSituation
     */
    OrgSituation fetchOrgSituationByAge(OrgSituation orgSituation);


    /**
     * 获取岗位人数分布
     * @param orgSituation
     * @return orgSituation
     */
    List<OrgSituation> fetchOrgSituationByPostCode(OrgSituation orgSituation);
}
