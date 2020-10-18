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
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftransDTO;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.mpclient.service.StafftransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 异动情况
 * @author andy
 * @date 2020-09-24 16:00:18
 */
@RestController
@RequestMapping("/stafftransaction" )
@Api(value = "stafftransaction", tags = "异动情况")
public class StafftransactionController extends BaseController<Stafftransaction>  {
    @Autowired
    private StafftransactionService stafftransactionService;

    @Autowired
    public void setEntityService(StafftransactionService stafftransactionService) {
        super.entityService = stafftransactionService;
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param stafftransaction 异动情况
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_stafftransaction_view')" )
    public R page(Page page, Stafftransaction stafftransaction) {
        return R.ok(stafftransactionService.page(page, Wrappers.query(stafftransaction)));
    }

    /**
     * 员工异动情况分页
     * @param page 分页对象
     * @param searchText 搜索关键字
     * @return
     */
    @ApiOperation(value = "员工异动情况分页", notes = "员工异动情况分页")
    @GetMapping("/findStaffTransPage")
    @OperationEntity(operation = "员工异动情况分页" ,clazz = Stafftransaction.class )
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键字")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffTransPage(Page page, String searchText) {
        Page pageResult = stafftransactionService.findStaffTransPage(page, searchText);
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
          @ApiImplicitParam(name="searchText",value="搜索关键字")
    })
    public void exportTrans(HttpServletResponse response, String searchText) {
        try {
            List<StafftransDTO> list = stafftransactionService.findStaffTrans(searchText);
            ExcelUtils.export2Web(response, "员工异动情况表", "员工异动情况表", StafftransDTO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            R.ok("导出失败");
        }

        R.ok("导出成功");
    }
}
