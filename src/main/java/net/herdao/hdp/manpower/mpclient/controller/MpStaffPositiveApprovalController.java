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

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.MpStaffPositiveApproval;
import net.herdao.hdp.manpower.mpclient.service.MpStaffPositiveApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



/**
 * 转正审批表
 *
 * @Author cp
 * @CreateDate 2020/12/8
 * @Email soul_thought@163.com
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mpstaffpositiveapproval" )
//@RequestMapping("/mp" )
@Api(value = "mpstaffpositiveapproval", tags = "转正审批表管理")
public class MpStaffPositiveApprovalController {

    @Autowired
    private  MpStaffPositiveApprovalService mpStaffPositiveApprovalService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param mpStaffPositiveApproval 转正审批表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('generator_mpstaffpositiveapproval_view')" )
    public R getMpStaffPositiveApprovalPage(Page<MpStaffPositiveApproval> page, MpStaffPositiveApproval mpStaffPositiveApproval) {
        return R.ok(mpStaffPositiveApprovalService.getPageInfo(page,mpStaffPositiveApproval));
    }


    /**
     * 通过id查询转正审批表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_mpstaffpositiveapproval_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(mpStaffPositiveApprovalService.getById(id));
    }

    /**
     * 新增转正审批表
     * @param mpStaffPositiveApproval 转正审批表
     * @return R
     */
    @ApiOperation(value = "新增转正审批表", notes = "新增转正审批表")
    @SysLog("新增转正审批表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('generator_mpstaffpositiveapproval_add')" )
    public R save(@RequestBody MpStaffPositiveApproval mpStaffPositiveApproval) {
        return R.ok(mpStaffPositiveApprovalService.save(mpStaffPositiveApproval));
    }

    /**
     * 修改转正审批表
     * @param mpStaffPositiveApproval 转正审批表
     * @return R
     */
    @ApiOperation(value = "修改转正审批表", notes = "修改转正审批表")
    @SysLog("修改转正审批表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('generator_mpstaffpositiveapproval_edit')" )
    public R updateById(@RequestBody MpStaffPositiveApproval mpStaffPositiveApproval) {
        return R.ok(mpStaffPositiveApprovalService.updateById(mpStaffPositiveApproval));
    }

    /**
     * 通过id删除转正审批表
     * @param ids id
     * @return R
     */
    @ApiOperation(value = "通过id删除转正审批表", notes = "通过id删除转正审批表")
    @SysLog("通过id删除转正审批表" )
    @DeleteMapping("/{ids}" )
//    @PreAuthorize("@pms.hasPermission('generator_mpstaffpositiveapproval_del')" )
    public R removeById(@PathVariable String ids) {
        String[] inputIds = ids.split(StringPool.COMMA);
        mpStaffPositiveApprovalService.deleteById(inputIds);
        return R.ok();
    }

}
