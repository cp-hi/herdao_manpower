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
import net.herdao.hdp.mpclient.entity.Stafftrain;
import net.herdao.hdp.mpclient.entity.Stafftransaction;
import net.herdao.hdp.mpclient.service.StafftrainService;
import net.herdao.hdp.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/stafftrain" )
@Api(value = "stafftrain", tags = "员工培训管理")
public class StafftrainController {

    private final  StafftrainService stafftrainService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param stafftrain 员工培训
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_stafftrain_view')" )
    public R getStafftrainPage(Page page, Stafftrain stafftrain) {
        return R.ok(stafftrainService.page(page, Wrappers.query(stafftrain)));
    }


    /**
     * 通过id查询员工培训
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_stafftrain_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(stafftrainService.getById(id));
    }

    /**
     * 新增员工培训
     * @param stafftrain 员工培训
     * @return R
     */
    @ApiOperation(value = "新增员工培训", notes = "新增员工培训")
    @SysLog("新增员工培训" )
    @PostMapping("/saveTrain")
    //@PreAuthorize("@pms.hasPermission('mpclient_stafftrain_add')" )
    public R saveTrain(@RequestBody Stafftrain stafftrain) {
        boolean flag = stafftrainService.saveTrain(stafftrain);
        return R.ok(flag);
    }

    /**
     * 修改员工培训
     * @param stafftrain 员工培训
     * @return R
     */
    @ApiOperation(value = "修改员工培训", notes = "修改员工培训")
    @SysLog("修改员工培训" )
    @PutMapping("/updateTrain")
    //@PreAuthorize("@pms.hasPermission('mpclient_stafftrain_edit')" )
    public R updateById(@RequestBody Stafftrain stafftrain) {
        boolean status = stafftrainService.updateTrain(stafftrain);
        return R.ok(status);
    }

    /**
     * 通过id删除员工培训
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工培训", notes = "通过id删除员工培训")
    @SysLog("通过id删除员工培训" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_stafftrain_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(stafftrainService.removeById(id));
    }

    /**
     * 员工培训分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "员工培训分页", notes = "员工培训分页")
    @GetMapping("/findStaffTrainPage")
    @OperationEntity(operation = "员工培训分页" ,clazz = Stafftrain.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffTrainPage(Page page, String orgId,String staffName, String staffCode) {
        Page pageResult = stafftrainService.findStaffTrainPage(page, orgId, staffName, staffCode);
        return R.ok(pageResult);
    }

    /**
     * 导出员工培训Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工培训Excel", notes = "导出员工培训Excel")
    @SysLog("导出员工培训Excel")
    @PostMapping("/exportTrans")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    public void exportTrans(HttpServletResponse response, String orgId, String staffName, String staffCode) {
        try {
            List<Stafftrain> list = stafftrainService.findStaffTrain(orgId, staffName, staffCode);
            ExcelUtils.export2Web(response, "员工培训表", "员工培训表", Stafftrain.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            R.ok("导出失败");
        }

        R.ok("导出成功");
    }
}
