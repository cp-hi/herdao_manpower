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
package net.herdao.hdp.manpower.mpmobile.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpmobile.entity.CardInformation;
import net.herdao.hdp.manpower.mpmobile.mapper.CardInformationMapper;
import net.herdao.hdp.manpower.mpmobile.service.CardInformationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 身份证信息表
 *
 * @author liang
 * @date 2020-12-16 09:43:28
 */
@Service
public class CardInformationServiceImpl extends ServiceImpl<CardInformationMapper, CardInformation> implements CardInformationService {

    @Override
    public CardInformation getIdentityById(Long id) {
        QueryWrapper<CardInformation> cardInformationQueryWrapper = new QueryWrapper<>();
        cardInformationQueryWrapper.select("card_holder","card_type","card_code","date_of_birth","sex").
                lambda().eq(CardInformation::getBizId,id);
        return  this.baseMapper.selectOne(cardInformationQueryWrapper);
    }


}
