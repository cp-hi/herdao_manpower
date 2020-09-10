/*
 *    Copyright (c) 2018-2025, hdp All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: hdp
 */
package net.herdao.hdp.hdpclientdemo.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.hdpclientdemo.oa.entity.Organization;
import net.herdao.hdp.hdpclientdemo.oa.mapper.OrganizationMapper;
import net.herdao.hdp.hdpclientdemo.oa.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Override
    public List<Organization> selectOrganizationListByParentOid(String parentOid) {
        List<Organization> list = this.baseMapper.selectOrganizationListByParentOid(parentOid);
        return list;
    }

    @Override
    public List<Organization> findAllOrganizations() {
        List<Organization> list = this.baseMapper.findAllOrganizations();
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
    public List<Organization> selectOrganByCurrentOrgOid(String keyword) {
        List<Organization> list = this.baseMapper.selectOrganByCurrentOrgOid(keyword);
        return list;
    }
}
