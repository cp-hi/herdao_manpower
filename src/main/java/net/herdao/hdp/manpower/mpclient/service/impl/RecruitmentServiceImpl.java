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
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.constant.CacheConstants;
import net.herdao.hdp.common.core.constant.enums.LoginTypeEnum;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.*;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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
@AllArgsConstructor
public class RecruitmentServiceImpl extends ServiceImpl<RecruitmentMapper, Recruitment> implements RecruitmentService {
    private final RedisTemplate redisTemplate;

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
    @OperationEntity(operation = "修改更新人才简历-个人基本情况",module="人才简历", clazz = RecruitmentDetailsDTO.class)
    public RecruitmentEditBaseInfo updateBaseInfo(RecruitmentEditBaseInfo dto) {
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
    public RecruitmentJobIntentDTO fetchResumeJobIntent(Long id) {
        RecruitmentJobIntentDTO result = this.baseMapper.fetchResumeJobIntent(id);
        return result;
    }

    @Override
    @OperationEntity(operation = "人才简历-从业情况与求职意向-更新",module="人才简历", clazz = RecruitmentJobIntentDTO.class)
    public RecruitmentJobIntentDTO updateRecruitmentJobIntent(RecruitmentJobIntentDTO dto) {
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

    @Override
    public RecruitmentPersonDTO fetchRecruitmentPerson(Long id) {
        RecruitmentPersonDTO recruitmentPersonDTO = this.baseMapper.fetchRecruitmentPerson(id);
        return recruitmentPersonDTO;
    }

    @Override
    public RecruitmentIntentDTO fetchRecruitmentIntent(Long id) {
        RecruitmentIntentDTO recruitmentIntentDTO = this.baseMapper.fetchRecruitmentIntent(id);
        return recruitmentIntentDTO;
    }

    @Override
    public RecruitmentTopEduDTO fetchRecruitmentTopEdu(Long id) {
        RecruitmentTopEduDTO recruitmentTopEduDTO = this.baseMapper.fetchRecruitmentTopEdu(id);
        return recruitmentTopEduDTO;
    }

    @Override
    public R recruitmentLogin(String mobile, String code) {
        String key = CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile;
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        if (!redisTemplate.hasKey(key)) {
            return  R.failed("验证码不合法");
        }

        String redisCode = (String)redisTemplate.opsForValue().get(key);

        if (StrUtil.isBlank(redisCode)) {
            redisTemplate.delete(key);
            return  R.failed("验证码不合法");
        }

        if (!StrUtil.equals(redisCode, code)) {
            return  R.failed("验证码不合法");
        }
        List<Recruitment> recruitments = this.baseMapper.selectList(Wrappers.<Recruitment>query().lambda().eq(Recruitment::getMobile, mobile));
        if(recruitments.isEmpty() || recruitments.size() != 1){
            return  R.failed("手机号不合法");
        }
        return R.ok(recruitments.get(0).getId());

    }

    @Override
    public RecruitmentOtherInfo fetchRecruitmentOtherInfo(Long id) {
        RecruitmentOtherInfo recruitmentOtherInfo = this.baseMapper.fetchRecruitmentOtherInfo(id);
        return recruitmentOtherInfo;
    }

    @Override
    public RecruitmentBaseInfo fetchRecruitmentBaseInfo(Long id) {
        RecruitmentBaseInfo recruitmentBaseInfo = this.baseMapper.fetchRecruitmentBaseInfo(id);
        return recruitmentBaseInfo;
    }

    @Override
    public RecruitmentEditOtherInfo updateOtherInfo(RecruitmentEditOtherInfo otherInfo) {
        Recruitment recruitment=new Recruitment();
        BeanUtils.copyProperties(otherInfo,recruitment);
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitment.setModifierTime(LocalDateTime.now());
        recruitment.setModifierCode(sysUser.getUsername());
        recruitment.setModifierName(sysUser.getAliasName());
        super.updateById(recruitment);
        BeanUtils.copyProperties(recruitment,otherInfo);

        return otherInfo;
    }
}
