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

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentUpdateFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentWorkexperience;

/**
 * 人才工作经历表
 *
 * @author Andy
 * @date 2020-11-26 09:18:33
 */
public interface RecruitmentWorkexperienceService extends IService<RecruitmentWorkexperience> {
    /**
     * 个人简历-工作经历
     * @param recruitmentId 人才ID
     * @return
     */
    RecruitmentWorkexperienceDTO findWorkExperience(Long recruitmentId);

    /**
     * 个人简历-工作经历-新增
     * @param dto 人才实体
     * @return
     */
    RecruitmentWorkexperienceDTO saveWorkExperience(RecruitmentWorkexperienceDTO dto);

    /**
     * 个人简历-工作经历-更新
     * @param dto 人才实体
     * @return
     */
    RecruitmentWorkexperienceDTO updateWorkExperience(RecruitmentWorkexperienceDTO dto);
}
