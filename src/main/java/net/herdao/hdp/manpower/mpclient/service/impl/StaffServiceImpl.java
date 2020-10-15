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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staff.*;
import net.herdao.hdp.manpower.mpclient.utils.DtoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.ObjectUtil;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.mapper.StaffMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.StaffTotalComponentVO;

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
		
		List<StaffOrganizationComponentVO> organizations = this.baseMapper.selectOrganizations(searchText);
		
		// 获取部门员工数
		Map<String, Integer> taffTotalComponentMap = this.baseMapper.getStaffTotals().stream()
				                                         .collect(Collectors.toMap(StaffTotalComponentVO::getOrgCode, StaffTotalComponentVO::getTotal));
		// 部门/组织员工数
		organizations.forEach(organization -> {
			Integer staffTotal = sumKeyLike(taffTotalComponentMap, organization.getOrgCode());
			organization.setStaffTotal(staffTotal);
		});
				
		organizations.forEach(organization -> {
			List<StaffOrganizationComponentVO> staffOrganizationComponents = organization.getStaffOrganizationComponents();
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
	public void recursionOrganization(List<StaffOrganizationComponentVO> staffOrganizationComponents, Map<String, Integer> taffTotalComponentMap) {
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

	@Override
	public Map<String, Object> queryCount(){
		int total = baseMapper.selectCount(new QueryWrapper<Staff>());
		int jobType1 = baseMapper.selectCount(new QueryWrapper<Staff>()
				.eq("JOB_TYPE", "1")
		);
		int jobType2 = baseMapper.selectCount(new QueryWrapper<Staff>()
				.eq("JOB_TYPE", "2")
		);
		int jobType3 = baseMapper.selectCount(new QueryWrapper<Staff>()
				.eq("JOB_TYPE", "3")
		);
		int toJoin = 0;
		int toLeave = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("total",total);
		map.put("jobType1",jobType1);
		map.put("jobType2",jobType2);
		map.put("jobType3",jobType3);
		map.put("toJoin",toJoin);
		map.put("toLeave",toLeave);
		return map;
	}

	@Override
	public IPage staffPage(Page page, Staff staff, String searchText){
		QueryWrapper<Staff> wrapper =  Wrappers.query(staff);
		if(searchText!=null && !"".equals(searchText)){
			wrapper.like("CONCAT(STAFF_NAME,STAFF_CODE)", searchText);
		}
		IPage result = this.page(page, wrapper);
		List<Staff> list = result.getRecords();
		List<StaffListDTO> entityList = new ArrayList<>();
		StaffListDTO entity;
		for(int i=0;i<list.size();i++){
			entity = DtoUtils.transferObject(list.get(i), StaffListDTO.class);
			entityList.add(entity);
		}
		result.setRecords(entityList);
		return result;
	}

	@Override
	public boolean staffSave(StaffDetailDTO staffForm){
		Staff staff = new Staff();
		BeanUtils.copyProperties(staffForm.getBaseObj(), staff);
		BeanUtils.copyProperties(staffForm.getJobObj(), staff);
		return this.save(staff);
	}

    @Override
    public boolean staffUpdate(StaffDetailDTO staffForm){
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffForm.getBaseObj(), staff);
        BeanUtils.copyProperties(staffForm.getJobObj(), staff);
        return this.updateById(staff);
    }

	@Override
    public StaffDetailDTO getStaffById(Long id){
	    Staff staff = this.getById(id);
        StaffDetailBaseDTO base = new StaffDetailBaseDTO();
        StaffDetailJobDTO job = new StaffDetailJobDTO();
        BeanUtils.copyProperties(staff, base);
        BeanUtils.copyProperties(staff, job);
        StaffDetailDTO form = new StaffDetailDTO(base, job);
        return form;
    }

	@Override
    public Map<String, Object> getStaffDetail(Long id){
		Staff staff = this.getById(id);
		StaffInfoDTO info = new StaffInfoDTO();
		StaffJobInfoDTO jobInfo = new StaffJobInfoDTO();
		StaffInfoOtherDTO infoOther = new StaffInfoOtherDTO();
		StaffEmergencyDTO emergency = new StaffEmergencyDTO();
		StaffEducationLastDTO educationLast = new StaffEducationLastDTO();
		BeanUtils.copyProperties(staff, info);
		BeanUtils.copyProperties(staff, jobInfo);
		BeanUtils.copyProperties(staff, infoOther);
		BeanUtils.copyProperties(staff, emergency);
		BeanUtils.copyProperties(staff, educationLast);

		Map<String, Object> map = new HashMap<>();
		map.put("info", info);
		map.put("jobInfo", jobInfo);
		map.put("infoOther", infoOther);
		map.put("emergency", emergency);
		map.put("educationLast", educationLast);
		return map;
	}
}
