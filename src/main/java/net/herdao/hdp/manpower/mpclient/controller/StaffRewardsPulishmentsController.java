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
import net.herdao.hdp.manpower.mpclient.entity.StaffRewardsPulishments;
import net.herdao.hdp.manpower.mpclient.entity.StaffRp;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.mpclient.service.StaffRewardsPulishmentsService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 员工奖惩
 *
 * @author andy
 * @date 2020-09-25 16:26:20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffrewardspulishments" )
@Api(value = "staffrewardspulishments", tags = "员工奖惩管理")
public class StaffRewardsPulishmentsController {

    private final  StaffRewardsPulishmentsService staffRewardsPulishmentsService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffRewardsPulishments 员工奖惩
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffrewardspulishments_view')" )
    public R getStaffRewardsPulishmentsPage(Page page, StaffRewardsPulishments staffRewardsPulishments) {
        return R.ok(staffRewardsPulishmentsService.page(page, Wrappers.query(staffRewardsPulishments)));
    }


    /**
     * 通过id查询员工奖惩
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffrewardspulishments_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffRewardsPulishmentsService.getById(id));
    }

    /**
     * 新增员工奖惩
     * @param staffRewardsPulishments 员工奖惩
     * @return R
     */
    @ApiOperation(value = "新增员工奖惩", notes = "新增员工奖惩")
    @SysLog("新增员工奖惩" )
    @PostMapping("/saveStaffRp")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffrewardspulishments_add')" )
    public R saveStaffRp(@RequestBody StaffRewardsPulishments staffRewardsPulishments) {
        boolean status = staffRewardsPulishmentsService.saveStaffRp(staffRewardsPulishments);
        return R.ok(status);
    }

    /**
     * 修改员工奖惩
     * @param staffRewardsPulishments 员工奖惩
     * @return R
     */
    @ApiOperation(value = "修改员工奖惩", notes = "修改员工奖惩")
    @SysLog("修改员工奖惩" )
    @PutMapping("/updateStaff")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffrewardspulishments_edit')" )
    public R updateById(@RequestBody StaffRewardsPulishments staffRewardsPulishments) {
        boolean status = staffRewardsPulishmentsService.updateStaffRp(staffRewardsPulishments);
        return R.ok(status);
    }

    /**
     * 通过id删除员工奖惩
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工奖惩", notes = "通过id删除员工奖惩")
    @SysLog("通过id删除员工奖惩" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_staffrewardspulishments_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(staffRewardsPulishmentsService.removeById(id));
    }

    /**
     * 员工奖惩分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "员工奖惩分页", notes = "员工奖惩分页")
    @GetMapping("/findStaffRpPage")
    @OperationEntity(operation = "员工奖惩分页" ,clazz = Staffeducation.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffRpPage(Page page, String orgId,String staffName, String staffCode) {
        Page pageResult = staffRewardsPulishmentsService.findStaffRpPage(page, orgId, staffName, staffCode);
        return R.ok(pageResult);
    }

    /**
     * 导出员工异动情况Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工奖惩情况Excel", notes = "导出员工奖惩情况Excel")
    @SysLog("导出员工奖惩情况Excel")
    @PostMapping("/exportStaffRp")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    public void exportTrans(HttpServletResponse response, String orgId, String staffName, String staffCode) {
        try {
            List<StaffRewardsPulishments> list = staffRewardsPulishmentsService.findStaffRp(orgId, staffName, staffCode);
            ExcelUtils.export2Web(response, "员工奖惩情况表", "员工奖惩情况表", StaffRp.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            R.ok("导出失败");
        }

        R.ok("导出成功");
    }

}
