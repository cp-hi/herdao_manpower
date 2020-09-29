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

import cn.hutool.core.util.ObjectUtil;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.mapper.StaffMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
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
	public R<?> selectStaffOrganizationComponent() {
		
		// 待接入用户权限 TODO
		String searchText = "";
		
		List<StaffOrganizationComponentVo> organizations = this.baseMapper.selectOrganizations(searchText);
		
		// 获取部门员工数
		Map<String, Integer> taffTotalComponentMap = this.baseMapper.getStaffTotals().stream()
				                                         .collect(Collectors.toMap(StaffTotalComponentVo::getOrgCode, StaffTotalComponentVo::getTotal));
		// 部门/组织员工数
		organizations.forEach(organization -> {
			Integer staffTotal = sumKeyLike(taffTotalComponentMap, organization.getOrgCode());
			organization.setStaffTotal(staffTotal);
		});
				
		organizations.forEach(organization -> {
			List<StaffOrganizationComponentVo> staffOrganizationComponents = organization.getStaffOrganizationComponents();
			if(ObjectUtil.isNotEmpty(staffOrganizationComponents)) {
				recursionOrganization(staffOrganizationComponents, taffTotalComponentMap);
			}
		});
		
		return R.ok(organizations);
	}
	
	/**
	 * 递归合计部门/组织人员数
	 * @param staffOrganizationComponents
	 * @param taffTotalComponentMap
	 */
	public void recursionOrganization(List<StaffOrganizationComponentVo> staffOrganizationComponents, Map<String, Integer> taffTotalComponentMap) {
		if(ObjectUtil.isNotEmpty(staffOrganizationComponents)) {
			staffOrganizationComponents.forEach(organizationChildren ->{
				// 子部门/组织员工数
				Integer staffTotal = sumKeyLike(taffTotalComponentMap, organizationChildren.getOrgCode());
				organizationChildren.setStaffTotal(staffTotal);
				recursionOrganization(organizationChildren.getStaffOrganizationComponents(), taffTotalComponentMap);
			});
		}
	}
	
	/**
	 * map 集合模糊匹配 合计
	 * 
	 * @param dataMap map集合
	 * @param keyLike 模糊key
	 * @return
	 */
	public Integer sumKeyLike(Map<String, Integer> dataMap, String keyLike) {
		Integer valSum = 0;
		for (Map.Entry<String, Integer> entity : dataMap.entrySet()) {
			if (entity.getKey().startsWith(keyLike)) {
				valSum += entity.getValue();
			}
		}
		return valSum;
	}

}
