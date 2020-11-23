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
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitment" )
@Api(value = "recruitment", tags = "人才表管理")
public class RecruitmentController {

    private final  RecruitmentService recruitmentService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param recruitment 人才表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_recruitment_view')" )
    public R getRecruitmentPage(Page page, Recruitment recruitment) {
        return R.ok(recruitmentService.page(page, Wrappers.query(recruitment)));
    }


    /**
     * 通过id查询人才表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询人才表", notes = "通过id查询人才表")
    @GetMapping("/{id}" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键id")
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitment_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(recruitmentService.getById(id));
    }

    /**
     * 新增或更新人才表
     * @param recruitment 人才表
     * @return R
     */
    @ApiOperation(value = "新增或更新人才表", notes = "新增或更新人才表")
    @PostMapping
    @ApiImplicitParams({
         @ApiImplicitParam(name="id",value="主键id"),
         @ApiImplicitParam(name="talentName",value="姓名"),
         @ApiImplicitParam(name="mobile",value="手机号码"),
         @ApiImplicitParam(name="talentType",value="人才类别"),
         @ApiImplicitParam(name="email",value="个人邮箱"),
         @ApiImplicitParam(name="birthday",value="出生年月日"),
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitment_add')" )
    public R saveOrUpdate(@RequestBody Recruitment recruitment) {
        recruitmentService.saveOrUpdate(recruitment);
        return R.ok();
    }

    /**
     * 修改人才表
     * @param recruitment 人才表
     * @return R
     */
    @ApiOperation(value = "修改人才表", notes = "修改人才表")
    @SysLog("修改人才表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('mpclient_recruitment_edit')" )
    public R updateById(@RequestBody Recruitment recruitment) {
        return R.ok(recruitmentService.updateById(recruitment));
    }

    /**
     * 通过id删除人才表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除人才表", notes = "通过id删除人才表")
    @SysLog("通过id删除人才表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_recruitment_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentService.removeById(id));
    }


    /**
     * 人才表分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    @ApiOperation(value = "人才表分页", notes = "人才表分页")
    @GetMapping("/findRecruitmentPage")
    @ApiImplicitParams({
         @ApiImplicitParam(name="page",value="分页对象"),
         @ApiImplicitParam(name="recruitmentDTO",value="人才表DTO"),
         @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findRecruitmentPage(Page page, RecruitmentDTO recruitmentDTO, String searchText) {
        Page pageResult = recruitmentService.findRecruitmentPage(page, recruitmentDTO, searchText);
        return R.ok(pageResult);
    }
}
