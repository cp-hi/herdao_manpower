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
import net.herdao.hdp.manpower.mpclient.entity.StaffProTitle;
import net.herdao.hdp.manpower.mpclient.entity.StaffRewardsPulishments;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 员工职称
 *
 * @author andy
 * @date 2020-10-10 16:37:15
 */
@Mapper
public interface StaffProTitleMapper extends BaseMapper<StaffProTitle> {
    /**
     * 员工职称分页
     * @param page 分页对象
     * @param staffId 员工ID
     * @return
     */
    Page<StaffProTitle> findStaffProTitlePage(Page<StaffProTitle> page,  @Param("staffId") String staffId);

    /**
     * 新增或修改员工职称
     * @param entity
     * @return
     */
     boolean saveOrUpdate(StaffProTitle entity);
}
