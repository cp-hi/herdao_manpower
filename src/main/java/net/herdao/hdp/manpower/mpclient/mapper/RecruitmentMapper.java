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
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentBaseDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentUpdateFormDTO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 人才表
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
@Mapper
public interface RecruitmentMapper extends BaseMapper<Recruitment> {

    /**
     * 人才表分页
     * @param page 分页对象
     * @param recruitmentDTO 人才DTO
     * @param searchText 关键字
     * @return
     */
    Page<RecruitmentDTO> findRecruitmentPage(Page<RecruitmentDTO> page, @Param("orgId") String orgId, @Param("searchText") String searchText);

    /**
     * 人才简历-顶部
     * @param id 主键ID
     * @return
     */
    RecruitmentUpdateFormDTO fetchResumeTop(Long id);

    /**
     * 人才简历-个人基本情况 从业情况与求职意向
     * @param id 主键ID
     * @return
     */
    RecruitmentBaseDTO fetchResumeBaseSituation(Long id);
}
