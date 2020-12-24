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
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.appointmentAndRemoval.removal.RemovalPageVO;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Liu Chang
 * @Date 2020/12/24 8:59 上午
 */
@RestController
@RequestMapping("/staff/removal" )
@Api(value = "staffemoval", tags = "员工任免")
public class StaffRemovalController {
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R<Page<RemovalPageVO>> page(String searchText,
                                           Page page,
                                           Long orgId,
                                           String status,
                                           String type) {
        return R.ok(null, "success");
    }

    @ApiOperation(value = "任免详情记录")
    @GetMapping("/{id}")
    public R<RemovalInfoVO> get(@RequestParam("id") Long id) {
        return R.ok(null, "success");
    }

    @ApiOperation(value = "新增任免记录")
    @PostMapping
    public R<Long> insert(@RequestBody SaveRemovalDTO dto) {
        return R.ok(null, "success");
    }

    @ApiOperation(value = "更新任免记录")
    @PutMapping
    public R<Long> update(@RequestParam("id") Long id,
                          @RequestBody SaveRemovalDTO dto) {
        return R.ok(null, "success");
    }

    @ApiOperation(value = "删除任免记录")
    @DeleteMapping
    public R<Long> delete(@RequestParam("id") Long id) {
        return R.ok(null, "success");
    }

}
