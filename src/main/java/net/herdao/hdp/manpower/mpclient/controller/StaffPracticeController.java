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
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffPracticeDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPractice;
import net.herdao.hdp.manpower.mpclient.service.StaffPracticeService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 员工实习记录
 * @author andy
 * @date 2020-10-09 17:51:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffpractice" )
@Api(value = "staffpractice", tags = "员工实习记录管理")
public class StaffPracticeController {

    private final  StaffPracticeService staffPracticeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffPractice 员工实习记录
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffpractice_view')" )
    public R getStaffPracticePage(Page page, StaffPractice staffPractice) {
        return R.ok(staffPracticeService.page(page, Wrappers.query(staffPractice)));
    }


    /**
     * 通过id查询员工实习记录
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffpractice_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffPracticeService.getById(id));
    }

    /**
     * 新增员工实习记录
     * @param staffPractice 员工实习记录
     * @return R
     */
    @ApiOperation(value = "新增员工实习记录", notes = "新增员工实习记录")
    @SysLog("新增员工实习记录" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('mpclient_staffpractice_add')" )
    public R save(@RequestBody StaffPractice staffPractice) {
        return R.ok(staffPracticeService.save(staffPractice));
    }

    /**
     * 新增或修改员工实习记录
     * @param staffPractice 员工实习记录
     * @return R
     */
    @ApiOperation(value = "新增或修改员工实习记录", notes = "新增或修改员工实习记录")
    @SysLog("新增或修改员工实习记录" )
    @PostMapping("/saveOrUpdate")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffpractice_add')" )
    public R saveOrUpdate(@RequestBody StaffPractice staffPractice) {
        boolean status = staffPracticeService.saveOrUpdate(staffPractice);
        return R.ok(status);
    }

    /**
     * 修改员工实习记录
     * @param staffPractice 员工实习记录
     * @return R
     */
    @ApiOperation(value = "修改员工实习记录", notes = "修改员工实习记录")
    @SysLog("修改员工实习记录" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('mpclient_staffpractice_edit')" )
    public R updateById(@RequestBody StaffPractice staffPractice) {
        return R.ok(staffPracticeService.updateById(staffPractice));
    }

    /**
     * 通过id删除员工实习记录
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工实习记录", notes = "通过id删除员工实习记录")
    @SysLog("通过id删除员工实习记录" )
    @DeleteMapping("/del/{id}" )
    @OperationEntity(operation = "删除员工实习记录", clazz = StaffPractice.class)
    public R removeById(@PathVariable Long id) {
        return R.ok(staffPracticeService.removeById(id));
    }


    /**
     * 查询员工实习记录
     * @param staffId 员工ID
     * @return R
     */
    @ApiOperation(value = "查询员工实习记录", notes = "查询员工实习记录")
    @GetMapping("/findStaffPractice" )
    public R findStaffPractice(Long staffId) {
        StaffPracticeDTO result = staffPracticeService.findStaffPractice(staffId);
        return R.ok(result);
    }

}
