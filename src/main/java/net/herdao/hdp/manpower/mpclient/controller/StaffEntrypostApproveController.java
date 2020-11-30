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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveDTO;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEducationDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffEntrypostApprove;
import net.herdao.hdp.manpower.mpclient.service.StaffEntrypostApproveService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


/**
 * 录用审批表
 *
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffentrypostapprove" )
@Api(value = "staffentrypostapprove", tags = "录用审批表管理")
public class StaffEntrypostApproveController {

    private final StaffEntrypostApproveService staffEntrypostApproveService;

    /**
     * 录用审批-保存
     * @param approveAddDTO 录用审批表
     * @return R
     */
    @ApiOperation(value = "录用审批-保存", notes = "录用审批-保存")
    @PostMapping("/saveApprove")
    public R<EntryApproveAddDTO> saveApprove(@RequestBody EntryApproveAddDTO approveAddDTO) {
        StaffEntrypostApprove approve=new StaffEntrypostApprove();
        BeanUtils.copyProperties(approveAddDTO,approve);

        SysUser sysUser = SysUserUtils.getSysUser();
        approve.setCreatorTime(LocalDateTime.now());
        approve.setCreatorCode(sysUser.getUsername());
        approve.setCreatorName(sysUser.getAliasName());

        staffEntrypostApproveService.save(approve);
        BeanUtils.copyProperties(approve,approveAddDTO);
        return R.ok(approveAddDTO);
    }

    /**
     * 录用审批-删除
     * @param id 主键ID
     * @return R
     */
    @ApiOperation(value = "录用审批-删除", notes = "录用审批-删除")
    @DeleteMapping("/del/{id}" )
    public R removeById(@PathVariable Long id) {
        return R.ok(staffEntrypostApproveService.removeById(id));
    }

    /**
     * 录用审批-列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @param status 状态：1 填报中，2 审批中，3 已审批
     * @return
     */
    @ApiOperation(value = "录用审批-列表")
    @GetMapping("/findApprovePage")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="分页对象",required = true),
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
        @ApiImplicitParam(name="status",value="状态：1 填报中，2 审批中，3 已审批"),
    })
    public R<Page<EntryApproveDTO>> findApprovePage(Page<EntryApproveDTO> page, String orgId, String searchText,String status) {
        Page<EntryApproveDTO> pageResult = staffEntrypostApproveService.findApprovePage(page,orgId,searchText,status);
        return R.ok(pageResult);
    }

    /**
     * 录用审批-导出
     * @param response
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @param status 状态：1 填报中，2 审批中，3 已审批
     */
    @ApiOperation(value = "录用审批-导出", notes = "录用审批-导出")
    @GetMapping("/exportApprove")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
        @ApiImplicitParam(name="status",value="状态：1 填报中，2 审批中，3 已审批"),
    })
    @SneakyThrows
    public R exportStaffEdu(HttpServletResponse response, String orgId, String searchText, String status) {
        Page page = new Page();
        page.setSize(-1);
        Page pageResult = staffEntrypostApproveService.findApprovePage(page, orgId, searchText, status);
        ExcelUtils.export2Web(response, "录用审批表", "录用审批表", EntryApproveDTO.class, pageResult.getRecords());
        return R.ok("导出成功");
    }

    /**
     * 录用审批-流程审批-详情
     * @param id 主键ID
     * @return R
     */
    @ApiOperation(value = "录用审批-流程审批-详情", notes = "录用审批-流程审批-详情")
    @GetMapping("/findApproveDetails")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键ID"),
    })
    public R<EntryApproveFormDTO> findApproveDetails(Long id) {
        EntryApproveFormDTO result = staffEntrypostApproveService.findApproveDetails(id);
        return R.ok(result);
    }
}
