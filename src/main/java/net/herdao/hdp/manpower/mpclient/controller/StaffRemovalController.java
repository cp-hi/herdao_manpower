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
import net.herdao.hdp.manpower.mpclient.dto.staffAppointmentAndRemoval.SaveRemovalDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffRemovalService;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalPageVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.WaitingRemovalPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 8:59 上午
 */
@RestController
@RequestMapping("/staff/removal" )
@Api(value = "staffemoval", tags = "员工任免")
public class StaffRemovalController {

    @Autowired
    private StaffRemovalService service;

    @ApiOperation(value = "待任免分页列表")
    @GetMapping("/wating/page/{appointmentAdnRemovalId}")
    public R<Page<WaitingRemovalPageVO>> pageWaitingRemoval(@PathVariable("appointmentAdnRemovalId") Long appointmentAdnRemovalId,
                                                            Page page) {
        return R.ok(service.pageWaitingRemoval(appointmentAdnRemovalId, page), "success");
    }

    @ApiOperation(value = "分页列表")
    @GetMapping("/page/{appointmentAdnRemovalId}")
    public R<Page<RemovalPageVO>> page(@PathVariable("appointmentAdnRemovalId") Long appointmentAdnRemovalId,
                                       Page page) {
        return R.ok(service.pageRemoval(appointmentAdnRemovalId, page), "success");
    }

    @ApiOperation(value = "任免详情记录")
    @GetMapping("/{id}")
    public R<RemovalInfoVO> get(@PathVariable("id") Long id) {
        return R.ok(service.getRemovalInfo(id), "success");
    }

    @ApiOperation(value = "新增任免记录")
    @PostMapping
    public R<Long> insert(@RequestBody SaveRemovalDTO dto) {
        return R.ok(service.insert(dto), "success");
    }

    @ApiOperation(value = "更新任免记录")
    @PutMapping("/{id}")
    public R<Long> update(@PathVariable("id") Long id,
                          @RequestBody SaveRemovalDTO dto) throws Exception {
        return R.ok(service.update(id, dto), "success");
    }

    @ApiOperation(value = "删除任免记录")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        return R.ok(service.removeById(id), "success");
    }

}
