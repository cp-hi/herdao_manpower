package net.herdao.hdp.manpower.mpclient.controller.staff.change;

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

import java.util.Date;

/**
 * @Author Liu Chang
 * @Date 2020/11/23 5:56 下午
 */

@RestController
@RequestMapping("/staff/transfer" )
@Api(value = "staffTransferApprove", tags = "员工异动管理-人事调动")
public class StaffTransferController {

    @ApiOperation(value = "分页列表")
    @GetMapping("/page" )
    public R<Page<StaffTransferPageVO>> getStaffTransferPage(String searchText,
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
    @GetMapping("/page" )
    public R getProcessDetail() {

        return null;
    }
    /**
     * "确认发起" 对接第三方 TODO:: 待讨论
     * @return
     */
    @ApiOperation(value = "分页列表-确认发起")
    @GetMapping("/affirm")
    public R affirmStaffTransfer() {
        return null;
    }

    /**
     * TODO:: 待确认是 id 还是 userId
     * @param id
     * @return
     */
    @ApiOperation(value = "分页列表-确认发起")
    @DeleteMapping("/affirm/{id}")
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
    public R<Long> addStaffTransfer(@RequestBody(required = true)SaveStaffTransferInfoDTO dto) {
        return null;
    }

    @ApiOperation(value = "更新人事调动")
    @PutMapping
    public R<Long> updateStaffTransfer(@RequestBody(required = true)SaveStaffTransferInfoDTO dto) {
        return null;
    }

    @ApiOperation(value = "获取执行调动")
    @GetMapping("/approve/{id}")
    public R<StaffTransferApproveVO> getStaffTransferProveInfo(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "人事调动-执行调动")
    @PutMapping("/approve/{id}")
    public R executeStaffTransferProve(@RequestBody(required = true) SaveStaffTransferProveDTO dto) {
        return null;
    }



}
