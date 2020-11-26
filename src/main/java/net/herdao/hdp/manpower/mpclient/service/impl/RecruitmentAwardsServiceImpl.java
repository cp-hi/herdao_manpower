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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentAwardsDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentAwards;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentAwardsMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentAwardsService;
import org.springframework.stereotype.Service;

/**
 * 人才获奖情况表
 *
 * @author Andy
 * @date 2020-11-26 18:51:47
 */
@Service
public class RecruitmentAwardsServiceImpl extends ServiceImpl<RecruitmentAwardsMapper, RecruitmentAwards> implements RecruitmentAwardsService {

    @Override
    public Page<RecruitmentAwardsDTO> fetchResumeAwardsPage(Page page, Long recruitmentId) {
        return this.baseMapper.fetchResumeAwardsPage(page, recruitmentId);
    }
}
