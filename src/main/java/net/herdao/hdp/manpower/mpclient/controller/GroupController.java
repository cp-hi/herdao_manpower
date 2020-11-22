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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.GroupDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.GroupListDTO;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.group.GroupAddVM;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.group.GroupUpdateVM;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * 集团表
 *
 * @author yangrr
 * @date 2020-09-11 11:57:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/group")
@Api(value = "group", tags = "集团表管理")
public class GroupController extends HdpBaseController {

    private final GroupService groupService;

    @Override
    public HdpService getHdpService() {
        return this.groupService;
    }

    @Override
    public Class getImportAddCls() {
        return GroupAddVM.class;
    }

    @Override
    public Class getImportUpdateCls() {
        return GroupUpdateVM.class;
    }

    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    @GetMapping("/list")
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_view')" )
    public R list() {
        return R.ok(groupService.groupList());
    }


    /**
     * 分页查询
     *
     * @param page       分页对象
     * @param group      集团表
     * @param searchText 查询
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_view')" )
    public R getMpGroupPage(Page page, GroupListDTO group, String searchText) {
        return R.ok(groupService.groupPage(page, group, searchText));
    }
    @ApiOperation(value = "导出集团", notes = "导出集团")
    @GetMapping("/export" )
    @SneakyThrows
    public void export(HttpServletResponse response, GroupListDTO group, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        IPage iPage = groupService.groupPage(page, group, searchText);
        ExcelUtils.export2Web(response, "集团", "集团", GroupListDTO.class, iPage.getRecords());
    }

    /**
     * 通过id查询集团表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}")
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_view')" )
    public R<GroupDetailDTO> getById(@PathVariable("id") Long id) {
        return R.ok(groupService.getGroupById(id));
    }

    /**
     * 新增集团表
     *
     * @param group 集团表
     * @return R
     */
    @ApiOperation(value = "新增集团表", notes = "新增集团表")
   // @SysLog("新增集团表")
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_add')" )
    public R save(@RequestBody GroupDetailDTO group) {
        boolean flag = groupService.groupSave(group);
        return R.ok(group);
    }

    /**
     * 修改集团表
     *
     * @param group 集团表
     * @return R
     */
    @ApiOperation(value = "修改集团表", notes = "修改集团表")
    @SysLog("修改集团表")
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_edit')" )
    public R<Boolean> updateById(@RequestBody GroupDetailDTO group) {
        return R.ok(groupService.groupUpdate(group));
    }

    /**
     * 通过id删除集团表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除集团表", notes = "通过id删除集团表")
    @SysLog("通过id删除集团表")
    @DeleteMapping("/{id}")
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_del')" )
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(groupService.removeById(id));
    }

}
