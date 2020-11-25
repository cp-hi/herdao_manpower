package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffCallInAndCallOutExecuteDTO;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.StaffCallInAndCallOutExecuteVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.level.change.StaffChangeLevelPageVO;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 11:25 下午
 */

@RestController
@RequestMapping("/staff/call-in-and-call-out" )
@Api(value = "staffCallInAndCallOut", tags = "员工异动管理-调入调出")
public class StaffCallInAndCallOutController {

    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R<Page<StaffChangeLevelPageVO>> pageStaffCallInAndCallOut(String searchText,
                                                                     Page page,
                                                                     Long orgId,
                                                                     String status) {
        return null;
    }

    /**
     * TODO:: 待确认
     */
    @ApiOperation(value = "分页列表-导出")
    @GetMapping("/export")
    public R export() {
        return null;
    }

    @ApiOperation(value = "分页列表-删除")
    @DeleteMapping("/{id}")
    public R deleteCallInAndCallOut(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "获取执行调动信息")
    @GetMapping("/execution/{id}")
    public R<StaffCallInAndCallOutExecuteVO> getStaffTransferExecutionInfo(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "确认执行")
    @PutMapping("/execution/{id}")
    public R executeStaffTransfer(@RequestBody(required = true) SaveStaffCallInAndCallOutExecuteDTO dto) {
        return null;
    }

}
