
package net.hedao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.hedao.hdp.mpclient.entity.Organization;
import net.hedao.hdp.mpclient.mapper.OrganizationMapper;
import net.hedao.hdp.mpclient.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Override
    public List<Organization> selectOrganizationListByParentOid(String parentId) {
        List<Organization> list = this.baseMapper.selectOrganizationListByParentOid(parentId);
        return list;
    }

    @Override
    public List<Organization> findAllOrganizations(Organization condition) {
        List<Organization> list = this.baseMapper.findAllOrganizations(condition);
        return list;
    }

    @Override
    public List<Organization> findOrganizationByCondition(Organization condition) {
        List<Organization> list = this.baseMapper.findOrganizationByCondition(condition);
        return list;
    }

    @Override
    public List<Organization> findOrganizationByKeyWords(String keyword) {
        List<Organization> list = this.baseMapper.findOrganizationByKeyWords(keyword);
        return list;
    }

    @Override
    public List<Organization> selectOrganByCurrentOrgOid(String id) {
        List<Organization> list = this.baseMapper.selectOrganByCurrentOrgOid(id);
        return list;
    }

    @Override
    public List<Organization> findRootOrganizations(Organization condition) {
        List<Organization> list = this.baseMapper.findRootOrganizations(condition);
        return list;
    }

    @Override
    public void stopOrganById(Long id) {
        this.baseMapper.stopOrganById(id);
    }

    @Override
    public void startOrganById(Long id) {
        this.baseMapper.startOrganById(id);
    }

    @Override
    public List<Organization> findOrgPage(Organization condition) {
        List<Organization> list = this.baseMapper.findOrgPage(condition);
        return list;
    }


}
