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

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentActiviti;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentTrain;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentTrainService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 人才培训表
 *
 * @author Andy
 * @date 2020-12-02 20:12:55
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmenttrain" )
@Api(value = "recruitmenttrain", tags = "人才培训表管理")
public class RecruitmentTrainController {

    private final  RecruitmentTrainService recruitmentTrainService;

    /**
     * 人才培训-分页列表
     * @param page 分页对象
     * @param recruitmentId 人才ID
     * @return
     */
    @ApiOperation(value = "人才培训-分页列表", notes = "人才培训-分页列表")
    @GetMapping("/getRecruitmentTrainPage" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="recruitmentId",value="人才ID",required = true),
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttrain_view')" )
    public R<Page<RecruitmentTrain>> getRecruitmentTrainPage(Page page, Long recruitmentId) {
        RecruitmentTrain train=new RecruitmentTrain();
        if (ObjectUtil.isNotNull(recruitmentId)){
            train.setRecruitmentId(recruitmentId);
        }
        Page<RecruitmentTrain> pagResult = recruitmentTrainService.page(page, Wrappers.query(train));
        return R.ok(pagResult);
    }

    /**
     * 人才培训-详情
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才培训-详情", notes = "人才培训-详情")
    @GetMapping("/fetchDetails/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttrain_view')" )
    public R<RecruitmentTrain> getById(@PathVariable("id" ) Long id) {
        RecruitmentTrain recruitmentTrain = recruitmentTrainService.getById(id);
        return R.ok(recruitmentTrain);
    }

    /**
     * 人才培训-新增
     * @param recruitmentTrain 人才培训表
     * @return R
     */
    @ApiOperation(value = "人才培训-新增", notes = "人才培训-新增")
    @PostMapping("/saveTrain")
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttrain_add')" )
    public R<RecruitmentTrain> saveTrain(@RequestBody RecruitmentTrain recruitmentTrain) {
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitmentTrain.setCreatorTime(LocalDateTime.now());
        recruitmentTrain.setCreatorCode(sysUser.getUsername());
        recruitmentTrain.setCreatorName(sysUser.getAliasName());
        recruitmentTrainService.save(recruitmentTrain);
        return R.ok(recruitmentTrain);
    }

    /**
     * 人才培训-修改更新
     * @param recruitmentTrain 人才培训表
     * @return R
     */
    @ApiOperation(value = "人才培训-修改更新", notes = "人才培训-修改更新")
    @PutMapping("/updateTrain")
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttrain_edit')" )
    public R<RecruitmentTrain> updateTrain(@RequestBody RecruitmentTrain recruitmentTrain) {
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitmentTrain.setModifierTime(LocalDateTime.now());
        recruitmentTrain.setModifierCode(sysUser.getUsername());
        recruitmentTrain.setModifierName(sysUser.getAliasName());
        recruitmentTrainService.updateById(recruitmentTrain);
        return R.ok();
    }

    /**
     * 人才培训-删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "人才培训-删除", notes = "人才培训-删除")
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttrain_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentTrainService.removeById(id));
    }

}
