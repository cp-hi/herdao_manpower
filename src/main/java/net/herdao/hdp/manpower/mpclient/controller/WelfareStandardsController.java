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

package net.herdao.hdp.manpower.mpclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.WelfareStandards;
import net.herdao.hdp.manpower.mpclient.service.WelfareStandardsService;


/**
 * 福利类型
 *
 * @author shuling
 * @date 2020-11-06 11:27:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/welfarestandards" )
@Api(value = "welfarestandards", tags = "福利类型管理")
public class WelfareStandardsController {

    private final  WelfareStandardsService welfareStandardsService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param welfareStandards 福利类型
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getWelfareStandardsPage(Page page, WelfareStandards welfareStandards) {
        return R.ok(welfareStandardsService.page(page, Wrappers.query(welfareStandards)));
    }

}
