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
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffRpDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftrainDTO;
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

    @Override
    public Page<StaffRpDTO> findStaffRpPage(Page<StaffRpDTO> page, String searchText) {
        Page<StaffRpDTO> list = this.baseMapper.findStaffRpPage(page, searchText);
        return list;
    }

    @Override
    public List<StaffRpDTO> findStaffRp(String searchText) {
        List<StaffRpDTO> list = this.baseMapper.findStaffRp(searchText);
        return list;
    }
}
