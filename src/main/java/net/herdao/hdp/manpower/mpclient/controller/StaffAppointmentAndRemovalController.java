/*
 * All rights Reserved, Designed By HerDao
 * Copyright:    Copyright(C) 2020-2099
 * Company:      HerDao Ltd.
 */

package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval.SaveAppointAndRemovalDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffAppointmentAndRemovalService;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.AppointmentAndRemovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.AppointmentAndRemovalPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 8:56 上午
 */
@RestController
@RequestMapping("/staff/appointmentAndRemoval" )
@Api(value = "staffAppointmentAndRemoval", tags = "员工兼职任免")
public class StaffAppointmentAndRemovalController {

    @Autowired
    private StaffAppointmentAndRemovalService service;

    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R<Page<AppointmentAndRemovalPageVO>> page(String searchText,
                                                     Page page,
                                                     Long orgId,
                                                     String status,
                                                     String type) {
        return R.ok(service.pageAppointmentAndRemoval(searchText, page, orgId, status, type), "success");
    }


    @ApiOperation(value = "兼职任免详情")
    @GetMapping("/{id}")
    public R<AppointmentAndRemovalInfoVO> get(@PathVariable("id") Long id) throws Exception {
        return R.ok(service.getAppointmentAndRemovalInfo(id), "success");
    }

    @ApiOperation(value = "新增兼职任免")
    @PostMapping
    public R<Long> insert(@RequestBody SaveAppointAndRemovalDTO dto) {
        return R.ok(service.insert(dto), "success");
    }

    @ApiOperation(value = "编辑兼职任免")
    @PutMapping("/{id}")
    public R<Long> update(@RequestParam("id") Long id,
                          @RequestBody SaveAppointAndRemovalDTO dto) throws Exception {
        return R.ok(service.update(id, dto), "success");
    }

    @ApiOperation(value = "删除兼职任免")
    @DeleteMapping("/{id}")
    public R delete(@RequestParam("id") Long id) {
        return R.ok(service.removeById(id), "success");
    }
}
