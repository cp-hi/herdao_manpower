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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffDetailDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staff;

import java.util.Map;

/**
 * 员工表
 *
 * @author yangrr
 * @date 2020-09-23 18:10:29
 */
public interface StaffService extends IService<Staff> {

	/**
 	 * 查询员工信息
 	 * 
 	 * @return
 	 */
	public R<?> selectStaffOrganizationComponent();

	Map<String, Object> queryCount();

	IPage staffPage(Page page, Staff staff, String searchText);

	boolean staffSave(StaffDetailDTO staffForm);

	boolean staffUpdate(StaffDetailDTO staffForm);

	StaffDetailDTO getStaffById(Long id);

	Map<String, Object> getStaffDetail(Long id);

}
