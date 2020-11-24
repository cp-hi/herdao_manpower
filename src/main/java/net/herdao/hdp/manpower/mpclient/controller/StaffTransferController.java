package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffTransfer.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTransfer.SaveStaffTransferProveDTO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferApproveVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.transfer.StaffTransferPageVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author Liu Chang
 * @Date 2020/11/23 5:56 下午
 */

@RestController
@RequestMapping("/staff/transfer" )
@Api(value = "staffTransfer", tags = "员工异动管理-人事调动")
public class StaffTransferController {

    @ApiOperation(value = "分页列表")
    @GetMapping("/page" )
    public R<Page<StaffTransferPageVO>> pageStaffTransfer(String searchText,
                                                          Page page,
                                                          Long orgId,
                                                          String status) {
        return null;
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

    /**
     * "确认发起" 对接第三方 TODO:: 待讨论
     * @return
     */
    @ApiOperation(value = "分页列表-确认发起")
    @GetMapping("/affirm/{id}")
    public R affirmStaffTransfer(@RequestParam("id") Long id) {
        return null;
    }

    @ApiOperation(value = "分页列表-删除")
    @DeleteMapping
    public R deleteStaffTransfer(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "人事调动详情")
    @GetMapping("/{id}" )
    public R<StaffTransferInfoVO> getStaffTransferInfo(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "新增人事调动")
    @PostMapping
    public R<Long> addStaffTransfer(@RequestBody @NotNull SaveStaffTransferInfoDTO dto) {
        return null;
    }

    @ApiOperation(value = "更新人事调动")
    @PutMapping("/{id}")
    public R<Long> updateStaffTransfer(@RequestBody @NotNull SaveStaffTransferInfoDTO dto,
                                       @PathVariable("id") @NotNull Long id) {
        return null;
    }

    @ApiOperation(value = "获取执行调动信息")
    @GetMapping("/execution/{id}")
    public R<StaffTransferApproveVO> getStaffTransferExecutionInfo(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "确认执行")
    @PutMapping("/execution/{id}")
    public R executeStaffTransfer(@RequestBody(required = true) SaveStaffTransferProveDTO dto) {
        return null;
    }
}
