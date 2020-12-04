package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffLeavePostDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffTransferInfoDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffLeaveService;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.leave.post.StaffLeavePostPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Liu Chang
 * @Date 2020/12/3 2:36 下午
 */

@RestController
@RequestMapping("/staff/leavePost" )
@Api(value = "staffLeavePost", tags = "员工离职")
public class StaffLeavePostController {
    @Autowired
    private StaffLeaveService service;

    /**
     *
     * @param searchText
     * @param page
     * @param orgId
     * @param status 此状态码描述可参考 StaffChangesApproveStatusConstants 常量类
     *               待离职-已执行传入参数为"56"，即状态 5 和 6 的都要
     *               已离职传参为"5"
     * @return
     */
    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R<Page<StaffLeavePostPageVO>> page(String searchText,
                                              Page page,
                                              Long orgId,
                                              String status) {
        return R.ok(service.pageStaffLeavePost(page, searchText, orgId, status));
    }

    @ApiOperation(value = "离职列表/详情页-确认发起")
    @PutMapping("/affirm/start")
    public R affirm(Long id,
                    @RequestBody @NotNull SaveStaffLeavePostDTO dto) throws Exception {
        return R.ok(service.affirmStart(id, dto));
    }

    @ApiModelProperty(value = "新增离职审批")
    @PostMapping
    public R<Long> insert(@RequestBody @NotNull SaveStaffLeavePostDTO dto) {

        return R.ok(service.insert(dto));
    }

    @ApiOperation(value = "获取离职详情")
    @GetMapping("/{id}")
    public R<StaffLeavePostInfoVO> getDetail(@PathVariable("id") Long id) {
        return R.ok(service.getStaffLeave(id));
    }

    @ApiOperation(value = "编辑")
    @PutMapping("/{id}")
    private R<Long> update(@PathVariable("id") @NotNull Long id,
                           @RequestBody @NotNull SaveStaffLeavePostDTO dto) throws Exception {
        return R.ok(service.updateStaffLeave(id, dto));
    }

    @ApiModelProperty(value = "取消离职")
    @DeleteMapping("/{id}")
    private R delete(@PathVariable("id") Long id) {
        return R.ok(service.removeById(id));
    }
}
