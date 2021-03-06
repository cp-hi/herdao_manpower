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

import net.herdao.hdp.manpower.mpclient.dto.comm.UserMsgDTO;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.mapper.UserMapper;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffBasicPositiveVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPage;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author andy
 * @date 2020-09-15 17:59:33
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public Integer getCountUser(String orgCode, Integer isStop) {
		return this.baseMapper.getCountUser(orgCode, isStop);
	}
	
	@Override
	public UserMsgDTO getUserMsg(long id){
		return this.baseMapper.getUserMsg(id);
	}


	/**
	 * 获取用户任职日期
	 *
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public StaffPositiveApprovalPage getUserStartDate(Long userId) {
		return this.baseMapper.getUserStartDate(userId);
	}


}
