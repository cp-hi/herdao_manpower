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
import net.herdao.hdp.manpower.mpmobile.entity.CardInformation;
import net.herdao.hdp.manpower.mpmobile.service.CardInformationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 身份证信息表
 *
 * @author liang
 * @date 2020-12-16 09:43:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cardinformation" )
@Api(value = "cardinformation", tags = "身份证信息表管理")
public class CardInformationController {

    private final CardInformationService cardInformationService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param cardInformation 身份证信息表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('generator_cardinformation_view')" )
    public R getCardInformationPage(Page page, CardInformation cardInformation) {
        return R.ok(cardInformationService.page(page, Wrappers.query(cardInformation)));
    }


    /**
     * 通过id查询身份证信息表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_cardinformation_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(cardInformationService.getById(id));
    }

    /**
     * 新增身份证信息表
     * @param cardInformation 身份证信息表
     * @return R
     */
    @ApiOperation(value = "新增身份证信息表", notes = "新增身份证信息表")
    @SysLog("新增身份证信息表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_cardinformation_add')" )
    public R save(@RequestBody CardInformation cardInformation) {
        return R.ok(cardInformationService.save(cardInformation));
    }

    /**
     * 修改身份证信息表
     * @param cardInformation 身份证信息表
     * @return R
     */
    @ApiOperation(value = "修改身份证信息表", notes = "修改身份证信息表")
    @SysLog("修改身份证信息表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_cardinformation_edit')" )
    public R updateById(@RequestBody CardInformation cardInformation) {
        return R.ok(cardInformationService.updateById(cardInformation));
    }

    /**
     * 通过id删除身份证信息表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除身份证信息表", notes = "通过id删除身份证信息表")
    @SysLog("通过id删除身份证信息表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_cardinformation_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(cardInformationService.removeById(id));
    }

}
