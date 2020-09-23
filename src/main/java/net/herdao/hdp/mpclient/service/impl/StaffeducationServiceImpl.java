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
package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.mpclient.entity.Familystatus;
import net.herdao.hdp.mpclient.entity.Organization;
import net.herdao.hdp.mpclient.entity.Staffeducation;
import net.herdao.hdp.mpclient.mapper.StaffeducationMapper;
import net.herdao.hdp.mpclient.service.StaffeducationService;
import org.springframework.stereotype.Service;

/**
 * 员工教育经历
 *
 * @author andy
 * @date 2020-09-23 17:22:28
 */
@Service
public class StaffeducationServiceImpl extends ServiceImpl<StaffeducationMapper, Staffeducation> implements StaffeducationService {

    @Override
    public Page<Organization> findStaffEducationPage(Page<Familystatus> page, String orgId, String staffName, String staffCode) {
        Page<Organization> pageResult = this.baseMapper.findStaffEducationPage(page, orgId, staffName, staffCode);
        return pageResult;
    }
}
