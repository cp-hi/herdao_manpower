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
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentActiviti;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentActivitiService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 人才简历-人才活动
 *
 * @author Andy
 * @date 2020-12-02 20:12:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmentactiviti" )
@Api(value = "recruitmentactiviti", tags = "人才简历-人才活动表管理")
public class RecruitmentActivitiController {

    private final  RecruitmentActivitiService recruitmentActivitiService;

    /**
     * 人才简历-人才活动-分页列表
     * @param page 分页对象
     * @param recruitmentId 人才ID
     * @return
     */
    @ApiOperation(value = "人才简历-人才活动-分页列表", notes = "人才简历-人才活动-分页列表")
    @GetMapping("/getRecruitmentActivitiPage" )
    @ApiImplicitParams({
         @ApiImplicitParam(name="recruitmentId",value="人才ID",required = true),
    })
    public R<Page<RecruitmentActiviti>> getRecruitmentActivitiPage(Page page, Long recruitmentId) {
        RecruitmentActiviti recruitmentActiviti=new RecruitmentActiviti();
        if (ObjectUtil.isNotNull(recruitmentId)){
            recruitmentActiviti.setRecruitmentId(recruitmentId);
        }
        Page<RecruitmentActiviti> pageResult = recruitmentActivitiService.page(page, Wrappers.query(recruitmentActiviti));
        return R.ok(pageResult);
    }

    /**
     * 人才简历-人才活动-详情
     * @param id 主键ID
     * @return R
     */
    @ApiOperation(value = "人才简历-人才活动-详情", notes = "人才简历-人才活动-详情")
    @GetMapping("/fetchDetails/{id}" )
    @ApiImplicitParams({
       @ApiImplicitParam(name="id",value="主键ID",required = true),
    })
    public R<RecruitmentActiviti> fetchDetails(@PathVariable("id" ) Long id) {
        RecruitmentActiviti recruitmentActiviti = recruitmentActivitiService.getById(id);
        return R.ok(recruitmentActiviti);
    }

    /**
     * 人才简历-人才活动-新增保存
     * @param recruitmentActiviti 人才活动表
     * @return R
     */
    @ApiOperation(value = "人才简历-人才活动-新增保存", notes = "人才简历-人才活动-新增保存")
    @PostMapping("/saveActiviti")
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmentactiviti_add')" )
    public R<RecruitmentActiviti> saveActiviti(@RequestBody RecruitmentActiviti recruitmentActiviti) {
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitmentActiviti.setCreatorTime(LocalDateTime.now());
        recruitmentActiviti.setCreatorCode(sysUser.getUsername());
        recruitmentActiviti.setCreatorName(sysUser.getAliasName());
        recruitmentActivitiService.save(recruitmentActiviti);
        return R.ok(recruitmentActiviti);
    }

    /**
     * 人才简历-人才活动-修改更新
     * @param recruitmentActiviti 人才活动表
     * @return R
     */
    @ApiOperation(value = "人才简历-人才活动-修改更新", notes = "人才简历-人才活动-修改更新")
    @PutMapping("/updateActiviti")
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmentactiviti_edit')" )
    public R<RecruitmentActiviti> updateActiviti(@RequestBody RecruitmentActiviti recruitmentActiviti) {
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitmentActiviti.setModifierTime(LocalDateTime.now());
        recruitmentActiviti.setModifierCode(sysUser.getUsername());
        recruitmentActiviti.setModifierName(sysUser.getAliasName());
        recruitmentActivitiService.updateById(recruitmentActiviti);
        return R.ok(recruitmentActiviti);
    }

    /**
     * 人才简历-人才活动-删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才简历-人才活动-删除", notes = "人才简历-人才活动-删除")
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmentactiviti_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentActivitiService.removeById(id));
    }

}
