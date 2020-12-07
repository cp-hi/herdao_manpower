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

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentActivitiDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentTitleDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentActiviti;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 人才活动表
 *
 * @author Andy
 * @date 2020-12-02 20:12:40
 */
@Mapper
public interface RecruitmentActivitiMapper extends BaseMapper<RecruitmentActiviti> {
    /**
     * 简历详情-人才活动
     * @param recruitmentId 人才ID
     * @return
     */
    List<RecruitmentActivitiDTO> findRecruitmentActivitiList(Long recruitmentId);
}
