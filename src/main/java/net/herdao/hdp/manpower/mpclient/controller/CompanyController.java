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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.CompanyDetailDTO;
import net.herdao.hdp.manpower.mpclient.entity.Company;
import net.herdao.hdp.manpower.mpclient.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 注册公司
 *
 * @author liang
 * @date 2020-09-15 17:10:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/company" )
@Api(value = "company", tags = "注册公司管理")
public class CompanyController {

    private final CompanyService companyService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param company 注册公司
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_view')" )
    public R getCompanyPage(Page page, Company company, String searchText) {
        return R.ok(companyService.companyPage(page, company, searchText));
    }


    /**
     * 通过id查询注册公司
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_view')" )
    public R<CompanyDetailDTO> getById(@PathVariable("id" ) Long id) {
        return R.ok(companyService.getCompanyById(id));
    }

    /**
     * 新增注册公司
     * @param companyForm 注册公司
     * @return R
     */
    @ApiOperation(value = "新增注册公司", notes = "新增注册公司")
    @SysLog("新增注册公司" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_add')" )
    public R<Boolean> save(@RequestBody CompanyDetailDTO companyForm) {
        return R.ok(companyService.companySave(companyForm));
    }

    /**
     * 修改注册公司
     * @param companyForm 注册公司
     * @return R
     */
    @ApiOperation(value = "修改注册公司", notes = "修改注册公司")
    @SysLog("修改注册公司" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_edit')" )
    public R<Boolean> updateById(@RequestBody CompanyDetailDTO companyForm) {
        return R.ok(companyService.companyUpdate(companyForm));
    }

    /**
     * 通过id删除注册公司
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除注册公司", notes = "通过id删除注册公司")
    @SysLog("通过id删除注册公司" )
    @DeleteMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_mpcompany_del')" )
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(companyService.removeById(id));
    }

}
