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
import net.herdao.hdp.manpower.mpclient.entity.OrgReport;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.service.StaffcontractService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * 员工合同签订
 *
 * @author liang
 * @date 2020-09-27 09:15:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffcontract" )
@Api(value = "staffcontract", tags = "员工合同签订管理")
public class StaffcontractController {

    private final  StaffcontractService staffcontractService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffcontract 员工合同签订
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffcontract_view')" )
    public R getStaffcontractPage(Page page, Staffcontract staffcontract) {
        return R.ok(staffcontractService.page(page, Wrappers.query(staffcontract)));
    }


    /**
     * 通过id查询员工合同签订
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffcontract_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffcontractService.getById(id));
    }

    /**
     * 新增员工合同签订
     * @param staffcontract 员工合同签订
     * @return R
     */
    @ApiOperation(value = "新增员工合同签订", notes = "新增员工合同签订")
    @SysLog("新增员工合同签订" )
    @PostMapping("/saveStaffContract")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffcontract_add')" )
    public R save(@RequestBody Staffcontract staffcontract) {
        boolean status = staffcontractService.saveContract(staffcontract);
        return R.ok(status);
    }

    /**
     * 修改员工合同签订
     * @param staffcontract 员工合同签订
     * @return R
     */
    @ApiOperation(value = "修改员工合同签订", notes = "修改员工合同签订")
    @SysLog("修改员工合同签订" )
    @PutMapping("/updateContract")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffcontract_edit')" )
    public R updateById(@RequestBody Staffcontract staffcontract) {
        boolean flag = staffcontractService.UpateContract(staffcontract);
        return R.ok(flag);
    }

    /**
     * 通过id删除员工合同签订
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工合同签订", notes = "通过id删除员工合同签订")
    @SysLog("通过id删除员工合同签订" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_staffcontract_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(staffcontractService.removeById(id));
    }

    /**
     * 员工合同签订分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "员工合同签订分页", notes = "员工合同签订分页")
    @GetMapping("/findStaffContractPage")
    @OperationEntity(operation = "员工合同签订分页" ,clazz = Staffeducation.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffContractPage(Page page, String orgId,String staffName, String staffCode) {
        Page pageResult = staffcontractService.findStaffContractPage(page, orgId, staffName, staffCode);
        return R.ok(pageResult);
    }

    /**
     * 导出员工合同Excel
     * @param  response
     * @return R
     */
    @ApiOperation(value = "导出员工合同Excel", notes = "导出员工合同Excel")
    @SysLog("导出员工合同Excel" )
    @PostMapping("/exportStaffContact")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="staffName",value="员工姓名"),
        @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    public R exportStaffContact(HttpServletResponse response, String orgId,String staffName, String staffCode) {
        try {
             ExcelUtils.export2Web(response, "导出员工合同表", "员工合同签订表", Staffcontract.class, staffcontractService.findStaffContract(orgId, staffName, staffCode));
        } catch (Exception e) {
             e.printStackTrace();
             return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }



}
