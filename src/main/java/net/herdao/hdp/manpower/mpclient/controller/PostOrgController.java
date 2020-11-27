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
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.PostOrg;
import net.herdao.hdp.manpower.mpclient.service.PostOrgService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 岗位组织
 *
 * @author hsh
 * @date 2020-11-25 11:49:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mppostorg" )
@Api(value = "mppostorg", tags = "岗位组织管理")
public class PostOrgController {

    private final PostOrgService postOrgService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param postOrg 岗位组织
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    //@PreAuthorize("@pms.hasPermission('mpclient_mppostorg_view')" )
    public R getMpPostOrgPage(Page page, PostOrg postOrg) {
        return R.ok(postOrgService.page(page, Wrappers.query(postOrg)));
    }


    /**
     * 通过id查询岗位组织
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_mppostorg_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(postOrgService.getById(id));
    }

    /**
     * 新增岗位组织
     * @param postOrg 岗位组织
     * @return R
     */
    @ApiOperation(value = "新增岗位组织", notes = "新增岗位组织")
    @SysLog("新增岗位组织" )
    @PostMapping
    //@PreAuthorize("@pms.hasPermission('mpclient_mppostorg_add')" )
    public R save(@RequestBody PostOrg postOrg) {
        return R.ok(postOrgService.saveOrUpdatePostOrg(postOrg));
    }

    /**
     * 修改岗位组织
     * @param postOrg 岗位组织
     * @return R
     */
    @ApiOperation(value = "修改岗位组织", notes = "修改岗位组织")
    @SysLog("修改岗位组织" )
    @PutMapping
    //@PreAuthorize("@pms.hasPermission('mpclient_mppostorg_edit')" )
    public R updateById(@RequestBody PostOrg postOrg) {
        return R.ok(postOrgService.updateById(postOrg));
    }

    /**
     * 通过id删除岗位组织
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除岗位组织", notes = "通过id删除岗位组织")
    @SysLog("通过id删除岗位组织" )
    @DeleteMapping("/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_mppostorg_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(postOrgService.removeById(id));
    }

}
