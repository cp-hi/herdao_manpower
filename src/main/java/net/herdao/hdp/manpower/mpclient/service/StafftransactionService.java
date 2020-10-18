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
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftransactionDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftransDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 异动情况
 *
 * @author andy
 * @date 2020-09-24 16:00:18
 */
public interface StafftransactionService extends EntityService<Stafftransaction> {
    /**
     * 员工异动情况分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    Page<StafftransDTO> findStaffTransPage(Page<StafftransDTO> page, String searchText);


    /**
     * @author lift
     * 员工详情-员工异动情况DTO
     * @param staffid
     * @return
     */
    List<StafftransactionDTO> findStafftransactionDto(Long staffid);
    
    List<StafftransDTO> findStaffTrans(String searchText);
}
