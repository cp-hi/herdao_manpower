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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffPracticeDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPractice;
import net.herdao.hdp.manpower.mpclient.mapper.StaffPracticeMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffPracticeService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 员工实习记录
 * @author andy
 * @date 2020-10-09 17:51:16
 */
@Service
public class StaffPracticeServiceImpl extends ServiceImpl<StaffPracticeMapper, StaffPractice> implements StaffPracticeService {

    @Autowired
    private RemoteUserService remoteUserService;
    
    @Override
    @OperationEntity(operation = "新增或修改员工实习记录", clazz = StaffPractice.class)
    public boolean saveOrUpdate(StaffPracticeDTO staffPracticeDTO) {
    	UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        Integer userId = userInfo.getSysUser().getUserId().intValue();
        LocalDateTime now = LocalDateTime.now();
        StaffPractice staffPractice;
        
        //设置实习期
        if (null != staffPracticeDTO.getBeginDate() && null !=  staffPracticeDTO.getEndDate()){
            String dayAndMonth = DateUtils.getDayAndMonth(DateUtils.formatDate(staffPracticeDTO.getBeginDate(),"yyyy-MM-dd"), DateUtils.formatDate( staffPracticeDTO.getEndDate(),"yyyy-MM-dd")  );
            staffPracticeDTO.setPeriod(dayAndMonth);
        }

        //如果id为null，新增数据
        if(staffPracticeDTO.getId()==null){
        	staffPractice = new StaffPractice();
        	BeanUtils.copyProperties(staffPracticeDTO, staffPractice);
        	
        	staffPractice.setCreatorId(userId.toString());
        	staffPractice.setCreatedTime(now);
        	staffPractice.setModifierId(userId.toString());
        	staffPractice.setModifiedTime(now);
        }
        else{
        	staffPractice = this.getById(staffPracticeDTO.getId());
        	BeanUtils.copyProperties(staffPracticeDTO, staffPractice);
        	staffPractice.setModifierId(userId.toString());
        	staffPractice.setModifiedTime(now);
        }
        
        boolean status = super.saveOrUpdate(staffPractice);
        return status;
    }

    @Override
    public StaffPracticeDTO findStaffPractice(Long staffId) {
        StaffPracticeDTO result = this.baseMapper.findStaffPractice(staffId);
        return result;
    }
}
