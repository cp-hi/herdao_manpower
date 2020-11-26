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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentUpdateFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentWorkexperience;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentWorkexperienceMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentWorkexperienceService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 人才工作经历表
 *
 * @author Andy
 * @date 2020-11-26 09:18:33
 */
@Service
public class RecruitmentWorkexperienceServiceImpl extends ServiceImpl<RecruitmentWorkexperienceMapper, RecruitmentWorkexperience> implements RecruitmentWorkexperienceService {

    @Override
    public RecruitmentWorkexperienceDTO findWorkExperience(Long recruitmentId) {
        RecruitmentWorkexperienceDTO result = this.baseMapper.findWorkExperience(recruitmentId);
        return result;
    }

    @Override
    public RecruitmentWorkexperienceDTO saveWorkExperience(RecruitmentWorkexperienceDTO dto) {
        RecruitmentWorkexperience workexperience=new RecruitmentWorkexperience();
        BeanUtils.copyProperties(dto,workexperience);

        SysUser sysUser = SysUserUtils.getSysUser();
        workexperience.setCreatorTime(LocalDateTime.now());
        workexperience.setCreatorCode(sysUser.getUsername());
        workexperience.setCreatorName(sysUser.getAliasName());

        super.save(workexperience);
        BeanUtils.copyProperties(workexperience,dto);
        return dto;
    }

    @Override
    public RecruitmentWorkexperienceDTO updateWorkExperience(RecruitmentWorkexperienceDTO dto) {
        RecruitmentWorkexperience workexperience=new RecruitmentWorkexperience();
        BeanUtils.copyProperties(dto,workexperience);

        SysUser sysUser = SysUserUtils.getSysUser();
        workexperience.setModifierTime(LocalDateTime.now());
        workexperience.setModifierCode(sysUser.getUsername());
        workexperience.setModifierName(sysUser.getAliasName());

        super.save(workexperience);
        BeanUtils.copyProperties(workexperience,dto);
        return dto;
    }
}
