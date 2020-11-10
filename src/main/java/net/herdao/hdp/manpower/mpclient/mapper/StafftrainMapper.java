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
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Mapper
public interface StafftrainMapper extends BaseMapper<Stafftrain> {
    /**
     * 员工培训分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    Page<StafftrainDTO> findStaffTrainPage(Page<StafftrainDTO> page,@Param("query") StafftrainDTO stafftrainDTO, @Param("searchText") String searchText);


    /**
     * 员工培训
     * @param searchText
     * @return
     */
    List<StafftrainDTO> findStaffTrain(@Param("searchText") String searchText);

}
