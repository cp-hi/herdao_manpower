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
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import net.herdao.hdp.manpower.mpclient.dto.staff.StaffArchiveDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffBaseDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffEducationLastDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffInfoDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffJobInfoDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffJobTravelDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffListDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffSalaryDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffSecurityDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffWelfareDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffWorkYearDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.vo.StaffComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationComponentVO;
import net.herdao.hdp.manpower.mpclient.vo.StaffTotalComponentVO;

/**
 * 员工表
 *
 * @author yangrr
 * @date 2020-09-23 18:10:29
 */
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {
	
	/**
	 * 查询员工信息（组件）
	 * 
	 * @return
	 */
	public List<StaffOrganizationComponentVO> selectOrganizations();
	
	/**
	 * 查询员工信息（组件）
	 * 
	 * @param orgCode
	 * @return
	 */
	public List<StaffOrganizationComponentVO> selectOrganizationComponentList(@Param("orgCode") String orgCode);
	
	/**
	 * 查询组织下所有人员信息
	 * 
	 * @param orgCode
	 * @param batchSelectOrgCodes
	 * @param staffCodes
	 * @param searchText
	 * @return
	 */
	public List<StaffComponentVO> selectStaffs(@Param("orgCode") String orgCode, @Param("batchSelectOrgCodes") String [] batchSelectOrgCodes, @Param("staffCodes") String [] staffCodes, @Param("searchText") String searchText);
	
	
	/**
	 * 统计组织员工数
	 * 
	 * @param orgCode
	 * @return
	 */
	public List<StaffTotalComponentVO> getStaffTotals(@Param("orgCode")String orgCode);
	
	/**
	 * 查询员工工龄信息
	 * 
	 * @return
	 */
	public StaffWorkYearDTO getStaffWorkYear(Long staffid);

	/**
	 * 查询员工基本信息
	 * @param id
	 * @return StaffBaseDTO
	 */
	StaffBaseDTO getStaffBase(Long id);

	/**
	 * 查询员工个人档案
	 * @param id
	 * @return StaffArchiveDTO
	 */
	StaffArchiveDTO getStaffArchive(Long id);

	/**
	 * 查询员工最高教育经历
	 * @param id
	 * @return StaffEducationLastDTO
	 */
	StaffEducationLastDTO getStaffEducationLast(Long id);

	/**
	 * 查询员工劳资情况
	 * @param id
	 * @return StaffWelfareDTO
	 */
	StaffWelfareDTO getStaffWelfare(Long id);

	/**
	 * 查询员工个人信息
	 * @param id
	 * @return StaffInfoDTO
	 */
	StaffInfoDTO getStaffInfo(Long id);

	/**
	 * 查询员工在职信息
	 * @param id
	 * @return StaffJobInfoDTO
	 */
	StaffJobInfoDTO getStaffJobInfo(Long id);

	/**
	 * 查询员工社保信息
	 * @param id
	 * @return StaffSecurityDTO
	 */
	StaffSecurityDTO getStaffSecurity(Long id);

	/**
	 * 查询员工薪资信息
	 * @param id
	 * @return StaffSalaryDTO
	 */
	StaffSalaryDTO getStaffSalary(Long id);

	/**
	 * 查询员工任职轨迹
	 * @param id
	 * @return List
	 */
	List<StaffJobTravelDTO> getJobTravel(Long id);

	/**
	 * 员工分页列表
	 *
	 * @param map
	 * @return
	 */
	List<StaffListDTO> staffPage(Map<String, Object> map);

}
