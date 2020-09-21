
package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.mpclient.entity.OrgSituation;
import net.herdao.hdp.mpclient.mapper.OrgSituationMapper;
import net.herdao.hdp.mpclient.service.OrgSituationService;
import org.springframework.stereotype.Service;

/**
 * 组织概况报表
 * @author andy
 * @date 2020-09-21 15:10:10
 */
@Service
public class OrgSituationServiceImpl extends ServiceImpl<OrgSituationMapper, OrgSituation> implements OrgSituationService {

    @Override
    public OrgSituation fetchOrgSituation(OrgSituation orgSituation) {
        OrgSituation result = this.baseMapper.fetchOrgSituation(orgSituation);
        return result;
    }
}
