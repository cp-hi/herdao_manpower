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
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.mapper.StaffcontractMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffcontractService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工合同签订
 *
 * @author liang
 * @date 2020-09-27 09:15:28
 */
@Service
@AllArgsConstructor
public class StaffcontractServiceImpl extends ServiceImpl<StaffcontractMapper, Staffcontract> implements StaffcontractService {
    private RemoteUserService remoteUserService;

    @Override
    public Page<Staffcontract> findStaffContractPage(Page<Staffcontract> page, String orgId, String staffName, String staffCode) {
        Page<Staffcontract> list = this.baseMapper.findStaffContractPage(page, orgId, staffName, staffCode);
        return list;
    }

    @Override
    public List<Staffcontract> findStaffContract(String orgId, String staffName, String staffCode) {
        List<Staffcontract> list = this.baseMapper.findStaffContract(orgId, staffName, staffCode);
        return list;
    }

    @Override
    public Boolean saveContract(Staffcontract staffcontract) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        staffcontract.setCreatorCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        staffcontract.setCreatedTime(now);
        boolean status = super.save(staffcontract);
        return status;
    }

    @Override
    public boolean UpateContract(Staffcontract staffcontract) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        staffcontract.setModifierCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        staffcontract.setModifiedTime(now);
        boolean status = super.updateById(staffcontract);
        return status;
    }
}