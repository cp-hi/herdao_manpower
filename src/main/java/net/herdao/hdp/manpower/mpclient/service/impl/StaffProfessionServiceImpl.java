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
import net.herdao.hdp.manpower.mpclient.dto.StaffProDto;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.StaffProfession;
import net.herdao.hdp.manpower.mpclient.mapper.StaffProfessionMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffProfessionService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.stereotype.Service;

/**
 * 员工职称及职业资料
 *
 * @author andy
 * @date 2020-10-09 10:53:38
 */
@Service
public class StaffProfessionServiceImpl extends ServiceImpl<StaffProfessionMapper, StaffProfession> implements StaffProfessionService {

    @Override
    public Page<StaffProDto> findStaffProPage(Page<StaffProDto> page, String orgId, String staffName, String staffCode) {
        Page<StaffProDto> pageResult = this.baseMapper.findStaffProPage(page, orgId, staffName, staffCode);
        return pageResult;
    }

    @Override
    @OperationEntity(operation = "保存或修改员工职称及职业资料", clazz = StaffProfession.class)
    public boolean saveOrUpdate(StaffProfession profession) {
        boolean status = super.saveOrUpdate(profession);
        return status;
    }
}
