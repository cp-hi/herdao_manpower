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

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;

import net.herdao.hdp.manpower.mpclient.dto.comm.UserMsgDTO;
import net.herdao.hdp.manpower.mpclient.entity.User;

/**
 * 
 *
 * @author andy
 * @date 2020-09-15 17:59:33
 */
public interface UserService extends IService<User> {
	
	/**
	 * 统计组织员工数
	 * 
	 * @param orgCode 组织code
	 * @param isStop 是否停用 值：1 停用， 值：0 启用
	 * @return
	 */
    Integer getCountUser(String orgCode, Integer isStop);

    /**
	 * 获取员工信息
	 * 
	 * @param id 用户id
	 * @return
	 */
    UserMsgDTO getUserMsg(long id);
}
