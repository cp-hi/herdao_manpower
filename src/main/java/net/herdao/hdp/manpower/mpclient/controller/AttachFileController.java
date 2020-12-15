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
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;
import net.herdao.hdp.manpower.mpclient.service.AttachFileService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;


/**
 * 通用附件表
 *
 * @author Andy
 * @date 2020-12-15 10:55:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/attachFile" )
@Api(value = "attachFile", tags = "通用附件表管理")
public class AttachFileController {

    private final  AttachFileService attachFileService;

    /**
     * 通用附件表
     * @param page 分页对象
     * @param attachFile 通用附件表
     * @return
     */
    @ApiOperation(value = "通用附件表", notes = "通用附件表")
    @GetMapping("/getAttachFilePage" )
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_view')" )
    public R<Page<AttachFile>> getAttachFilePage(Page page, AttachFile attachFile) {
        Page<AttachFile> pageResult = attachFileService.page(page, Wrappers.query(attachFile));
        return R.ok(pageResult);
    }


    /**
     * 通过id查询通用附件表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询附件详情", notes = "通过id查询附件详情")
    @PostMapping("/getAttachFileById" )
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_view')" )
    public R<AttachFile> getAttachFileById(Long id) {
        AttachFile file = attachFileService.getById(id);
        return R.ok(file);
    }

    /**
     * 新增通用附件表
     * @param attachFile 通用附件表
     * @return R
     */
    @ApiOperation(value = "新增通用附件表", notes = "新增通用附件表")
    @SysLog("新增通用附件表" )
    @PostMapping
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_add')" )
    public R<AttachFile> save(@RequestBody AttachFile attachFile) {
        attachFileService.save(attachFile);
        return R.ok(attachFile);
    }

    /**
     * 修改通用附件表
     * @param attachFile 通用附件表
     * @return R
     */
    @ApiOperation(value = "修改通用附件表", notes = "修改通用附件表")
    @PostMapping("/updateFileById")
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_edit')" )
    public R<AttachFile> updateById(@RequestBody AttachFile attachFile) {
       attachFileService.updateById(attachFile);
        return R.ok(attachFile);
    }

    /**
     * 通过id删除通用附件表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "删除附件", notes = "通过id删除通用附件表")
    @PostMapping("/del")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键id")
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_attachfile_del')" )
    public R removeById(Long id) {
        return R.ok(attachFileService.removeById(id));
    }

}
