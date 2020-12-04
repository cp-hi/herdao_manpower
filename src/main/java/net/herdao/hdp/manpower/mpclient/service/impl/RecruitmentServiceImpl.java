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
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.*;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
@Service
public class RecruitmentServiceImpl extends ServiceImpl<RecruitmentMapper, Recruitment> implements RecruitmentService {

    @Override
    public Page<RecruitmentDTO> findRecruitmentPage(Page<RecruitmentDTO> page, String orgId, String searchText) {
        Page<RecruitmentDTO> list = this.baseMapper.findRecruitmentPage(page, orgId, searchText);
        return list;
    }

    @Override
    @OperationEntity(operation = "人才表单更新",module="人才简历", clazz = RecruitmentUpdateFormDTO.class)
    public R<RecruitmentUpdateFormDTO> updateRecruitment(RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        //校检手机号码
        if (checkMobile(recruitmentUpdateFormVO)){
            return R.failed("当前手机号码已被占用，请重新检查手机号码!");
        }

        //校检姓名+手机号码
        if (checkTalentNameAndMobile(recruitmentUpdateFormVO)){
            return R.failed("候选人#姓名#已存在!");
        }

        //校检邮箱
        if (checkEmail(recruitmentUpdateFormVO)){
            return R.failed("当前个人邮箱已被占用，请重新检查手机号码!");
        }

        Recruitment recruitment=new Recruitment();
        BeanUtils.copyProperties(recruitmentUpdateFormVO,recruitment);

        SysUser sysUser = SysUserUtils.getSysUser();
        recruitment.setModifierTime(LocalDateTime.now());
        recruitment.setModifierCode(sysUser.getUsername());
        recruitment.setModifierName(sysUser.getAliasName());
        super.updateById(recruitment);

        BeanUtils.copyProperties(recruitment, recruitmentUpdateFormVO);
        return R.ok(recruitmentUpdateFormVO,"快速编辑-保存成功");
    }

    @Override
    @OperationEntity(operation = "人才表单新增",module="人才简历", clazz = RecruitmentAddFormDTO.class)
    public R<RecruitmentAddFormDTO> saveRecruitment(RecruitmentAddFormDTO recruitmentAddFormDTO) {
        RecruitmentUpdateFormDTO updateFormVO=new RecruitmentUpdateFormDTO();
        BeanUtils.copyProperties(recruitmentAddFormDTO,updateFormVO);

        //校检手机号码
        if (checkMobile(updateFormVO)){
            return R.failed("当前手机号码已被占用，请重新检查手机号码!");
        }

        //校检姓名+手机号码
        if (checkTalentNameAndMobile(updateFormVO)){
            return R.failed("候选人已存在!");
        }

        Recruitment recruitment=new Recruitment();
        BeanUtils.copyProperties(recruitmentAddFormDTO,recruitment);

        SysUser sysUser = SysUserUtils.getSysUser();
        recruitment.setCreatorTime(LocalDateTime.now());
        recruitment.setCreatorCode(sysUser.getUsername());
        recruitment.setCreatorName(sysUser.getAliasName());
        super.save(recruitment);

        BeanUtils.copyProperties(recruitment, recruitmentAddFormDTO);
        return R.ok(recruitmentAddFormDTO,"新增候选人成功");
    }

    /**
     * 校检手机号码
     * @param recruitmentUpdateFormVO
     * @return
     */
    private boolean checkMobile(RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        if (ObjectUtil.isNotEmpty(recruitmentUpdateFormVO.getMobile())){
            LambdaQueryWrapper<Recruitment> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq( Recruitment::getMobile,recruitmentUpdateFormVO.getMobile());
            List<Recruitment> list = this.list(queryWrapper);
            return ObjectUtil.isNotEmpty(list) && list.size() >= 2;
        }
        return false;
    }

    /**
     * 校检姓名+手机号码
     * @param recruitmentUpdateFormVO
     * @return
     */
    private boolean checkTalentNameAndMobile(RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        if (ObjectUtil.isAllNotEmpty(recruitmentUpdateFormVO.getTalentName(),recruitmentUpdateFormVO.getMobile())){
            LambdaQueryWrapper<Recruitment> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq( Recruitment::getTalentName,recruitmentUpdateFormVO.getTalentName());
            queryWrapper.eq( Recruitment::getMobile,recruitmentUpdateFormVO.getMobile());
            List<Recruitment> list = this.list(queryWrapper);
            return ObjectUtil.isNotEmpty(list) && list.size() >= 2;
        }
        return false;
    }

    /**
     * 校检邮箱
     * @param recruitmentUpdateFormVO
     * @return
     */
    private boolean checkEmail(RecruitmentUpdateFormDTO recruitmentUpdateFormVO) {
        if (ObjectUtil.isNotEmpty(recruitmentUpdateFormVO.getEmail())){
            LambdaQueryWrapper<Recruitment> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq( Recruitment::getEmail,recruitmentUpdateFormVO.getEmail());
            List<Recruitment> list = this.list(queryWrapper);
            return ObjectUtil.isNotEmpty(list) && list.size() >= 2;
        }
        return false;
    }

    @Override
    public RecruitmentUpdateFormDTO fetchResumeTop(Long id) {
        RecruitmentUpdateFormDTO entity = this.baseMapper.fetchResumeTop(id);
        return entity;
    }

    @Override
    public RecruitmentBaseDTO fetchResumeBaseSituation(Long id) {
        RecruitmentBaseDTO entity = this.baseMapper.fetchResumeBaseSituation(id);
        return entity;
    }

    @Override
    @OperationEntity(operation = "人才简历-个人基本情况 其他个人信息 从业情况与求职意向",module="人才简历", clazz = RecruitmentBaseDTO.class)
    public RecruitmentBaseDTO saveOrUpdate(RecruitmentBaseDTO dto) {
        //更新
        if(ObjectUtil.isNotNull(dto.getId())){
            Recruitment recruitment=new Recruitment();
            BeanUtils.copyProperties(dto,recruitment);
            SysUser sysUser = SysUserUtils.getSysUser();
            recruitment.setModifierTime(LocalDateTime.now());
            recruitment.setModifierCode(sysUser.getUsername());
            recruitment.setModifierName(sysUser.getAliasName());
            super.updateById(recruitment);
            BeanUtils.copyProperties(recruitment,dto);
        }

        //新增
        if(ObjectUtil.isNull(dto.getId())){

        }

        return dto;
    }

    @Override
    public RecruitmentJobDTO fetchResumeJob(Long id) {
        RecruitmentJobDTO result = this.baseMapper.fetchResumeJob(id);
        return result;
    }

    @Override
    @OperationEntity(operation = "人才简历-从业情况与求职意向-更新",module="人才简历", clazz = RecruitmentJobDTO.class)
    public RecruitmentJobDTO updateRecruitmentJob(RecruitmentJobDTO dto) {
        Recruitment recruitment=new Recruitment();

        BeanUtils.copyProperties(dto,recruitment);
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitment.setModifierTime(LocalDateTime.now());
        recruitment.setModifierCode(sysUser.getUsername());
        recruitment.setModifierName(sysUser.getAliasName());
        super.updateById(recruitment);

        BeanUtils.copyProperties(recruitment,dto);

        return dto;
    }

    @Override
    public RecruitmentEmployeeDTO fetchEmploy(String recruitmentId) {
        RecruitmentEmployeeDTO entity = this.baseMapper.fetchEmploy(recruitmentId);
        return entity;
    }
}
