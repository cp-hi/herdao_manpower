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
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffcontractDTO;
import net.herdao.hdp.manpower.mpclient.entity.OrgReport;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.service.StaffcontractService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/staffcontract" )
@Api(value = "staffcontract", tags = "员工合同签订管理")
@Slf4j
public class StaffcontractController extends BaseController<Staffcontract> {
    @Autowired
    private StaffcontractService staffcontractService;

    @Autowired
    public void setEntityService(StaffcontractService staffcontractService) {
        super.entityService = staffcontractService;
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffcontract 员工合同签订
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffcontract_view')" )
    public R page(Page page, Staffcontract staffcontract) {
        return R.ok(staffcontractService.page(page, Wrappers.query(staffcontract)));
    }


    /**
     * 员工合同签订分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    @ApiOperation(value = "员工合同签订分页", notes = "员工合同签订分页")
    @GetMapping("/findStaffContractPage")
    @OperationEntity(operation = "员工合同签订分页" ,clazz = Staffeducation.class )
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键词")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffContractPage(Page page, String searchText) {
        Page pageResult = staffcontractService.findStaffContractPage(page, searchText);
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
        @ApiImplicitParam(name="searchText",value="搜索关键词")
    })
    public R exportStaffContact(HttpServletResponse response, String searchText) {
        try {
             ExcelUtils.export2Web(response, "导出员工合同表", "员工合同签订表", StaffcontractDTO.class, staffcontractService.findStaffContract(searchText));
        } catch (Exception e) {
             log.error("导出员工合同Excel失败",e.getMessage());
             return R.failed("导出员工合同Excel失败");
        }

        return R.ok("导出成功");
    }

}
