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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.BooleanUtil;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.mapper.StaffMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.vo.StaffComponentVo;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationComponentVo;
import net.herdao.hdp.manpower.mpclient.vo.StaffTotalComponentVo;

/**
 * 员工表
 *
 * @author yangrr
 * @date 2020-09-23 18:10:29
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {

	@Override
	public R<?> selectStaffOrganizationComponent(String searchText, String isLikeSearch) {
		
		// 是否模糊查询
		Boolean likeSearch = BooleanUtil.toBoolean(isLikeSearch);

		// 部门/组织员工合计数（包含下级）
		if (likeSearch) {
			// 模糊查询员工信息
			return R.ok(this.selectStaffComponent(searchText, isLikeSearch));
		} else {
			// 员工部门/组织信息
			StaffOrganizationComponentVo organizationComponentVo = this.baseMapper.selectOrganization(searchText);
			// 下級部门/组织信息
			List<StaffOrganizationComponentVo> organizationChildrenVos = this.baseMapper.selectOrganizationChildrens(searchText);
			// 获取部门员工数
			Map<String, Integer> taffTotalComponentMap = this.baseMapper.getStaffTotals(searchText).stream()
					                                         .collect(Collectors.toMap(StaffTotalComponentVo::getOrgCode, StaffTotalComponentVo::getTotal));
			// 设置部门员工数
			organizationChildrenVos.forEach(organizationChildren -> {
				organizationChildren.setStaffTotal(taffTotalComponentMap.get(organizationChildren.getOrgCode()));
			});
			// 设置子部门/组织信息
			organizationComponentVo.setOrganizationComponents(organizationChildrenVos);
			// 设置当前部门数
			organizationComponentVo.setStaffTotal(taffTotalComponentMap.get(organizationComponentVo.getOrgCode()));
			// 员工信息
			organizationComponentVo.setStaffComponents(this.selectStaffComponent(searchText, null));
			
			return R.ok(organizationComponentVo);
		}
	}

	@Override
	public List<StaffComponentVo> selectStaffComponent(String searchText, String isLikeSearch) {
		return this.baseMapper.selectStaffs(searchText, BooleanUtil.toBoolean(isLikeSearch));
	}

}
