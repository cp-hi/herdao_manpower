package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallInDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallOutDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffCallOutService;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.out.StaffCallOutInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StaffCallOutService service;

    @ApiOperation(value = "员工调出记录详情")
    @GetMapping("/{id}")
    public R<StaffCallOutInfoVO> getStaffCallOutInfo(@PathVariable("id") Long id) {
        return R.ok(service.getDetail(id));
    }

    /**
     * TODO:: 该接口是否需要关闭，不允许手动发起
     * @param dto
     * @return
     */
    @ApiOperation(value = "新增员工调出记录")
    @PostMapping
    public R<Long> addStaffCallIn(@RequestBody @NotNull SaveStaffCallOutDTO dto) throws Exception {
        return R.ok(service.saveInfo(dto));
    }

    @ApiOperation(value = "更新员工调入记录")
    @PutMapping("/{id}")
    public R<Long> updateStaffChangeLevel(@PathVariable @NotNull Long id,
                                          @RequestBody @NotNull SaveStaffCallOutDTO dto) throws Exception {
        return R.ok(service.updateInfo(id, dto));
    }

    @ApiOperation(value = "确认发起")
    @PutMapping("/affirm/start")
    public R<Long> affirm(Long id,
                          @RequestBody @NotNull SaveStaffCallOutDTO dto) throws Exception {
        return R.ok(service.affirmStart(id, dto));
    }
}
