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

import net.herdao.hdp.manpower.mpclient.dto.staff.WorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.Workexperience;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 员工工作经历
 *
 * @author andy
 * @date 2020-09-24 10:24:09
 */
@Mapper
public interface WorkexperienceMapper extends BaseMapper<Workexperience> {

    /**
     * 员工工作经历分页
     * @param page 分页对象
     * @param searchText 关键字搜索
     * @return
     */
    Page<WorkexperienceDTO> findStaffWorkPage(Page<WorkexperienceDTO> page, @Param("searchText") String searchText);

    /**
     * 员工工作经历
     * @param searchText 关键字搜索
     * @return
     */
    List<WorkexperienceDTO> findStaffWork(@Param("searchText") String searchText);

    /**
     * author
     * 获取员工工作经历
     * @param staffid
     * @return
     */
    List<WorkexperienceDTO> findWorkexperienceDTO(Long staffid);
}
