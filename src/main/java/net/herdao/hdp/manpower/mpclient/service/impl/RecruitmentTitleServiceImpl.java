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
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentTitleDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentTitle;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentTitleMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentTitleService;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人才职称资格
 *
 * @author Andy
 * @date 2020-12-05 14:19:39
 */
@Service
public class RecruitmentTitleServiceImpl extends ServiceImpl<RecruitmentTitleMapper, RecruitmentTitle> implements RecruitmentTitleService {

    @Override
    public List<RecruitmentTitleDTO> findRecruitmentTitleList(Long recruitmentId) {
        List<RecruitmentTitleDTO> titleList = this.baseMapper.findRecruitmentTitleList(recruitmentId);
        if (CollectionUtil.isNotEmpty(titleList)){
            for (RecruitmentTitleDTO dto : titleList) {
                dto.setCertificateTime(LocalDateTimeUtils.convert2Long(dto.getCertificateTimeLocal()));
            }
        }
        return titleList;
    }
}
