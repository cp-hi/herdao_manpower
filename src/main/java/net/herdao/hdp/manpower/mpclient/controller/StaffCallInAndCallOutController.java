package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.service.StaffCallInAndCallOutService;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.StaffCallInAndCallOutExecuteVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.call.StaffCallInAndCallOutPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 11:25 下午
 */

@RestController
@RequestMapping("/staff/callInAndCallOut" )
@Api(value = "staffCallInAndCallOut", tags = "员工异动管理-调入调出")
public class StaffCallInAndCallOutController {

    @Autowired
    private StaffCallInAndCallOutService service;

    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R<Page<StaffCallInAndCallOutPageVO>> pageStaffCallInAndCallOut(String searchText,
                                                                          Page page,
                                                                          Long orgId,
                                                                          String status,
                                                                          String type) {
        return R.ok(service.pageStaffCallInAndCallOut(page, searchText, orgId, status, type), "success");
    }

    @ApiOperation(value = "分页列表-删除")
    @DeleteMapping("/{id}")
    public R deleteCallInAndCallOut(@PathVariable("id") Long id) {
        return R.ok(service.removeById(id), "success");
    }

    @ApiOperation(value = "获取执行调动信息")
    @GetMapping("/execution/{id}")
    public R<StaffCallInAndCallOutExecuteVO> getStaffTransferExecutionInfo(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "分页列表页-确认发起")
    @PutMapping("/affirm/{id}")
    public R<Long> affirm(@PathVariable("id") Long id) throws Exception {
        return R.ok(service.affirm(id), "success");
    }

}
