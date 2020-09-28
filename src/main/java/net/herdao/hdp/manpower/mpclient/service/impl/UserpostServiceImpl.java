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
import net.herdao.hdp.manpower.mpclient.dto.UserpostNow;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import net.herdao.hdp.manpower.mpclient.mapper.UserpostMapper;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author andy
 * @date 2020-09-15 08:57:53
 */
@Service
@AllArgsConstructor
public class UserpostServiceImpl extends ServiceImpl<UserpostMapper, Userpost> implements UserpostService {
    private RemoteUserService remoteUserService;

    @Override
    public List<Userpost> findUserPost(Userpost condition) {
        List<Userpost> list = this.baseMapper.findUserPost(condition);
        return list;
    }

    @Override
    public Page<UserpostNow> findUserPostNowPage(Page<UserpostNow> page, String orgId, String staffName, String staffCode) {
        Page<UserpostNow> pageResult = this.baseMapper.findUserPostNowPage(page, orgId, staffName, staffCode);
        return pageResult;
    }

    @Override
    public List<UserpostNow> findUserPostNow(String orgId, String staffName, String staffCode) {
        List<UserpostNow> list = this.baseMapper.findUserPostNow(orgId, staffName, staffCode);
        return list;
    }

    @Override
    public Boolean saveUserPostNow(Userpost entity) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        entity.setCreatorCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedTime(now);
        boolean status = super.save(entity);
        return status;
    }

    @Override
    public Boolean updateUserPostNow(Userpost entity) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        entity.setModifierCode(userId.toString());
        LocalDateTime now = LocalDateTime.now();
        entity.setModifiedTime(now);
        boolean status = super.updateById(entity);
        return status;
    }
}
