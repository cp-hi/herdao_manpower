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
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffRewardsPulishments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 员工奖惩
 *
 * @author andy
 * @date 2020-09-25 16:26:20
 */
@Mapper
public interface StaffRewardsPulishmentsMapper extends BaseMapper<StaffRewardsPulishments> {
    /**
     * 员工奖惩分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    List<StaffRpDTO> findStaffRpPage(Map<String, Object> map);

    /**
     * 员工奖惩
     * @param searchText
     * @return
     */
    List<StaffRpDTO> findStaffRp(@Param("query") StaffRpDTO staffRpDTO, @Param("searchText") String searchText);



}
