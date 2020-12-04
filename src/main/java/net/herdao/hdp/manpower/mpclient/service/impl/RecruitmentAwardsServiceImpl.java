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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentAwardsDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentAwards;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentAwardsMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentAwardsService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    @OperationEntity(operation = "人才简历-获奖情况-新增或修改",module="人才简历", clazz = RecruitmentAwardsDTO.class)
    public RecruitmentAwardsDTO saveOrUpdate(RecruitmentAwardsDTO dto) {
        //更新
        if(ObjectUtil.isNotNull(dto.getId())){
            RecruitmentAwards awards=new RecruitmentAwards();
            BeanUtils.copyProperties(dto,awards);

            SysUser sysUser = SysUserUtils.getSysUser();
            awards.setModifierTime(LocalDateTime.now());
            awards.setModifierCode(sysUser.getUsername());
            awards.setModifierName(sysUser.getAliasName());

            BeanUtils.copyProperties(awards,dto);
            super.updateById(awards);
        }

        //新增
        if(ObjectUtil.isNull(dto.getId())){
            RecruitmentAwards awards=new RecruitmentAwards();
            BeanUtils.copyProperties(dto,awards);

            SysUser sysUser = SysUserUtils.getSysUser();
            awards.setCreatorTime(LocalDateTime.now());
            awards.setCreatorCode(sysUser.getUsername());
            awards.setCreatorName(sysUser.getAliasName());

            BeanUtils.copyProperties(awards,dto);
            super.save(awards);
        }

        return dto;
    }

    @Override
    public List<RecruitmentAwardsDTO> fetchResumeAwardsList(Long recruitmentId) {
        List<RecruitmentAwardsDTO> list = this.baseMapper.fetchResumeAwardsList(recruitmentId);
        return list;
    }
}
