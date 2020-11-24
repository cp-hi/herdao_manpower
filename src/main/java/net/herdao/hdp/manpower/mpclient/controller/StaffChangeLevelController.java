package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffTransfer.SavaStaffChangeLevelDTO;
import net.herdao.hdp.manpower.mpclient.vo.staff.level.change.StaffChangeLevelInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.level.change.StaffChangeLevelPageVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Liu Chang
 * @Date 2020/11/24 5:40 下午
 */

@RestController
@RequestMapping("/staff/level-change" )
@Api(value = "staffLevelChange", tags = "员工异动管理-职级升降")
public class StaffChangeLevelController {

    @ApiOperation(value = "分页列表")
    @GetMapping("/page" )
    public R<Page<StaffChangeLevelPageVO>> pageStaffChangeLevel(String searchText,
                                                                Page page,
                                                                Long orgId,
                                                                String status) {
        return null;
    }

    @ApiOperation(value = "分页列表-流程详情")
    @GetMapping("/process/{id}" )
    public R getProcessDetail(@RequestParam("id") Long id) {

        return null;
    }

    @ApiOperation(value = "分页列表-确认发起")
    @GetMapping("/affirm/{id}")
    public R affirmStaffChangeLevel(@RequestParam("id") Long id) {
        return null;
    }

    @ApiOperation(value = "分页列表-删除")
    @DeleteMapping
    public R deleteStaffChangeLevel(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "员工职级变更记录详情")
    @GetMapping("/{id}" )
    public R<StaffChangeLevelInfoVO> getStaffChangeLevel(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "新增员工职级变更记录")
    @PostMapping
    public R<Long> addStaffChangeLevel(@RequestBody @NotNull SavaStaffChangeLevelDTO dto) {
        return null;
    }

    @ApiOperation(value = "更新员工职级变更记录")
    @PutMapping("/{id}")
    public R<Long> updateStaffChangeLevel(@RequestBody @NotNull SavaStaffChangeLevelDTO dto,
                                          @PathVariable("id") @NotNull Long id) {
        return null;
    }
}
