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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.StaffProfession;
import net.herdao.hdp.manpower.mpclient.service.StaffProfessionService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 员工职称及职业资料
 *
 * @author andy
 * @date 2020-10-09 10:53:38
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffprofession" )
@Api(value = "staffprofession", tags = "员工职称及职业资料管理")
public class StaffProfessionController {

    private final  StaffProfessionService staffProfessionService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffProfession 员工职称及职业资料
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffprofession_view')" )
    public R getStaffProfessionPage(Page page, StaffProfession staffProfession) {
        return R.ok(staffProfessionService.page(page, Wrappers.query(staffProfession)));
    }


    /**
     * 通过id查询员工职称及职业资料
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffprofession_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffProfessionService.getById(id));
    }

    /**
     * 新增员工职称及职业资料
     * @param staffProfession 员工职称及职业资料
     * @return R
     */
    @ApiOperation(value = "新增员工职称及职业资料", notes = "新增员工职称及职业资料")
    @SysLog("新增员工职称及职业资料" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('mpclient_staffprofession_add')" )
    public R save(@RequestBody StaffProfession staffProfession) {
        return R.ok(staffProfessionService.save(staffProfession));
    }

    /**
     * 新增或修改员工职称及职业资料
     * @param staffProfession 新增或修改员工职称及职业资料
     * @return R
     */
    @ApiOperation(value = "新增或修改员工职称及职业资料", notes = "新增或修改员工职称及职业资料")
    @SysLog("新增或修改员工职称及职业资料" )
    @PostMapping("/saveOrUpdate")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffprofession_add')" )
    public R saveOrUpdate(@RequestBody StaffProfession staffProfession) {
        boolean status = staffProfessionService.saveOrUpdate(staffProfession);
        return R.ok(status);
    }

    /**
     * 修改员工职称及职业资料
     * @param staffProfession 员工职称及职业资料
     * @return R
     */
    @ApiOperation(value = "修改员工职称及职业资料", notes = "修改员工职称及职业资料")
    @SysLog("修改员工职称及职业资料" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('mpclient_staffprofession_edit')" )
    public R updateById(@RequestBody StaffProfession staffProfession) {
        return R.ok(staffProfessionService.updateById(staffProfession));
    }

    /**
     * 通过id删除员工职称及职业资料
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工职称及职业资料", notes = "通过id删除员工职称及职业资料")
    @SysLog("通过id删除员工职称及职业资料" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_staffprofession_del')" )
    @OperationEntity(operation = "删除员工职称及职业资料", clazz = StaffProfession.class)
    public R removeById(@PathVariable Long id) {
        return R.ok(staffProfessionService.removeById(id));
    }

    /**
     * 员工职称及职业资料分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "员工职称及职业资料分页", notes = "员工职称及职业资料分页")
    @GetMapping("/findStaffProPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffProPage(Page page, String orgId,String staffName, String staffCode) {
        Page pageResult = staffProfessionService.findStaffProPage(page, orgId, staffName, staffCode);
        return R.ok(pageResult);
    }

}
