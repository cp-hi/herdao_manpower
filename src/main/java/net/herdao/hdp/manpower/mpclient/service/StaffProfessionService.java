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

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.StaffProDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffProfession;

/**
 * 员工职称及职业资料
 *
 * @author andy
 * @date 2020-10-09 10:53:38
 */
public interface StaffProfessionService extends IService<StaffProfession> {
    /**
     * 员工职称及职业资料分页
     * @param page 分页对象
     * @param orgId
     * @param staffName
     * @param staffCode
     * @return
     */
    Page<StaffProDTO> findStaffProPage(Page<StaffProDTO> page, String orgId, String staffName, String staffCode);

    /**
     * 新增或修改员工职称及职业资料
     * @param profession
     * @return
     */
    @Override
    boolean saveOrUpdate(StaffProfession profession);

}
