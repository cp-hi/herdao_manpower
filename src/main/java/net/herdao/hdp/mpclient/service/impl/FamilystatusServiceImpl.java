
package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.mpclient.entity.Familystatus;
import net.herdao.hdp.mpclient.entity.Organization;
import net.herdao.hdp.mpclient.mapper.FamilystatusMapper;
import net.herdao.hdp.mpclient.service.FamilystatusService;
import org.springframework.stereotype.Service;

/**
 * 员工家庭成员
 *
 * @author liang
 * @date 2020-09-23 10:53:08
 */
@Service
public class FamilystatusServiceImpl extends ServiceImpl<FamilystatusMapper, Familystatus> implements FamilystatusService {

    @Override
    public Page<Organization> findFamilyStatusPage(Page<Familystatus> page, String orgId, String staffName, String staffCode) {
        Page<Organization> pageResult = this.baseMapper.findFamilyStatusPage(page, orgId,staffName,staffCode);
        return pageResult;
    }
}
