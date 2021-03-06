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

import net.herdao.hdp.manpower.mpclient.entity.Workexperience;

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
    List<Workexperience> findStaffWorkPage(Map<String, Object> map);

    /**
     * 员工工作经历
     * @param searchText 关键字搜索
     * @param staffId 员工ID
     * @return
     */
    List<Workexperience> findStaffWork(@Param("searchText") String searchText,@Param("staffId") String staffId);

    /**
     * author
     * 获取员工工作经历
     * @param staffid
     * @return
     */
    List<Workexperience> findWorkexperience(Long staffid);
}
