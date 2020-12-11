package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallInDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffCallInService;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.in.StaffCallInInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 5:06 下午
 */

@RestController
@RequestMapping("/staff/callIn" )
@Api(value = "staffCallIn", tags = "员工异动管理-调入管理")
public class StaffCallInController {

    @Autowired
    private StaffCallInService service;

    @ApiOperation(value = "员工调入记录详情")
    @GetMapping("/{id}" )
    public R<StaffCallInInfoVO> getStaffCallInInfo(@PathVariable("id") Long id) {
        return R.ok(service.getDetail(id), "success");
    }

    @ApiOperation(value = "新增员工调入记录")
    @PostMapping
    public R<Long> addStaffCallIn(@RequestBody @NotNull SaveStaffCallInDTO dto) throws Exception {

        return R.ok(service.saveInfo(dto), "success");
    }

    @ApiOperation(value = "更新员工调入记录")
    @PutMapping("/{id}")
    public R<Long> updateCallIn(@PathVariable("id") Long id, @RequestBody @NotNull SaveStaffCallInDTO dto) throws Exception {
        return R.ok(service.updateInfo(id, dto), "success");
    }

    @ApiOperation(value = "新建、编辑页-确认发起")
    @PostMapping("/affirm/start")
    public R<Long> affirmStart(Long id,
                          @RequestBody @NotNull SaveStaffCallInDTO dto) throws Exception {
        return R.ok(service.affirmStart(id, dto), "success");
    }
}
