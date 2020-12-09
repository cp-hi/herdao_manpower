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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveApprovalSaveDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveListDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPositiveApproval;
import net.herdao.hdp.manpower.mpclient.service.StaffPositiveApprovalService;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPageVO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("staff/positiveApproval" )
@Api(value = "staffPositiveApproval", tags = "转正审批表管理")
public class StaffPositiveApprovalController {

    @Autowired
    private StaffPositiveApprovalService StaffPositiveApprovalService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dto 转正审批表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('generator_StaffPositiveApproval_view')" )
    public R<Page<StaffPositiveApprovalPageVO>> getStaffPositiveApprovalPage(Page<StaffPositiveApproval> page, StaffPositiveListDTO dto) {
        return R.ok(StaffPositiveApprovalService.getPageInfo(page,dto));
    }


    /**
     * 通过id查询转正审批表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('generator_StaffPositiveApproval_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(StaffPositiveApprovalService.getById(id));
    }

    /**
     * 新增转正审批表
     * @param dto 转正审批表
     * @return R
     */
    @ApiOperation(value = "新增转正审批表", notes = "新增转正审批表")
    @SysLog("新增转正审批表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('generator_StaffPositiveApproval_add')" )
    public R<Long> save(@RequestBody @NotNull StaffPositiveApprovalSaveDTO dto) {
        return R.ok(StaffPositiveApprovalService.insert(dto));
    }

    /**
     * 修改转正审批表
     * @param StaffPositiveApproval 转正审批表
     * @return R
     */
    @ApiOperation(value = "修改转正审批表", notes = "修改转正审批表")
    @SysLog("修改转正审批表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('generator_StaffPositiveApproval_edit')" )
    public R updateById(@RequestBody StaffPositiveApproval StaffPositiveApproval) {
        return R.ok(StaffPositiveApprovalService.updateById(StaffPositiveApproval));
    }

    /**
     * 通过id删除转正审批表
     * @param ids id
     * @return R
     */
    @ApiOperation(value = "通过id删除转正审批表", notes = "通过id删除转正审批表")
    @SysLog("通过id删除转正审批表" )
    @DeleteMapping("/{ids}" )
//    @PreAuthorize("@pms.hasPermission('generator_StaffPositiveApproval_del')" )
    public R removeById(@PathVariable String ids) {
        String[] inputIds = ids.split(StringPool.COMMA);
        StaffPositiveApprovalService.deleteById(inputIds);
        return R.ok();
    }

}
