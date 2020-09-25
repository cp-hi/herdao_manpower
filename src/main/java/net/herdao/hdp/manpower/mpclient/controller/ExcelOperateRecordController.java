/*
 *    Copyright (c) 2018-2025, hdp All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: hdp
 */

package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.ExcelOperateRecord;
import net.herdao.hdp.manpower.mpclient.service.ExcelOperateRecordService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 
 * excel 操作记录
 * @author andy
 * @date 2020-09-18 19:46:22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/exceloperaterecord" )
@Api(value = "exceloperaterecord", tags = "管理")
public class ExcelOperateRecordController {

    private final  ExcelOperateRecordService excelOperateRecordService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param excelOperateRecord 
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_exceloperaterecord_view')" )
    public R getExcelOperateRecordPage(Page page, ExcelOperateRecord excelOperateRecord) {
        return R.ok(excelOperateRecordService.page(page, Wrappers.query(excelOperateRecord)));
    }


    /**
     * 通过id查询
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_exceloperaterecord_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(excelOperateRecordService.getById(id));
    }

    /**
     * 新增
     * @param excelOperateRecord 
     * @return R
     */
    @ApiOperation(value = "新增", notes = "新增")
    @SysLog("新增" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('mpclient_exceloperaterecord_add')" )
    public R save(@RequestBody ExcelOperateRecord excelOperateRecord) {
        return R.ok(excelOperateRecordService.save(excelOperateRecord));
    }

    /**
     * 修改
     * @param excelOperateRecord 
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @SysLog("修改" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('mpclient_exceloperaterecord_edit')" )
    public R updateById(@RequestBody ExcelOperateRecord excelOperateRecord) {
        return R.ok(excelOperateRecordService.updateById(excelOperateRecord));
    }

    /**
     * 通过id删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_exceloperaterecord_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(excelOperateRecordService.removeById(id));
    }

}
