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

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveApprovalSaveDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPositiveApproval;
import net.herdao.hdp.manpower.mpclient.service.StaffPositiveApprovalService;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 转正审批表
 *
 * @Author cp
 * @CreateDate 2020/12/8
 * @Email soul_thought@163.com
 */
@RestController
@AllArgsConstructor
@RequestMapping("staff/positiveApproval")
@Api(value = "staffPositiveApproval", tags = "转正审批表管理")
public class StaffPositiveApprovalController {

    @Autowired
    private StaffPositiveApprovalService staffPositiveApprovalService;

    /**
     * 分页查询
     *
     * @param page       分页对象
     * @param searchText
     * @param orgId
     * @param status
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
//    @PreAuthorize("@pms.hasPermission('generator_StaffPositiveApproval_view')" )
    public R<Page<StaffPositiveApprovalPageVO>> getStaffPositiveApprovalPage(Page<StaffPositiveApproval> page, Long orgId,
                                                                             String status,
                                                                             String searchText) {
        return R.ok(staffPositiveApprovalService.getPositiveApprovalPageInfo(page, orgId, status, searchText));
    }


    @ApiOperation(value = "获取转正详情")
    @GetMapping("/{id}")
    public R<StaffPositiveApprovalInfoVO> getDetail(@PathVariable("id") Long id) {
        return R.ok(staffPositiveApprovalService.getStaffPositive(id));
    }


    @ApiOperation(value = "新增、编辑页-确认发起")
    @PostMapping("/affirm/start")
    public R affirmStart(Long id,
                         @RequestBody @NotNull StaffPositiveApprovalSaveDTO dto) throws Exception {
        return R.ok(staffPositiveApprovalService.affirmStart(id, dto));
    }


    @ApiOperation(value = "新增、编辑页-确认发起")
    @PutMapping("/affirm/{id}")
    public R affirm(@PathVariable("id") Long id) throws Exception {
        return R.ok(staffPositiveApprovalService.affirm(id));
    }


    @ApiOperation(value = "编辑")
    @PutMapping("/{id}")
    private R<Long> update(@PathVariable("id") @NotNull Long id,
                           @RequestBody @NotNull StaffPositiveApprovalSaveDTO dto) throws Exception {
        return R.ok(staffPositiveApprovalService.updateStaffLeave(id, dto));
    }

    /**
     * 新增转正审批表
     *
     * @param dto 转正审批表
     * @return R
     */
    @ApiOperation(value = "新增转正审批表", notes = "新增转正审批表")
    @SysLog("新增转正审批表")
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('generator_StaffPositiveApproval_add')" )
    public R<Long> save(@RequestBody @NotNull StaffPositiveApprovalSaveDTO dto) {
        return R.ok(staffPositiveApprovalService.insert(dto));
    }


    /**
     * 通过id删除转正审批表
     *
     * @param ids id
     * @return R
     */
    @ApiOperation(value = "通过id删除转正审批表", notes = "通过id删除转正审批表")
    @SysLog("取消转正")
    @DeleteMapping("/{ids}")
//    @PreAuthorize("@pms.hasPermission('generator_StaffPositiveApproval_del')" )
    public R removeById(@PathVariable String ids) {
        String[] inputIds = ids.split(StringPool.COMMA);
        staffPositiveApprovalService.deleteById(inputIds);
        return R.ok();
    }

}
