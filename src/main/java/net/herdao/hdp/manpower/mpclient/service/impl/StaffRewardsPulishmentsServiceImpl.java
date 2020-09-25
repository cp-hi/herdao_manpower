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
import net.herdao.hdp.manpower.mpclient.entity.StaffRewardsPulishments;
import net.herdao.hdp.manpower.mpclient.mapper.StaffRewardsPulishmentsMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffRewardsPulishmentsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工奖惩
 *
 * @author andy
 * @date 2020-09-25 16:26:20
 */
@Service
@AllArgsConstructor
public class StaffRewardsPulishmentsServiceImpl extends ServiceImpl<StaffRewardsPulishmentsMapper, StaffRewardsPulishments> implements StaffRewardsPulishmentsService {
    private RemoteUserService remoteUserService;

    @Override
    public Boolean saveStaffRp(StaffRewardsPulishments entity) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        entity.setCreatorCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedTime(now);
        boolean status = super.save(entity);
        return status;
    }

    @Override
    public Boolean updateStaffRp(StaffRewardsPulishments entity) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        entity.setModifierCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        entity.setModifiedTime(now);
        boolean status = super.updateById(entity);
        return status;
    }

    @Override
    public Page<StaffRewardsPulishments> findStaffRpPage(Page<StaffRewardsPulishments> page, String orgId, String staffName, String staffCode) {
        Page<StaffRewardsPulishments> list = this.baseMapper.findStaffRpPage(page, orgId, staffName, staffCode);
        return list;
    }

    @Override
    public List<StaffRewardsPulishments> findStaffRp(String orgId, String staffName, String staffCode) {
        List<StaffRewardsPulishments> list = this.baseMapper.findStaffRp( orgId, staffName, staffCode);
        return list;
    }
}
