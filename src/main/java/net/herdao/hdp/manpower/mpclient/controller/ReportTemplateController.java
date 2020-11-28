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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.entity.ReportTemplate;
import net.herdao.hdp.manpower.mpclient.service.ReportTemplateService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 报表模板表
 *
 * @author hsh
 * @date 2020-11-28 11:02:34
 */
@RestController
@AllArgsConstructor
@RequestMapping("/reporttelate" )
@Api(value = "reporttelate", tags = "报表模板表管理")
public class ReportTemplateController {

    private final ReportTemplateService reportTemplateService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param reportTemplate 报表模板表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    //@PreAuthorize("@pms.hasPermission('client_reporttelate_view')" )
    public R getReportTelatePage(Page page, ReportTemplate reportTemplate,String seachText) {
        return R.ok(reportTemplateService.findReportTelatePage(page, reportTemplate,seachText));
    }


    /**
     * 通过id查询报表模板表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    //@PreAuthorize("@pms.hasPermission('client_reporttemplate_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(reportTemplateService.getById(id));
    }

    /**
     * 新增报表模板表
     * @param reportTemplate 报表模板表
     * @return R
     */
    @ApiOperation(value = "新增报表模板表", notes = "新增报表模板表")
    @SysLog("新增报表模板表" )
    @PostMapping
    //@PreAuthorize("@pms.hasPermission('client_reporttemplate_add')" )
    public R<Boolean> save(@RequestBody ReportTemplate reportTemplate) {
        int count = reportTemplateService.count(Wrappers.<ReportTemplate>lambdaQuery().eq(ReportTemplate::getCode, reportTemplate.getCode()));
        if(count>0){
            return R.failed("编码已存在");
        }
        reportTemplate.setCreatorCode(SysUserUtils.getSysUser().getUsername());
        reportTemplate.setCreatorName(SysUserUtils.getSysUser().getAliasName());
        reportTemplate.setCreatorTime(LocalDateTime.now());
        return R.ok(reportTemplateService.save(reportTemplate));
    }

    /**
     * 修改报表模板表
     * @param reportTemplate 报表模板表
     * @return R
     */
    @ApiOperation(value = "修改报表模板表", notes = "修改报表模板表")
    @SysLog("修改报表模板表" )
    @PutMapping
    //@PreAuthorize("@pms.hasPermission('client_reporttelate_edit')" )
    public R updateById(@RequestBody ReportTemplate reportTemplate) {
        ReportTemplate byId = reportTemplateService.getById(reportTemplate.getId());
        if(!byId.getCode().equals(reportTemplate.getCode())){
            int count = reportTemplateService.count(Wrappers.<ReportTemplate>lambdaQuery().eq(ReportTemplate::getCode, reportTemplate.getCode()));
            if(count>0){
                return R.failed("编码已存在");
            }
        }
        reportTemplate.setModifierCode(SysUserUtils.getSysUser().getUsername());
        reportTemplate.setModifierName(SysUserUtils.getSysUser().getAliasName());
        reportTemplate.setModifierTime(LocalDateTime.now());
        return R.ok(reportTemplateService.updateById(reportTemplate));
    }

    /**
     * 通过id删除报表模板表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除报表模板表", notes = "通过id删除报表模板表")
    @SysLog("通过id删除报表模板表" )
    @DeleteMapping("/{id}" )
    //@PreAuthorize("@pms.hasPermission('client_reporttelate_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(reportTemplateService.removeById(id));
    }

}
