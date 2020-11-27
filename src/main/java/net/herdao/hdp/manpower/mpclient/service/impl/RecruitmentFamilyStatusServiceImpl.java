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
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentFamilyDTO;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentFamilyStatus;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentWorkexperience;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentFamilyStatusMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentFamilyStatusService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 人才家庭情况
 *
 * @author Andy
 * @date 2020-11-26 15:28:43
 */
@Service
public class RecruitmentFamilyStatusServiceImpl extends ServiceImpl<RecruitmentFamilyStatusMapper, RecruitmentFamilyStatus> implements RecruitmentFamilyStatusService {

    @Override
    public List<RecruitmentFamilyDTO> fetchResumeFamily(Long recruitmentId) {
        List<RecruitmentFamilyDTO> list = this.baseMapper.fetchResumeFamily(recruitmentId);
        return list;
    }

    @Override
    public Page<RecruitmentFamilyDTO> fetchResumeFamilyPage(Page page, Long recruitmentId) {
        Page<RecruitmentFamilyDTO> list = this.baseMapper.fetchResumeFamilyPage(page, recruitmentId);
        return list;
    }

    @Override
    @OperationEntity(operation = "人才简历-家庭成员-新增保存",module="人才简历", clazz = RecruitmentFamilyDTO.class)
    public RecruitmentFamilyDTO saveFamily(RecruitmentFamilyDTO familyDTO) {
        RecruitmentFamilyStatus familyStatus=new RecruitmentFamilyStatus();
        BeanUtils.copyProperties(familyDTO,familyStatus);

        SysUser sysUser = SysUserUtils.getSysUser();
        familyStatus.setCreatorTime(LocalDateTime.now());
        familyStatus.setCreatorCode(sysUser.getUsername());
        familyStatus.setCreatorName(sysUser.getAliasName());

        super.save(familyStatus);
        BeanUtils.copyProperties(familyStatus,familyDTO);
        return familyDTO;
     }

    @Override
    @OperationEntity(operation = "人才简历-家庭成员-修改更新",module="人才简历", clazz = RecruitmentFamilyDTO.class)
    public RecruitmentFamilyDTO updateFamily(RecruitmentFamilyDTO familyDTO) {
        RecruitmentFamilyStatus familyStatus=new RecruitmentFamilyStatus();
        BeanUtils.copyProperties(familyDTO,familyStatus);

        SysUser sysUser = SysUserUtils.getSysUser();
        familyStatus.setModifierTime(LocalDateTime.now());
        familyStatus.setModifierCode(sysUser.getUsername());
        familyStatus.setModifierName(sysUser.getAliasName());

        super.updateById(familyStatus);
        BeanUtils.copyProperties(familyStatus,familyDTO);
        return familyDTO;
    }
}
