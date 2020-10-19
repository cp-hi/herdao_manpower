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
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffProTitleDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffProTitle;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.service.StaffProTitleService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 员工职称
 *
 * @author andy
 * @date 2020-10-10 16:37:15
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffprotitle" )
@Api(value = "staffprotitle", tags = "员工职称管理")
public class StaffProTitleController {

    private final  StaffProTitleService staffProTitleService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffProTitle 员工职称
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffprotitle_view')" )
    public R getStaffProTitlePage(Page page, StaffProTitle staffProTitle) {
        return R.ok(staffProTitleService.page(page, Wrappers.query(staffProTitle)));
    }


    /**
     * 通过id查询员工职称
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffprotitle_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(staffProTitleService.getById(id));
    }

    /**
     * 新增员工职称
     * @param staffProTitle 员工职称
     * @return R
     */
    @ApiOperation(value = "新增员工职称", notes = "新增员工职称")
    @SysLog("新增员工职称" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('mpclient_staffprotitle_add')" )
    public R save(@RequestBody StaffProTitle staffProTitle) {
        return R.ok(staffProTitleService.save(staffProTitle));
    }

    /**
     * 修改员工职称
     * @param staffProTitle 员工职称
     * @return R
     */
    @ApiOperation(value = "修改员工职称", notes = "修改员工职称")
    @SysLog("修改员工职称" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('mpclient_staffprotitle_edit')" )
    public R updateById(@RequestBody StaffProTitle staffProTitle) {
        return R.ok(staffProTitleService.updateById(staffProTitle));
    }

    /**
     * 通过id删除员工职称
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工职称", notes = "通过id删除员工职称")
    @SysLog("通过id删除员工职称" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_staffprotitle_del')" )
    @OperationEntity(operation = "删除员工职称", clazz = StaffProTitle.class)
    public R removeById(@PathVariable Integer id) {
        return R.ok(staffProTitleService.removeById(id));
    }


    /**
     * 员工职称分页
     * @param page 分页对象
     * @param staffId
     * @return
     */
    @ApiOperation(value = "员工职称分页", notes = "员工职称分页")
    @GetMapping("/findStaffProTitlePage")
    @OperationEntity(operation = "员工职称分页" ,clazz = StaffProTitle.class )
    @ApiImplicitParams({
        @ApiImplicitParam(name="staffId",value="员工ID"),
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffProTitlePage(Page page,String staffId) {
        Page pageResult = staffProTitleService.findStaffProTitlePage(page, staffId);
        return R.ok(pageResult);
    }
    /**
     * 新增或修改员工职称
     * @param staffProTitle 员工职称
     * @return R
     */
    @ApiOperation(value = "新增员工职称", notes = "新增员工职称")
    @SysLog("新增或修改员工职称" )
    @PostMapping("/saveOrUpdate")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffprotitle_add')" )
    public R saveOrUpdate(@RequestBody StaffProTitle staffProTitle) {
        return R.ok(staffProTitleService.saveOrUpdate(staffProTitle));
    }
    /**
     * 新增或修改职称及职业资格
     * @param staffProTitle 员工职称
     * @return R
     */
    @ApiOperation(value = "新增或修改职称及职业资格", notes = "新增或修改职称及职业资格")
    @SysLog("新增或修改职称及职业资格" )
    @PostMapping("/saveOrUpdateDTO")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffprotitle_add')" )
    public R saveOrUpdate(@RequestBody StaffProTitleDTO staffProTitleDTO) {
        return R.ok(staffProTitleService.saveOrUpdateDTO(staffProTitleDTO));
    }
}
