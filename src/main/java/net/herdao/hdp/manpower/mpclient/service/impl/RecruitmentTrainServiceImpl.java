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
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentTrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentTrain;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentTrainMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentTrainService;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人才培训表
 *
 * @author Andy
 * @date 2020-12-02 20:12:55
 */
@Service
public class RecruitmentTrainServiceImpl extends ServiceImpl<RecruitmentTrainMapper, RecruitmentTrain> implements RecruitmentTrainService {
    @Override
    public List<RecruitmentTrainDTO> findRecruitmentTrainList(Long recruitmentId) {
        List<RecruitmentTrainDTO> list = this.baseMapper.findRecruitmentTrainList(recruitmentId);
        if (CollectionUtil.isNotEmpty(list)){
            for (RecruitmentTrainDTO dto : list) {
                dto.setBeginDate(LocalDateTimeUtils.convert2Long(dto.getBeginDateLocal()));
                dto.setEndDate(LocalDateTimeUtils.convert2Long(dto.getEndDateLocal()));
            }
        }
        return list;
    }
}
