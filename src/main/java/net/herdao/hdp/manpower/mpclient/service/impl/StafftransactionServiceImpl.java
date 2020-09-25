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
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.mpclient.mapper.StafftransactionMapper;
import net.herdao.hdp.manpower.mpclient.service.StafftransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 异动情况
 *
 * @author andy
 * @date 2020-09-24 16:00:18
 */
@Service
@AllArgsConstructor
public class StafftransactionServiceImpl extends ServiceImpl<StafftransactionMapper, Stafftransaction> implements StafftransactionService {
    private RemoteUserService remoteUserService;

    @Override
    public Page<Stafftransaction> findStaffTransPage(Page<Stafftransaction> page, String orgId, String staffName, String staffCode) {
        Page<Stafftransaction> pageResult = this.baseMapper.findStaffTransPage(page, orgId, staffName, staffCode);
        return pageResult;
    }

    @Override
    public Boolean saveTrans(Stafftransaction stafftransaction) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        stafftransaction.setCreatorCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        stafftransaction.setCreatedTime(now);
        boolean status = super.save(stafftransaction);
        return status;
    }

    @Override
    public Boolean updateTrans(Stafftransaction stafftransaction) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        stafftransaction.setModifierCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        stafftransaction.setModifiedTime(now);
        boolean status = super.updateById(stafftransaction);
        return status;
    }

    @Override
    public List<Stafftransaction> findStaffTrans(String orgId, String staffName, String staffCode) {
        List<Stafftransaction> list = this.baseMapper.findStaffTrans(orgId, staffName, staffCode);
        return list;
    }
}
