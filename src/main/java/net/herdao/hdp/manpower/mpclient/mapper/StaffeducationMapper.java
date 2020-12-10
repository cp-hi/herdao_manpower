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

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEducationDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 员工教育经历
 *
 * @author andy
 * @date 2020-09-23 17:22:28
 */
@Mapper
public interface StaffeducationMapper extends BaseMapper<Staffeducation> {
    /**
     * 员工家庭情况分页
     * @param page 分页对象
     * @param searchText 关键字搜索
     * @return
     */
    List<StaffEducationDTO> findStaffEducationPage(Map<String, Object> map);

    /**
     * 员工教育经历
     * @param searchText 关键字搜索
     * @param staffId 员工工号
     * @return
     */
    List<StaffEducationDTO> findStaffEducation(@Param("query") StaffEducationDTO staffEducationDTO,@Param("searchText") String searchText);
}
