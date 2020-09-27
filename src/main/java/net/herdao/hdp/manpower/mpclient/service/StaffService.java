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

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.vo.StaffComponentVo;

/**
 * 员工表
 *
 * @author yangrr
 * @date 2020-09-23 18:10:29
 */
public interface StaffService extends IService<Staff> {
	
	/**
	 * 查询员工信息（返回结果包含员工所在部门/组织信息）组件
	 * @param searchText 查询内容
	 * @param isLikeSearch 是否为模糊查询， 值：“1” 为 true, 不传或者传其它值 为 false
	 * @return
	 */
	public R<?> selectStaffOrganizationComponent(String searchText, String isLikeSearch);
	
	/**
	 * 查询员工信息组件
	 * 
	 * @param searchText 查询内容
	 * @param isLikeSearch 是否模糊查询， 值：“1” 为  true, 值：0 或者 null 为  false
	 * @return
	 */
	public List<StaffComponentVo> selectStaffComponent(String searchText, String isLikeSearch);

}
