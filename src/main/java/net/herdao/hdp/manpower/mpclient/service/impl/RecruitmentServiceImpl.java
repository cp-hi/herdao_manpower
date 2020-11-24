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
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentUpdateFormVO;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.mapper.RecruitmentMapper;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
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
    public R<RecruitmentUpdateFormVO> updateRecruitment(RecruitmentUpdateFormVO recruitmentUpdateFormVO) {
        Recruitment recruitment=new Recruitment();
        BeanUtils.copyProperties(recruitmentUpdateFormVO,recruitment);

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

        SysUser sysUser = SysUserUtils.getSysUser();
        recruitment.setModifierTime(LocalDateTime.now());
        recruitment.setModifierCode(sysUser.getUsername());
        recruitment.setModifierName(sysUser.getAliasName());
        super.updateById(recruitment);

        BeanUtils.copyProperties(recruitment, recruitmentUpdateFormVO);
        return R.ok(recruitmentUpdateFormVO,"快速编辑-保存成功");
    }

    /**
     * 校检手机号码
     * @param recruitmentUpdateFormVO
     * @return
     */
    private boolean checkMobile(RecruitmentUpdateFormVO recruitmentUpdateFormVO) {
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
    private boolean checkTalentNameAndMobile(RecruitmentUpdateFormVO recruitmentUpdateFormVO) {
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
    private boolean checkEmail(RecruitmentUpdateFormVO recruitmentUpdateFormVO) {
        if (ObjectUtil.isNotEmpty(recruitmentUpdateFormVO.getEmail())){
            LambdaQueryWrapper<Recruitment> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq( Recruitment::getEmail,recruitmentUpdateFormVO.getEmail());
            List<Recruitment> list = this.list(queryWrapper);
            return ObjectUtil.isNotEmpty(list) && list.size() >= 2;
        }
        return false;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
