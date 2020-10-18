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

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffPracticeDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPractice;

/**
 * 员工实习记录
 * @author andy
 * @date 2020-10-09 17:51:16
 */
public interface StaffPracticeService extends IService<StaffPractice> {
    /**
     * 新增或修改员工实习记录
     * @param practice
     * @return
     */
    @Override
    boolean saveOrUpdate(StaffPractice practice);

    /**
     * 员工实习记录
     * @param staffId
     * @return
     */
    StaffPracticeDTO findStaffPractice(Long staffId);

}
