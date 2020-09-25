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
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import net.herdao.hdp.manpower.mpclient.service.UserposthistoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 员工岗位历史表
 *
 * @author yangrr
 * @date 2020-09-25 17:24:25
 */
@RestController
@AllArgsConstructor
@RequestMapping("/userposthistory" )
@Api(value = "userposthistory", tags = "员工岗位历史表管理")
public class UserposthistoryController {

    private final  UserposthistoryService userposthistoryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param userposthistory 员工岗位历史表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('mpclient_userposthistory_view')" )
    public R getUserposthistoryPage(Page page, Userposthistory userposthistory) {
        return R.ok(userposthistoryService.page(page, Wrappers.query(userposthistory)));
    }


    /**
     * 通过id查询员工岗位历史表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_userposthistory_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(userposthistoryService.getById(id));
    }

    /**
     * 新增员工岗位历史表
     * @param userposthistory 员工岗位历史表
     * @return R
     */
    @ApiOperation(value = "新增员工岗位历史表", notes = "新增员工岗位历史表")
    @SysLog("新增员工岗位历史表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_userposthistory_add')" )
    public R save(@RequestBody Userposthistory userposthistory) {
        return R.ok(userposthistoryService.save(userposthistory));
    }

    /**
     * 修改员工岗位历史表
     * @param userposthistory 员工岗位历史表
     * @return R
     */
    @ApiOperation(value = "修改员工岗位历史表", notes = "修改员工岗位历史表")
    @SysLog("修改员工岗位历史表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_userposthistory_edit')" )
    public R updateById(@RequestBody Userposthistory userposthistory) {
        return R.ok(userposthistoryService.updateById(userposthistory));
    }

    /**
     * 通过id删除员工岗位历史表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工岗位历史表", notes = "通过id删除员工岗位历史表")
    @SysLog("通过id删除员工岗位历史表" )
    @DeleteMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_userposthistory_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(userposthistoryService.removeById(id));
    }

}
