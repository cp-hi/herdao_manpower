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

package net.herdao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.mpclient.entity.Staff;
import net.herdao.hdp.mpclient.service.StaffService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 员工表
 *
 * @author yangrr
 * @date 2020-09-23 18:10:29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staff" )
@Api(value = "staff", tags = "员工表管理")
public class StaffController {

    private final  StaffService staffService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staff 员工表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R getStaffPage(Page page, Staff staff) {
        return R.ok(staffService.page(page, Wrappers.query(staff)));
    }


    /**
     * 通过id查询员工表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffService.getById(id));
    }

    /**
     * 新增员工表
     * @param staff 员工表
     * @return R
     */
    @ApiOperation(value = "新增员工表", notes = "新增员工表")
    @SysLog("新增员工表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_add')" )
    public R save(@RequestBody Staff staff) {
        return R.ok(staffService.save(staff));
    }

    /**
     * 修改员工表
     * @param staff 员工表
     * @return R
     */
    @ApiOperation(value = "修改员工表", notes = "修改员工表")
    @SysLog("修改员工表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_edit')" )
    public R updateById(@RequestBody Staff staff) {
        return R.ok(staffService.updateById(staff));
    }

    /**
     * 通过id删除员工表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工表", notes = "通过id删除员工表")
    @SysLog("通过id删除员工表" )
    @DeleteMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(staffService.removeById(id));
    }

}
