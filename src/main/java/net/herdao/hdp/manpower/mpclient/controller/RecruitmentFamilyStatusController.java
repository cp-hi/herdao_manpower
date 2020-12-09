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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentFamilyDTO;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentFamilyStatus;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentFamilyStatusService;
import org.springframework.beans.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 人才家庭情况
 *
 * @author Andy
 * @date 2020-11-26 15:28:43
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmentfamilystatus")
@Api(value = "recruitmentfamilystatus", tags = "简历库-人才家庭情况管理")
public class RecruitmentFamilyStatusController {

    private final RecruitmentFamilyStatusService recruitmentFamilyStatusService;
 

    /**
     * 编辑个人简历-家庭情况-详情
     * @param id 主键ID
     * @return R
     */
    @ApiOperation(value = "编辑个人简历-家庭情况-详情", notes = "编辑个人简历-家庭情况-详情")
    @GetMapping("/fetchDetailsById")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    })
    public R<RecruitmentFamilyDTO> fetchDetailsById(Long id) {
        RecruitmentFamilyStatus familyStatus = recruitmentFamilyStatusService.getById(id);
        RecruitmentFamilyDTO dto=new RecruitmentFamilyDTO();
        BeanUtils.copyProperties(familyStatus,dto);
        return R.ok(dto);
    }

    /**
     * 个人简历-家庭情况-新增保存
     * @param dto 人才家庭情况
     * @return R
     */
    @ApiOperation(value = "个人简历-家庭情况-新增保存", notes = "个人简历-家庭情况-新增保存")
    @PostMapping("/saveFamily")
    public R<RecruitmentFamilyDTO> saveFamily(@RequestBody RecruitmentFamilyDTO dto) {
        RecruitmentFamilyDTO result = recruitmentFamilyStatusService.saveFamily(dto);
        return R.ok(result);
    }

    /**
     * 个人简历-家庭情况-修改更新
     * @param dto 人才家庭情况
     * @return R
     */
    @ApiOperation(value = "个人简历-家庭情况-修改更新", notes = "个人简历-家庭情况-修改更新")
    @PostMapping("/updateFamily")
    public R<RecruitmentFamilyDTO> updateFamily(@RequestBody RecruitmentFamilyDTO dto) {
        RecruitmentFamilyDTO result = recruitmentFamilyStatusService.updateFamily(dto);
        return R.ok(result);
    }

    /**
     * 人才简历-家庭情况-删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才简历-家庭情况-删除", notes = "人才简历-家庭情况-删除")
    @DeleteMapping("/del/{id}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "ID", required = true)
    })
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentFamilyStatusService.removeById(id));
    }




}
