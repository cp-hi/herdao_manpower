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
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.mapper.StaffMapper;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
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
@AllArgsConstructor
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {
	
	private final RemoteUserService remoteUserService;
	
	private final OrganizationService organizationService;
	
	@Override
	public R<?> selectStaffOrganizationComponent(String searchText, String isLikeSearch) {
		
		// 是否模糊查询
		Boolean likeSearch = BooleanUtil.toBoolean(isLikeSearch);

		// 部门/组织员工合计数（包含下级）
		if (likeSearch) {
			// 模糊查询员工信息
			return R.ok(this.selectStaffComponent(searchText, isLikeSearch));
		} else {
			
			if(StrUtil.isEmpty(searchText)) {
				// 获取用户信息
		        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
		        if(userInfo != null) {
		        	// 获取当前人员部门信息
		        	Organization organization = organizationService.getById(userInfo.getSysUser().getDeptId());
		        	// 默认查询当前人员所在部门
		        	searchText = organization.getOrgCode();
		        }
			}
			
			// 员工部门/组织信息
			StaffOrganizationComponentVo organizationComponentVo = this.baseMapper.selectOrganization(searchText);
			if(organizationComponentVo == null) {
				return R.failed("没有符合查询条件的部门/组织信息！");
			}
			// 下級部门/组织信息
			List<StaffOrganizationComponentVo> organizationChildrenVos = this.baseMapper.selectOrganizationChildrens(searchText);
			// 获取部门员工数
			Map<String, Integer> taffTotalComponentMap = this.baseMapper.getStaffTotals(searchText).stream()
					                                         .collect(Collectors.toMap(StaffTotalComponentVo::getOrgCode, StaffTotalComponentVo::getTotal));
			// 子部门/组织员工数
			organizationChildrenVos.forEach(organizationChildren -> {
				organizationChildren.setStaffTotal(taffTotalComponentMap.get(organizationChildren.getOrgCode()));
			});
			// 子部门/组织信息
			organizationComponentVo.setOrganizationComponents(organizationChildrenVos);
			
			Integer staffTotal = NumberUtil.parseInt(StrUtil.toString(taffTotalComponentMap.get(organizationComponentVo.getOrgCode())));
			
			// 当前部门/组织员工数
			organizationComponentVo.setStaffTotal(staffTotal);
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
