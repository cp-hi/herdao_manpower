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
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.mapper.StaffMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.vo.StaffComponentVo;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationComponentVo;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationDataComponentVo;
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
	
	@Override
	public R<?> selectStaffOrganizationComponent(String searchText, String isLikeSearch) {
		
		// 待接入用户权限 TODO
		
		// 是否模糊查询
		Boolean likeSearch = BooleanUtil.toBoolean(isLikeSearch);
		
		StaffOrganizationDataComponentVo staffOrganizationDataComponentVo = new StaffOrganizationDataComponentVo();
		
		// 部门/组织员工合计数（包含下级）
		if (likeSearch) {
			if(StrUtil.isBlank(StrUtil.toString(searchText).trim())) {
				return R.failed("查询条件为空！");
			}
			// 员工信息   
			staffOrganizationDataComponentVo.setStaffComponents(this.selectStaffComponent(searchText, isLikeSearch));			
		} else {
			// 下級部门/组织信息
			List<StaffOrganizationComponentVo> organizationChildrenVos = null;
			// 部门/组织信息
			if(StrUtil.isBlank(searchText)) {
				// 下級部门/组织信息
				organizationChildrenVos = this.baseMapper.selectOrganizations(searchText);
			}else {
				// 下級部门/组织信息
				organizationChildrenVos = this.baseMapper.selectOrganizationChildrens(searchText);
				
				// 员工信息
				staffOrganizationDataComponentVo.setStaffComponents(this.selectStaffComponent(searchText, null));
			}
			
			// 获取部门员工数
			Map<String, Integer> taffTotalComponentMap = this.baseMapper.getStaffTotals(searchText).stream()
					                                         .collect(Collectors.toMap(StaffTotalComponentVo::getOrgCode, StaffTotalComponentVo::getTotal));
			// 子部门/组织员工数
			organizationChildrenVos.forEach(organizationChildren -> {
				Integer staffTotal = taffTotalComponentMap.get(organizationChildren.getOrgCode());
				organizationChildren.setStaffTotal(staffTotal == null ? 0 : staffTotal);
			});
			
			// 子部门/组织信息
			staffOrganizationDataComponentVo.setOrganizationComponents(organizationChildrenVos);
		}
		return R.ok(staffOrganizationDataComponentVo);
	}

	@Override
	public List<StaffComponentVo> selectStaffComponent(String searchText, String isLikeSearch) {
		return this.baseMapper.selectStaffs(searchText, BooleanUtil.toBoolean(isLikeSearch));
	}

}
