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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentUpdateFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.workExperience.RecruitmentWorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentWorkexperience;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentWorkexperienceService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 人才工作经历表
 *
 * @author Andy
 * @date 2020-11-26 09:18:33
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmentworkexperience")
@Api(value = "recruitmentworkexperience", tags = "人才工作经历表管理")
public class RecruitmentWorkexperienceController {

    private final RecruitmentWorkexperienceService recruitmentWorkexperienceService;

    /**
     * 人才简历-工作情况-列表
     * @param page          分页对象
     * @param recruitmentId 人才ID
     * @return
     */
    @ApiOperation(value = "人才简历-工作情况-列表", notes = "人才简历-工作情况-列表")
    @GetMapping("/page")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页实体", required = true),
        @ApiImplicitParam(name = "recruitmentId", value = "人才ID", required = true)
    })
    public R<Page<RecruitmentWorkexperienceDTO>> getRecruitmentWorkexperiencePage(Page page, Long recruitmentId) {
        RecruitmentWorkexperience workexperience = new RecruitmentWorkexperience();
        workexperience.setRecruitmentId(recruitmentId);
        Page<RecruitmentWorkexperienceDTO> pageResult = recruitmentWorkexperienceService.page(page, Wrappers.query(workexperience));
        return R.ok(pageResult);
    }

    /**
     * 人才简历-工作情况-编辑
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才简历-工作情况-编辑", notes = "人才简历-工作情况-编辑")
    @GetMapping("/fetchDetails/{id}")
    public R<RecruitmentWorkexperienceDTO> fetchDetails(@PathVariable("id") Long id) {
        RecruitmentWorkexperience result = recruitmentWorkexperienceService.getById(id);
        RecruitmentWorkexperienceDTO dto = new RecruitmentWorkexperienceDTO();
        BeanUtils.copyProperties(result, dto);
        return R.ok(dto);
    }

    /**
     * 人才简历-工作情况-新增
     * @param dto 人才工作经历表
     * @return R
     */
    @ApiOperation(value = "人才简历-工作情况-新增", notes = "人才简历-工作情况-新增")
    @PostMapping("/saveWorkExperience")
    public R<RecruitmentWorkexperienceDTO> saveWorkExperience(@RequestBody RecruitmentWorkexperienceDTO dto) {
        RecruitmentWorkexperienceDTO result = recruitmentWorkexperienceService.saveWorkExperience(dto);
        return R.ok(result);
    }

    /**
     * 人才简历-工作情况-更新
     * @param dto 人才工作经历表
     * @return R
     */
    @ApiOperation(value = "才简历-工作情况-更新", notes = "才简历-工作情况-更新")
    @PutMapping("/updateWorkExperience")
    public R<RecruitmentWorkexperienceDTO> updateWorkExperience(@RequestBody RecruitmentWorkexperienceDTO dto) {
        RecruitmentWorkexperienceDTO result = recruitmentWorkexperienceService.updateWorkExperience(dto);
        return R.ok(result);
    }

    /**
     * 删除个人简历-工作经历
     * @param id id
     * @return R
     */
    @ApiOperation(value = "删除个人简历-工作经历", notes = "删除个人简历-工作经历")
    @SysLog("删除个人简历-工作经历")
    @DeleteMapping("/del/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentWorkexperienceService.removeById(id));
    }

    /**
     * 查看个人简历-工作经历
     *
     * @param recruitmentId 人才ID
     * @return R
     */
    @ApiOperation(value = "查看个人简历-工作经历", notes = "查看个人简历-工作经历")
    @GetMapping("/findWorkExperience")
    @ApiImplicitParams({
         @ApiImplicitParam(name = "recruitmentId", value = "人才ID", required = true)
    })
    public R<RecruitmentWorkexperienceDTO> findWorkExperience(Long recruitmentId) {
        RecruitmentWorkexperienceDTO result = recruitmentWorkexperienceService.findWorkExperience(recruitmentId);
        return R.ok(result);
    }

}
