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

package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentAwardsDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentUpdateFormDTO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentAwards;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentAwardsService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 人才获奖情况表
 *
 * @author Andy
 * @date 2020-11-26 18:51:47
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmentawards" )
@Api(value = "recruitmentawards", tags = "人才获奖情况表管理")
public class RecruitmentAwardsController {

    private final  RecruitmentAwardsService recruitmentAwardsService;

    /**
     * 人才简历-获奖情况-编辑
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才简历-获奖情况-编辑", notes = "人才简历-获奖情况-编辑")
    @GetMapping("/fetchDetails/{id}" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键id")
    })
    public R<RecruitmentAwardsDTO> fetchDetails(@PathVariable("id" ) Long id) {
        RecruitmentAwards awards= recruitmentAwardsService.getById(id);
        RecruitmentAwardsDTO dto=new RecruitmentAwardsDTO();
        if (ObjectUtil.isNotNull(awards)){
            BeanUtils.copyProperties(awards,dto);
        }

        return R.ok(dto);
     }

    /**
     * 人才简历-获奖情况-新增
     * @param dto 人才获奖情况表
     * @return R
     */
    @ApiOperation(value = "人才简历-获奖情况-新增", notes = "人才简历-获奖情况-新增")
    @PostMapping("/saveAwards")
    public R<RecruitmentAwardsDTO> saveAwards(@RequestBody RecruitmentAwardsDTO dto) {
        RecruitmentAwardsDTO result = recruitmentAwardsService.saveOrUpdate(dto);
        return R.ok(result);
    }

    /**
     * 人才简历-获奖情况-更新
     * @param dto 人才获奖情况表
     * @return R
     */
    @ApiOperation(value = "人才简历-获奖情况-更新", notes = "人才简历-获奖情况-更新")
    @PutMapping("/updateAwards")
    public R<RecruitmentAwardsDTO> updateAwards(@RequestBody RecruitmentAwardsDTO dto) {
        RecruitmentAwardsDTO result = recruitmentAwardsService.saveOrUpdate(dto);
        return R.ok(result);
    }

    /**
     * 人才简历-获奖情况-删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才简历-获奖情况-删除", notes = "人才简历-获奖情况-删除")
    @DeleteMapping("/del/{id}" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键id")
    })
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentAwardsService.removeById(id));
    }


    /**
     * 人才简历-获奖情况-列表
     * @param page 分页对象
     * @param recruitmentId 人才ID
     * @return
     */
    @ApiOperation(value = "人才简历-获奖情况-列表", notes = "人才简历-获奖情况-列表")
    @GetMapping("/page" )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页对象", required = true),
        @ApiImplicitParam(name = "recruitmentId", value = "人才ID", required = true)
    })
    public R<Page<RecruitmentAwardsDTO>> getRecruitmentAwardsPage(Page page, Long recruitmentId) {
        Page<RecruitmentAwardsDTO> pageResult = recruitmentAwardsService.fetchResumeAwardsPage(page, recruitmentId);
        return R.ok(pageResult);
    }
}
