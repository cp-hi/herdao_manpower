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

import net.herdao.hdp.manpower.mpclient.dto.staff.StaffProTitleDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.StaffProTitle;
import net.herdao.hdp.manpower.mpclient.mapper.StaffProTitleMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffProTitleService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工职称
 *
 * @author andy
 * @date 2020-10-10 16:37:15
 */
@Service
public class StaffProTitleServiceImpl extends ServiceImpl<StaffProTitleMapper, StaffProTitle> implements StaffProTitleService {

    @Override
    public Page<StaffProTitle> findStaffProTitlePage(Page<StaffProTitle> page, String staffId) {
        Page<StaffProTitle> pageResult = this.baseMapper.findStaffProTitlePage(page, staffId);
        return pageResult;
    }

    @Override
    @OperationEntity(operation = "新增或修改员工职称", clazz = StaffProTitle.class)
    public boolean saveOrUpdate(StaffProTitle entity) {
        boolean status = super.saveOrUpdate(entity);
        return status;
    }
    
    @Override
    public List<StaffProTitleDTO> findStaffProTitleDTO(Long staffid){
    	List<StaffProTitleDTO> staffProTitleDTO = this.baseMapper.findStaffProTitleDTO(staffid);
    	return staffProTitleDTO;
    }
}
