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

package net.herdao.hdp.manpower.mpclient.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.vo.StaffComponentVo;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationComponentVo;
import net.herdao.hdp.manpower.mpclient.vo.StaffTotalComponentVo;

/**
 * 员工表
 *
 * @author yangrr
 * @date 2020-09-23 18:10:29
 */
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {
	
	/**
	 * 查询员工信息（包含部门/组织信息）
	 * 
	 * @param searchText
	 * @return
	 */
	public StaffOrganizationComponentVo selectOrganization(@Param("searchText") String searchText);
	
	/**
	 * 查询员工所在下级部门/组织信息
	 * 
	 * @param searchText
	 * @return
	 */
	public List<StaffOrganizationComponentVo> selectOrganizationChildrens(@Param("searchText")String searchText);
	
	/**
	 * 查询员工信息
	 * 
	 * @param searchText
	 * @param isLikeSearch
	 * @return
	 */
	public List<StaffComponentVo> selectStaffs(@Param("searchText")String searchText, @Param("isLikeSearch")Boolean isLikeSearch);
	
	/**
	 * 统计部门/组织员工数
	 * 
	 * @param searchText
	 * @return
	 */
	public List<StaffTotalComponentVo> getStaffTotals(@Param("searchText")String searchText);

}
