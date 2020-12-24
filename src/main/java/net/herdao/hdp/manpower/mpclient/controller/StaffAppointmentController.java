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
import net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval.SaveAppointmentDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffAppointmentService;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.appointment.AppointmentPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 8:58 上午
 */
@RestController
@RequestMapping("/staff/appointment" )
@Api(value = "staffAppointment", tags = "员工兼职")
public class StaffAppointmentController {
    @Autowired
    private StaffAppointmentService service;

    @ApiOperation(value = "分页列表")
    @GetMapping("/page/{appointmentAdnRemovalId}")
    public R<Page<AppointmentPageVO>> page(@RequestParam("appointmentAdnRemovalId") Long appointmentAdnRemovalId,
                                           Page page) {
        return R.ok(service.pageAppointment(appointmentAdnRemovalId, page), "success");
    }

    @ApiOperation(value = "兼职详情记录")
    @GetMapping("/{id}")
    public R<AppointmentInfoVO> get(@PathVariable("id") @NotNull Long id) {
        return R.ok(service.getAppointmentInfoVO(id), "success");
    }

    @ApiOperation(value = "新增兼职记录")
    @PostMapping
    public R<Long> insert(@RequestBody SaveAppointmentDTO dto) {
        return R.ok(service.insert(dto), "success");
    }

    @ApiOperation(value = "更新兼职记录")
    @PutMapping("/{id}")
    public R<Long> update(@PathVariable("id") Long id,
            @RequestBody SaveAppointmentDTO dto) throws Exception {
        return R.ok(service.update(id, dto), "success");
    }

    @ApiOperation(value = "删除兼职记录")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        return R.ok(service.removeById(id), "success");
    }
}
