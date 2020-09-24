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
import net.herdao.hdp.mpclient.entity.Workexperience;
import net.herdao.hdp.mpclient.service.WorkexperienceService;
import net.herdao.hdp.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 员工工作经历
 *
 * @author andy
 * @date 2020-09-24 10:24:09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/workexperience" )
@Api(value = "workexperience", tags = "员工工作经历管理")
public class WorkexperienceController {

    private final  WorkexperienceService workexperienceService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param workexperience 员工工作经历
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_workexperience_view')" )
    public R getWorkexperiencePage(Page page, Workexperience workexperience) {
        return R.ok(workexperienceService.page(page, Wrappers.query(workexperience)));
    }


    /**
     * 通过id查询员工工作经历
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_workexperience_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(workexperienceService.getById(id));
    }

    /**
     * 新增员工工作经历
     * @param workexperience 员工工作经历
     * @return R
     */
    @ApiOperation(value = "新增员工工作经历", notes = "新增员工工作经历")
    @SysLog("新增员工工作经历" )
    @PostMapping("/saveWork")
    //@PreAuthorize("@pms.hasPermission('mpclient_workexperience_add')" )
    public R saveWork(@RequestBody Workexperience workexperience) {
        boolean flag = workexperienceService.saveWork(workexperience);
        return R.ok(flag);
    }

    /**
     * 修改员工工作经历
     * @param workexperience 员工工作经历
     * @return R
     */
    @ApiOperation(value = "修改员工工作经历", notes = "修改员工工作经历")
    @SysLog("修改员工工作经历" )
    @PutMapping("/updateWork")
    //@PreAuthorize("@pms.hasPermission('mpclient_workexperience_edit')" )
    public R updateWork(@RequestBody Workexperience workexperience) {
        boolean flag = workexperienceService.updateWork(workexperience);
        return R.ok(flag);
    }

    /**
     * 通过id删除员工工作经历
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工工作经历", notes = "通过id删除员工工作经历")
    @SysLog("通过id删除员工工作经历" )
    @DeleteMapping("/del/{id}" )
    // @PreAuthorize("@pms.hasPermission('mpclient_workexperience_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(workexperienceService.removeById(id));
    }

    /**
     * 员工工作经历分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "员工工作经历分页", notes = "员工工作经历分页")
    @GetMapping("/findStaffWorkPage")
    @OperationEntity(operation = "员工工作经历分页" ,clazz = Workexperience.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffWorkPage(Page page, String orgId,String staffName, String staffCode) {
        Page pageResult = workexperienceService.findStaffWorkPage(page, orgId, staffName, staffCode);
        return R.ok(pageResult);
    }
}
