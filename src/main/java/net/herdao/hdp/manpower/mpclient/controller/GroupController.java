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

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.GroupDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.GroupListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.listener.StaffExcelListener;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


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

    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    @GetMapping("/list")
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_view')" )
    public R list() {
        return R.ok(groupService.groupList());
    }


    /**
     * 分页查询
     * @param page 分页对象
     * @param group 集团表
     * @param searchText 查询
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_view')" )
    public R getMpGroupPage(Page page, GroupListDTO group, String searchText) {
        return R.ok(groupService.groupPage(page, group, searchText));
    }


    /**
     * 通过id查询集团表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_view')" )
    public R<GroupDetailDTO> getById(@PathVariable("id" ) Long id) {
        return R.ok(groupService.getGroupById(id));
    }

    /**
     * 新增集团表
     * @param group 集团表
     * @return R
     */
    @ApiOperation(value = "新增集团表", notes = "新增集团表")
    @SysLog("新增集团表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_add')" )
    public R<Boolean> save(@RequestBody GroupDetailDTO group) {
        return R.ok(groupService.groupSave(group));
    }

    /**
     * 修改集团表
     * @param group 集团表
     * @return R
     */
    @ApiOperation(value = "修改集团表", notes = "修改集团表")
    @SysLog("修改集团表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('group_mpgroup_edit')" )
    public R<Boolean> updateById(@RequestBody GroupDetailDTO group) {
        return R.ok(groupService.groupUpdate(group));
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
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(groupService.removeById(id));
    }

    @ApiOperation("导入")
    @SysLog("导入")
    @PostMapping("/import")
    public R<String> importExcel(MultipartFile file, String editType){
        System.out.println(editType);
        try {
            EasyExcel.read(file.getInputStream(), GroupListDTO.class,
                    new StaffExcelListener<GroupListDTO, Group>(groupService, Group.class)).sheet().doRead();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return R.ok("导入成功");
    }

    @ApiOperation(value = "导出", notes = "导出")
    @GetMapping("/export" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public void export(HttpServletResponse response, Page page, GroupListDTO group, String searchText) {
        page.setCurrent(1);
        page.setSize(50000);
        IPage result = groupService.groupPage(page, group, searchText);
        long total = result.getTotal();
        if(total>50000){
            return;
        }
        List<GroupListDTO> list = result.getRecords();
        try {
            ExcelUtils.export2Web(response, "集团", "集团", GroupListDTO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
