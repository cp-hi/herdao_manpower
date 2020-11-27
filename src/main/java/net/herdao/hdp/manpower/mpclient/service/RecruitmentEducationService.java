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

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEduDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentEducation;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 人才教育情况
 *
 * @author Andy
 * @date 2020-11-27 08:57:26
 */
public interface RecruitmentEducationService extends IService<RecruitmentEducation> {

    /**
     * @param page 人才简历-教育情况-列表
     * @param recruitmentId 人才ID
     * @return
     */
    Page<RecruitmentEduDTO> fetchResumeEduPage(Page page, Long recruitmentId);

    /**
     * 人才简历-教育情况-新增或修改
     * @param dto
     * @return
     */
    RecruitmentEduDTO saveOrUpdate(RecruitmentEduDTO dto);
}
