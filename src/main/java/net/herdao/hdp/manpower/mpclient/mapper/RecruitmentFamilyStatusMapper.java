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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentFamilyDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentFamilyStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人才家庭情况
 *
 * @author Andy
 * @date 2020-11-26 15:28:43
 */
@Mapper
public interface RecruitmentFamilyStatusMapper extends BaseMapper<RecruitmentFamilyStatus> {
    /**
     * 个人简历-家庭情况
     * @param recruitmentId 人才ID
     * @return
     */
    List<RecruitmentFamilyDTO> fetchResumeFamilyList(@Param("recruitmentId") Long recruitmentId);

    /**
     * @param page 分页对象
     * @param recruitmentId 人才ID
     * @return
     */
    Page<RecruitmentFamilyDTO> fetchResumeFamilyPage(Page page,@Param("recruitmentId") Long recruitmentId);

}
