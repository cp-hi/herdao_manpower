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
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 集团表
 *
 * @author yangrr
 * @date 2020-09-11 11:57:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/group" )
@Api(value = "group", tags = "集团表管理")
public class GroupController {

    private final GroupService groupService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param mpGroup 集团表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_view')" )
    public R getMpGroupPage(Page page, Group mpGroup) {
        return R.ok(groupService.page(page, Wrappers.query(mpGroup)));
    }


    /**
     * 通过id查询集团表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(groupService.getById(id));
    }

    /**
     * 新增集团表
     * @param mpGroup 集团表
     * @return R
     */
    @ApiOperation(value = "新增集团表", notes = "新增集团表")
    @SysLog("新增集团表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_add')" )
    public R save(@RequestBody Group mpGroup) {
        return R.ok(groupService.save(mpGroup));
    }

    /**
     * 修改集团表
     * @param mpGroup 集团表
     * @return R
     */
    @ApiOperation(value = "修改集团表", notes = "修改集团表")
    @SysLog("修改集团表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_edit')" )
    public R updateById(@RequestBody Group mpGroup) {
        return R.ok(groupService.updateById(mpGroup));
    }

    /**
     * 通过id删除集团表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除集团表", notes = "通过id删除集团表")
    @SysLog("通过id删除集团表" )
    @DeleteMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(groupService.removeById(id));
    }

}
