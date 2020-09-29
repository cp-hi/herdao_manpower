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
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import net.herdao.hdp.manpower.mpclient.mapper.UserposthistoryMapper;
import net.herdao.hdp.manpower.mpclient.service.UserposthistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 员工岗位历史表
 *
 * @author yangrr
 * @date 2020-09-25 17:24:25
 */
@Service
@AllArgsConstructor
public class UserposthistoryServiceImpl extends ServiceImpl<UserposthistoryMapper, Userposthistory> implements UserposthistoryService {
    private RemoteUserService remoteUserService;

    @Override
    public Page<UserpostDTO> findUserPostHistoryPage(Page<UserpostDTO> page, String orgId, String staffName, String staffCode) {
        Page<UserpostDTO> pageResult = this.baseMapper.findUserPostHistoryPage(page, orgId, staffName, staffCode);
        return pageResult;
    }

    @Override
    public Boolean saveHistory(Userposthistory entity) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        entity.setCreatorCode(userId.toString());

        entity.setCreatedTime(new Date());
        boolean status = super.save(entity);
        return status;
    }

    @Override
    public Boolean updateHistory(Userposthistory entity) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        entity.setModifierCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        entity.setModifiedTime(new Date());
        boolean status = super.updateById(entity);
        return status;
    }

    @Override
    public List<UserpostDTO> findUserPostHistory(String orgId, String staffName, String staffCode) {
        List<UserpostDTO> list = this.baseMapper.findUserPostHistory(orgId, staffName, staffCode);
        return list;
    }
}
