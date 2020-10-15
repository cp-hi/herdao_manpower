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
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.mapper.StafftrainMapper;
import net.herdao.hdp.manpower.mpclient.service.StafftrainService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Service
@AllArgsConstructor
public class StafftrainServiceImpl extends ServiceImpl<StafftrainMapper, Stafftrain> implements StafftrainService {
    private RemoteUserService remoteUserService;

    @Override
    public Page<Stafftrain> findStaffTrainPage(Page<Stafftrain> page, String orgId, String staffName, String staffCode) {
        Page<Stafftrain> list = this.baseMapper.findStaffTrainPage(page, orgId, staffName, staffCode);
        return list;
    }

    @Override
    public boolean saveTrain(Stafftrain stafftrain) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        stafftrain.setCreatorCode(userId.toString());
        stafftrain.setCreatedTime(new Date());
        boolean status = super.save(stafftrain);
        return status;
    }

    @Override
    public boolean updateTrain(Stafftrain stafftrain) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        stafftrain.setModifierCode(userId.toString());
        stafftrain.setModifiedTime(new Date());
        boolean status = super.updateById(stafftrain);
        return status;
    }

    @Override
    public List<Stafftrain> findStaffTrain(String orgId, String staffName, String staffCode) {
        List<Stafftrain> list = this.baseMapper.findStaffTrain(orgId, staffName, staffCode);
        return list;
    }
}
