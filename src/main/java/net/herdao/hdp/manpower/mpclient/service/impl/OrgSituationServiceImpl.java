
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.OrgSituation;
import net.herdao.hdp.manpower.mpclient.mapper.OrgSituationMapper;
import net.herdao.hdp.manpower.mpclient.service.OrgSituationService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public OrgSituation fetchOrgSituationByJobType(OrgSituation orgSituation) {
        OrgSituation result = this.baseMapper.fetchOrgSituationByJobType(orgSituation);
        return result;
    }

    @Override
    public OrgSituation fetchOrgSituationByAge(OrgSituation orgSituation) {
        OrgSituation result = this.baseMapper.fetchOrgSituationByAge(orgSituation);
        return result;
    }

    @Override
    public List<OrgSituation> fetchOrgSituationByPostCode(OrgSituation orgSituation) {
        List<OrgSituation> list = this.baseMapper.fetchOrgSituationByPostCode(orgSituation);
        return list;
    }
}
