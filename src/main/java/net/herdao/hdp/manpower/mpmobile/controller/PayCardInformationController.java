/*
 *    Copyright (c) 2018-2025, herdao All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the herdao.net developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liang
 */

package net.herdao.hdp.manpower.mpmobile.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpmobile.entity.PayCardInformation;
import net.herdao.hdp.manpower.mpmobile.service.PayCardInformationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 工资卡信息表
 *
 * @author liang
 * @date 2020-12-16 09:46:03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/paycardinformation" )
@Api(value = "paycardinformation", tags = "工资卡信息表管理")
public class PayCardInformationController {

    private final PayCardInformationService payCardInformationService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param payCardInformation 工资卡信息表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('generator_paycardinformation_view')" )
    public R getPayCardInformationPage(Page page, PayCardInformation payCardInformation) {
        return R.ok(payCardInformationService.page(page, Wrappers.query(payCardInformation)));
    }


    /**
     * 通过id查询工资卡信息表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_paycardinformation_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(payCardInformationService.getById(id));
    }

    /**
     * 新增工资卡信息表
     * @param payCardInformation 工资卡信息表
     * @return R
     */
    @ApiOperation(value = "新增工资卡信息表", notes = "新增工资卡信息表")
    @SysLog("新增工资卡信息表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_paycardinformation_add')" )
    public R save(@RequestBody PayCardInformation payCardInformation) {
        return R.ok(payCardInformationService.save(payCardInformation));
    }

    /**
     * 修改工资卡信息表
     * @param payCardInformation 工资卡信息表
     * @return R
     */
    @ApiOperation(value = "修改工资卡信息表", notes = "修改工资卡信息表")
    @SysLog("修改工资卡信息表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_paycardinformation_edit')" )
    public R updateById(@RequestBody PayCardInformation payCardInformation) {
        return R.ok(payCardInformationService.updateById(payCardInformation));
    }

    /**
     * 通过id删除工资卡信息表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除工资卡信息表", notes = "通过id删除工资卡信息表")
    @SysLog("通过id删除工资卡信息表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_paycardinformation_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(payCardInformationService.removeById(id));
    }

}
