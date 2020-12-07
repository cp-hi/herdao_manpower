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
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.RecruitmentTitle;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentTitleService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 人才职称资格
 *
 * @author Andy
 * @date 2020-12-05 14:19:39
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitmentTitle" )
@Api(value = "recruitmentTitle", tags = "人才职称资格管理")
public class RecruitmentTitleController {

    private final  RecruitmentTitleService recruitmentTitleService;

   /**
     * 简历详情-人才职称资格-详情
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "简历详情-人才职称资格-详情", notes = "简历详情-人才职称资格-详情")
    @GetMapping("/fetchRecruitmentTitleById" )
    @ApiImplicitParams({
       @ApiImplicitParam(name = "id", value = "主键id")
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttitle_view')" )
    public R<RecruitmentTitle> fetchRecruitmentTitleById(Long id) {
        RecruitmentTitle recruitmentTitle = recruitmentTitleService.getById(id);
        return R.ok(recruitmentTitle);
    }

    /**
     * 简历详情-人才职称资格-新增保存
     * @param recruitmentTitle 人才职称资格
     * @return R
     */
    @ApiOperation(value = "简历详情-人才职称资格-新增保存", notes = "简历详情-人才职称资格-新增保存")
    @PostMapping("/saveTitle")
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttitle_add')" )
    public R<RecruitmentTitle> saveTitle(@RequestBody RecruitmentTitle recruitmentTitle) {
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitmentTitle.setCreatorTime(LocalDateTime.now());
        recruitmentTitle.setCreatorCode(sysUser.getUsername());
        recruitmentTitle.setCreatorName(sysUser.getAliasName());

        recruitmentTitleService.save(recruitmentTitle);
        return R.ok(recruitmentTitle);
    }

    /**
     * 简历详情-人才职称资格-修改更新
     * @param recruitmentTitle 人才职称资格
     * @return R
     */
    @ApiOperation(value = "简历详情-人才职称资格-修改更新", notes = "简历详情-人才职称资格-修改更新")
    @PostMapping("/updateTitle")
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttitle_edit')" )
    public R<RecruitmentTitle> updateTitle(@RequestBody RecruitmentTitle recruitmentTitle) {
        SysUser sysUser = SysUserUtils.getSysUser();
        recruitmentTitle.setModifierTime(LocalDateTime.now());
        recruitmentTitle.setModifierCode(sysUser.getUsername());
        recruitmentTitle.setModifierName(sysUser.getAliasName());

        recruitmentTitleService.updateById(recruitmentTitle);
        return R.ok(recruitmentTitle);
    }

    /**
     * 简历详情-人才职称资格-删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "简历详情-人才职称资格-删除", notes = "简历详情-人才职称资格-删除")
    @DeleteMapping("del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitmenttitle_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(recruitmentTitleService.removeById(id));
    }

}
