/*
 *    Copyright (c) 2018-2025, herdao All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the herdao.net developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liang
 */

package net.herdao.hdp.manpower.mpmobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpmobile.dto.PayCardInformationDTO;
import net.herdao.hdp.manpower.mpmobile.entity.PayCardInformation;

/**
 * 工资卡信息表
 *
 * @author liang
 * @date 2020-12-16 09:46:03
 */
public interface PayCardInformationService extends IService<PayCardInformation> {


    /*
     * 通过id查询工资卡信息表
   */
    PayCardInformation getCardById(Long id);

    /*
     *
     * 新增工资卡信息表
     * @param payCardInformation 工资卡信息表
     * @return R*/
    Long insertPayCard(PayCardInformationDTO payCardInformation);
}
