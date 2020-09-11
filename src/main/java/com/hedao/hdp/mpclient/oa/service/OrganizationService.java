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

package com.hedao.hdp.mpclient.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hedao.hdp.mpclient.oa.entity.Organization;

import java.util.List;

/**
 * 
 *
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 查询子组织架构
     * @param parentOid
     * @return
     */
     List<Organization> selectOrganizationListByParentOid(String parentOid);

    /**
     * 查询根组织架构
     * @return
     */
    List<Organization> findAllOrganizations();

    /**
     * 高级查询根组织架构
     * @param condition
     * @return
     */
    List<Organization> findOrganizationByCondition(Organization condition);

    /**
     * 关键字查询根组织架构
     * @param keyword
     * @return
     */
    List<Organization> findOrganizationByKeyWords(String keyword);

    /**
     * 关键字查询根组织架构
     * @param orgOid
     * @return
     */
    List<Organization>  selectOrganByCurrentOrgOid(String orgOid);
}
