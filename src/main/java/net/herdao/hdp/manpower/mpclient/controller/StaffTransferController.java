package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferExecuteDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffTransferService;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferExecuteVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Liu Chang
 * @Date 2020/11/23 5:56 下午
 */

@RestController
@RequestMapping("/staff/transfer" )
@Api(value = "staffTransfer", tags = "员工异动管理-人事调动")
public class StaffTransferController {
    @Autowired
    private StaffTransferService service;

    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R<Page<StaffTransferPageVO>> pageStaffTransfer(String searchText,
                                                          Page page,
                                                          Long orgId,
                                                          String status) {
        return R.ok(service.pageTransfer(page, searchText, orgId, status));
    }

    /**
     * 对接第三方工具，待讨论
     * @return
     */
    @ApiOperation(value = "分页列表-流程详情")
    @GetMapping("/process/{id}" )
    public R getProcessDetail(@RequestParam("id") Long id) {

        return null;
    }

    @ApiOperation(value = "新增、详情页-确认发起")
    @PostMapping("/affirm/start")
    public R affirmDetail(Long id,
                    @RequestBody SaveStaffTransferInfoDTO dto) throws Exception {
        return R.ok(service.affirmStart(id, dto));
    }

    @ApiOperation(value = "分页列表-确认发起")
    @PutMapping("/affirm/{id}")
    public R affirmPage(@PathVariable Long id) throws Exception {
        return R.ok(service.affirm(id));
    }

    @ApiOperation(value = "分页列表-删除")
    @DeleteMapping("/{id}")
    public R deleteStaffTransfer(@PathVariable("id") Long id) {
        return R.ok(service.removeById(id));
    }

    @ApiOperation(value = "人事调动详情")
    @GetMapping("/{id}" )
    public R<StaffTransferInfoVO> getStaffTransferInfo(@PathVariable("id") Long id) {
        return R.ok(service.getDetail(id));
    }

    @ApiOperation(value = "新增人事调动-保存")
    @PostMapping
    public R<Long> addStaffTransfer(@RequestBody @NotNull SaveStaffTransferInfoDTO dto) throws Exception {
       return R.ok(service.saveInfo(dto));
    }

    @ApiOperation(value = "更新人事调动-保存")
    @PutMapping("/{id}")
    public R<Long> updateStaffTransfer(@RequestBody @NotNull SaveStaffTransferInfoDTO dto,
                                       @PathVariable("id") @NotNull Long id) throws Exception {
        return R.ok(service.updateInfo(id, dto));
    }

    @ApiOperation(value = "获取执行调动信息")
    @GetMapping("/execution/{id}")
    public R<StaffTransferExecuteVO> getStaffTransferExecutionInfo(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "确认执行")
    @PutMapping("/execution/{id}")
    public R executeStaffTransfer(@RequestBody(required = true) SaveStaffTransferExecuteDTO dto) {
        return null;
    }
}
