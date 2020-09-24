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

package net.herdao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.mpclient.common.Utils.ExcelUtils;
import net.herdao.hdp.mpclient.entity.OrgMinReport;
import net.herdao.hdp.mpclient.entity.OrgReport;
import net.herdao.hdp.mpclient.entity.Organization;
import net.herdao.hdp.mpclient.entity.Stafftransaction;
import net.herdao.hdp.mpclient.service.StafftransactionService;
import net.herdao.hdp.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 异动情况
 *
 * @author andy
 * @date 2020-09-24 16:00:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/stafftransaction" )
@Api(value = "stafftransaction", tags = "异动情况管理")
public class StafftransactionController {

    private final  StafftransactionService stafftransactionService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param stafftransaction 异动情况
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_stafftransaction_view')" )
    public R getStafftransactionPage(Page page, Stafftransaction stafftransaction) {
        return R.ok(stafftransactionService.page(page, Wrappers.query(stafftransaction)));
    }


    /**
     * 通过id查询异动情况
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_stafftransaction_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(stafftransactionService.getById(id));
    }

    /**
     * 新增异动情况
     * @param stafftransaction 异动情况
     * @return R
     */
    @ApiOperation(value = "新增异动情况", notes = "新增异动情况")
    @SysLog("新增异动情况" )
    @PostMapping("/saveTrans")
    //@PreAuthorize("@pms.hasPermission('mpclient_stafftransaction_add')" )
    public R saveTrans(@RequestBody Stafftransaction stafftransaction) {
        boolean flag = stafftransactionService.saveTrans(stafftransaction);
        return R.ok(flag);
    }

    /**
     * 修改异动情况
     * @param stafftransaction 异动情况
     * @return R
     */
    @ApiOperation(value = "修改异动情况", notes = "修改异动情况")
    @SysLog("修改异动情况" )
    @PutMapping("/updateTrans")
    //@PreAuthorize("@pms.hasPermission('mpclient_stafftransaction_edit')" )
    public R updateTrans(@RequestBody Stafftransaction stafftransaction) {
        return R.ok(stafftransactionService.updateTrans(stafftransaction));
    }

    /**
     * 通过id删除异动情况
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除异动情况", notes = "通过id删除异动情况")
    @SysLog("通过id删除异动情况" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_stafftransaction_del')" )
    public R removeById(@PathVariable Long id) {
        boolean status = stafftransactionService.removeById(id);
        return R.ok(status);
    }


    /**
     * 员工异动情况分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "员工异动情况分页", notes = "员工异动情况分页")
    @GetMapping("/findStaffTransPage")
    @OperationEntity(operation = "员工异动情况分页" ,clazz = Stafftransaction.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffTransPage(Page page, String orgId,String staffName, String staffCode) {
        Page pageResult = stafftransactionService.findStaffTransPage(page, orgId, staffName, staffCode);
        return R.ok(pageResult);
    }

    /**
     * 导出员工异动情况Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工异动情况Excel", notes = "导出员工异动情况Excel")
    @SysLog("导出员工异动情况Excel")
    @PostMapping("/exportTrans")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="staffName",value="员工姓名"),
        @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    public void exportTrans(HttpServletResponse response, String orgId,String staffName, String staffCode) {
        try {
            List<Stafftransaction> list = stafftransactionService.findStaffTrans(orgId, staffName, staffCode);
            ExcelUtils.export2Web(response, "员工异动情况表", "员工异动情况表", Stafftransaction.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            R.ok("导出失败");
        }

        R.ok("导出成功");
    }
}
