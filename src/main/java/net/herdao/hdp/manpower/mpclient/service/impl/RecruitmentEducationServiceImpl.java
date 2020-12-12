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
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentEduDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentTopEduDTO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentEducation;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentEducationMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentEducationService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 人才教育情况
 *
 * @author Andy
 * @date 2020-11-27 08:57:26
 */
@Service
public class RecruitmentEducationServiceImpl extends ServiceImpl<RecruitmentEducationMapper, RecruitmentEducation> implements RecruitmentEducationService {


    @Override
    public Page<RecruitmentEduDTO> fetchResumeEduPage(Page page, Long recruitmentId) {
        Page<RecruitmentEduDTO> pageResult = this.baseMapper.fetchResumeEduPage(page, recruitmentId);
        return pageResult;
    }

    /**
     * @description 人才简历-教育情况-新增保存
     * @date 2020-10-19 10:22:19
     * @version 1.0
     */
    @Override
    //@OperationEntity(operation = "人才简历-教育情况-新增或修改",module="人才简历", clazz = RecruitmentEduDTO.class)
    public RecruitmentEduDTO saveEdu(RecruitmentEduDTO dto) {
        RecruitmentEducation education=new RecruitmentEducation();
        BeanUtils.copyProperties(dto,education);

        SysUser sysUser = SysUserUtils.getSysUser();
        education.setCreatorTime(LocalDateTime.now());
        education.setCreatorCode(sysUser.getUsername());
        education.setCreatorName(sysUser.getAliasName());

        BeanUtils.copyProperties(education,dto);
        super.save(education);

        //更新人才表的最高学历信息
        updateHeightEdu(education.getRecruitmentId());
        return dto;
    }

    /**
     * @description 人才简历-教育情况-修改更新
     * @date 2020-10-19 10:22:19
     * @version 1.0
     */
    @Override
    //@OperationEntity(operation = "人才简历-教育情况-新增或修改",module="人才简历", clazz = RecruitmentEduDTO.class)
    public RecruitmentEduDTO updateEdu(RecruitmentEduDTO dto) {
        RecruitmentEducation education=new RecruitmentEducation();
        BeanUtils.copyProperties(dto,education);

        SysUser sysUser = SysUserUtils.getSysUser();
        education.setModifierTime(LocalDateTime.now());
        education.setModifierCode(sysUser.getUsername());
        education.setModifierName(sysUser.getAliasName());

        BeanUtils.copyProperties(education,dto);
        super.updateById(education);

        return dto;
    }

    @Override
    public List<RecruitmentEduDTO> fetchResumeEduList(Long recruitmentId) {
        List<RecruitmentEduDTO> list = this.baseMapper.fetchResumeEduList(recruitmentId);
        return list;
    }

    /**
     * 更新人才简历表（人才表）的最高学历的信息
     * @param recruitmentId 人才ID
     */
    private void updateHeightEdu(Long recruitmentId){
        //获取人才教育表的最高学历信息
        LambdaQueryWrapper<RecruitmentEducation> eduQueryWrapper = Wrappers.lambdaQuery();
        eduQueryWrapper.eq( RecruitmentEducation::getRecruitmentId,recruitmentId).orderByDesc(RecruitmentEducation::getPeriod);
        List<RecruitmentEducation> eduList = super.list(eduQueryWrapper);
        if (ObjectUtil.isNotEmpty(eduList)){
            //更新人才表的最高学历教育信息
            Recruitment recruitment=new Recruitment();
            if (ObjectUtil.isNotEmpty(eduList)){
                RecruitmentEducation education = eduList.get(0);
                recruitment.setBeginDate(education.getPeriod());
                recruitment.setEndDate(education.getTodate());
                recruitment.setHighestEducation(education.getEducationQua());
                recruitment.setEducationDegree(education.getDegree());
                recruitment.setGraduated(education.getSchoolName());
                recruitment.setProfessional(education.getProfessional());
                recruitment.setLearnForm(education.getLearnForm());
            }
        }

    }
}
