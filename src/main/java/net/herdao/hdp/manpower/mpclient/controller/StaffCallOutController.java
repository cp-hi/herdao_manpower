package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallOutDTO;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.out.StaffCallOutInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 5:37 下午
 */

@RestController
@RequestMapping("/staff/call-out" )
@Api(value = "staffCallOut", tags = "员工异动管理-调出管理")
public class StaffCallOutController {

    @ApiOperation(value = "员工调出记录详情")
    @GetMapping("/{id}")
    public R<StaffCallOutInfoVO> getStaffCallOutInfo(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "新增员工调入记录")
    @PostMapping
    public R<Long> addStaffCallIn(@RequestBody @NotNull SaveStaffCallOutDTO dto) {
        return null;
    }

    @ApiOperation(value = "更新员工调入记录")
    @PutMapping("/{id}")
    public R<Long> updateStaffChangeLevel(@RequestBody @NotNull SaveStaffCallOutDTO dto) {
        return null;
    }
}
