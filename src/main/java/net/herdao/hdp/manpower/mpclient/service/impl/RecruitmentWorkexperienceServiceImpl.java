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

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkExperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentWorkexperience;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentWorkexperienceMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentWorkexperienceService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 人才工作经历表
 *
 * @author Andy
 * @date 2020-11-26 09:18:33
 */
@Service
public class RecruitmentWorkexperienceServiceImpl extends ServiceImpl<RecruitmentWorkexperienceMapper, RecruitmentWorkexperience> implements RecruitmentWorkexperienceService {

    @Override
    public List<RecruitmentWorkExperienceDTO> findWorkExperienceList(Long recruitmentId) {
        List<RecruitmentWorkExperienceDTO> workExperienceList = this.baseMapper.findWorkExperienceList(recruitmentId);
        return workExperienceList;
    }

    @Override
    @OperationEntity(operation = "人才工作经历表-新增保存",module="人才简历", clazz = RecruitmentWorkExperienceDTO.class)
    public RecruitmentWorkExperienceDTO saveWorkExperience(RecruitmentWorkExperienceDTO dto) {
        RecruitmentWorkexperience workExperience=new RecruitmentWorkexperience();
        BeanUtils.copyProperties(dto,workExperience);

        SysUser sysUser = SysUserUtils.getSysUser();
        workExperience.setCreatorTime(LocalDateTime.now());
        workExperience.setCreatorCode(sysUser.getUsername());
        workExperience.setCreatorName(sysUser.getAliasName());

        super.save(workExperience);
        BeanUtils.copyProperties(workExperience,dto);
        return dto;
    }

    @Override
    @OperationEntity(operation = "人才工作经历表-修改更新",module="人才简历", clazz = RecruitmentWorkExperienceDTO.class)
    public RecruitmentWorkExperienceDTO updateWorkExperience(RecruitmentWorkExperienceDTO dto) {
        RecruitmentWorkexperience workExperience=new RecruitmentWorkexperience();
        BeanUtils.copyProperties(dto,workExperience);

        SysUser sysUser = SysUserUtils.getSysUser();
        workExperience.setModifierTime(LocalDateTime.now());
        workExperience.setModifierCode(sysUser.getUsername());
        workExperience.setModifierName(sysUser.getAliasName());

        super.updateById(workExperience);
        BeanUtils.copyProperties(workExperience,dto);
        return dto;
    }

    @Override
    public RecruitmentWorkExperienceDTO saveOrUpdateWorkExperience(RecruitmentWorkExperienceDTO dto) {
        if (ObjectUtil.isNotNull(dto)){
            if (ObjectUtil.isNotNull(dto.getId())){
                this.updateWorkExperience(dto);
            }
            if (ObjectUtil.isNull(dto.getId())){
                this.saveWorkExperience(dto);
            }
        }

        return null;
    }
}
