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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostOrg;
import net.herdao.hdp.manpower.mpclient.service.PostOrgService;
import net.herdao.hdp.manpower.mpclient.vo.post.PostOrgListVO;
import net.herdao.hdp.manpower.mpclient.vo.post.PostShortVO;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;


/**
 * 岗位组织
 *
 * @author hsh
 * @date 2020-11-25 11:49:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/postorg" )
@Api(value = "postorg", tags = "岗位组织管理")
public class PostOrgController {

    private final PostOrgService postOrgService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param postOrgListVO 岗位组织
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    //@PreAuthorize("@pms.hasPermission('client_postorg_view')" )
    public R getPostOrgPage(Page page, PostOrgListVO postOrgListVO, String seachText) {
        return R.ok(postOrgService.findPostOrgPage(page,postOrgListVO,seachText));
    }
    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "集团ID"),
    })
    public R<List<PostOrgListVO>> list(Long groupId){
        List<PostOrg> list = postOrgService.list(Wrappers.<PostOrg>lambdaQuery().eq(PostOrg::getGroupId, groupId));
        List<PostOrgListVO> vos = DtoConverter.dto2vo(list, PostOrgListVO.class);
        return R.ok(vos);
    }

    /**
     * 通过id查询岗位组织
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    //@PreAuthorize("@pms.hasPermission('client_postorg_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(postOrgService.findPostOrgById(id));
    }

    /**
     * 新增岗位组织
     * @param postOrg 岗位组织
     * @return R
     */
    @ApiOperation(value = "新增岗位组织", notes = "新增岗位组织")
    @SysLog("新增岗位组织" )
    @PostMapping
    //@PreAuthorize("@pms.hasPermission('client_postorg_add')" )
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
    //@PreAuthorize("@pms.hasPermission('client_postorg_edit')" )
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
    //@PreAuthorize("@pms.hasPermission('client_postorg_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(postOrgService.removeById(id));
    }

}
