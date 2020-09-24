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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.mpclient.entity.Organization;
import net.herdao.hdp.mpclient.entity.Staffeducation;
import net.herdao.hdp.mpclient.service.StaffeducationService;
import net.herdao.hdp.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 员工教育经历
 *
 * @author andy
 * @date 2020-09-23 17:22:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffEducation" )
@Api(value = "staffEducation", tags = "员工教育经历管理")
public class StaffeducationController {

    private final  StaffeducationService staffeducationService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffeducation 员工教育经历
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffeducation_view')" )
    public R getStaffeducationPage(Page page, Staffeducation staffeducation) {
        return R.ok(staffeducationService.page(page, Wrappers.query(staffeducation)));
    }


    /**
     * 通过id查询员工教育经历
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffeducation_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffeducationService.getById(id));
    }

    /**
     * 新增员工教育经历
     * @param staffeducation 员工教育经历
     * @return R
     */
    @ApiOperation(value = "新增员工教育经历", notes = "新增员工教育经历")
    @SysLog("新增员工教育经历" )
    @PostMapping("/saveEducation")
   // @PreAuthorize("@pms.hasPermission('mpclient_staffeducation_add')" )
    public R save(@RequestBody Staffeducation staffeducation) {
        boolean status = staffeducationService.saveEdu(staffeducation);
        return R.ok(status);
    }



    /**
     * 修改员工教育经历
     * @param staffeducation 员工教育经历
     * @return R
     */
    @ApiOperation(value = "修改员工教育经历", notes = "修改员工教育经历")
    @SysLog("修改员工教育经历" )
    @PutMapping("/updateEducation")
    // @PreAuthorize("@pms.hasPermission('mpclient_staffeducation_edit')" )
    public R updateById(@RequestBody Staffeducation staffeducation) {
        boolean status = staffeducationService.updateEdu(staffeducation);
        return R.ok(status);
    }

    /**
     * 通过id删除员工教育经历
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工教育经历", notes = "通过id删除员工教育经历")
    @SysLog("通过id删除员工教育经历" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_staffeducation_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(staffeducationService.removeById(id));
    }


    /**
     * 员工家庭情况分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "员工家庭情况分页", notes = "员工家庭情况分页")
    @GetMapping("/findStaffEducationPage")
    @OperationEntity(operation = "员工家庭情况分页" ,clazz = Staffeducation.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffEducationPage(Page page, String orgId,String staffName, String staffCode) {
        Page pageResult = staffeducationService.findStaffEducationPage(page, orgId, staffName, staffCode);
        return R.ok(pageResult);
    }
}
