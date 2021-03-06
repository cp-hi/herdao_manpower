package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SavaStaffPromoteDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffPromoteExecuteDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffPromoteService;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromoteExecuteVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromoteInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.promote.StaffPromotePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 5:40 下午
 */

@RestController
@RequestMapping("/staff/promote" )
@Api(value = "staffPromote", tags = "员工异动管理-职级升降")
public class StaffPromoteController {

    @Autowired
    private StaffPromoteService service;

    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R<Page<StaffPromotePageVO>> pageStaffPromote(String searchText,
                                                        Page page,
                                                        Long orgId,
                                                        String status) {
        return R.ok(service.pageStaffPromote(page, searchText, orgId, status), "success");
    }

    @ApiOperation(value = "分页列表-流程详情")
    @GetMapping("/process/{id}" )
    public R getProcessDetail(@RequestParam("id") Long id) {

        return null;
    }

    @ApiOperation(value = "新增、详情页-确认发起")
    @PostMapping("/affirm/start")
    public R affirmStaffPromote(@RequestParam("id") Long id,
                                    @RequestBody @NotNull SavaStaffPromoteDTO dto) throws Exception {
        return R.ok(service.affirmStart(id, dto), "success");
    }

    @ApiOperation(value = "分页列表-确认发起")
    @PutMapping("/affirm/{id}")
    public R affirmPage(@PathVariable Long id) throws Exception{
        return R.ok(service.affirm(id), "success");
    }

    @ApiOperation(value = "分页列表-删除")
    @DeleteMapping("/{id}")
    public R deleteStaffPromote(@PathVariable("id") Long id) {
        return R.ok(service.removeById(id), "success");
    }

    @ApiOperation(value = "员工职级变更记录详情")
    @GetMapping("/{id}" )
    public R<StaffPromoteInfoVO> getStaffPromote(@PathVariable("id") Long id) throws Exception {
        return R.ok(service.getDetail(id), "success");
    }

    @ApiOperation(value = "新增员工职级变更记录")
    @PostMapping
    public R<Long> addStaffPromote(@RequestBody @NotNull SavaStaffPromoteDTO dto) throws Exception {
        return R.ok(service.saveInfo(dto), "success");
    }

    @ApiOperation(value = "更新员工职级变更记录")
    @PutMapping("/{id}")
    public R<Long> updateStaffPromote(@RequestBody @NotNull SavaStaffPromoteDTO dto,
                                          @PathVariable("id") @NotNull Long id) throws Exception {
        return R.ok(service.updateInfo(id, dto), "success");
    }

    @ApiOperation(value = "获取执行调动信息")
    @GetMapping("/execution/{id}")
    public R<StaffPromoteExecuteVO> getStaffTransferExecutionInfo(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "确认执行")
    @PutMapping("/execution/{id}")
    public R executeStaffTransfer(@RequestBody(required = true) SaveStaffPromoteExecuteDTO dto) {
        return null;
    }

}
