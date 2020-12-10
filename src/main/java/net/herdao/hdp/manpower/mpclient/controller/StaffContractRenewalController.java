package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.staffChanges.SaveStaffContractRenewalDTO;
import net.herdao.hdp.manpower.mpclient.service.StaffContractRenewalService;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.renew.contract.StaffContractRenewalPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Liu Chang
 * @Date 2020/12/7 9:17 上午
 */
@RestController
@RequestMapping("/staff/renewContract" )
@Api(value = "staffRenewContract", tags = "合同续签")
public class StaffContractRenewalController {

    @Autowired
    private StaffContractRenewalService service;

    @ApiOperation(value = "分页列表")
    @GetMapping("/page")
    public R<Page<StaffContractRenewalPageVO>> pageStaffRenewContract(String searchText,
                                                                      Page page,
                                                                      Long orgId,
                                                                      String status) {
        return R.ok(service.pageStaffRenewContract(page, searchText, orgId, status));
    }

    @ApiOperation(value = "新增、修改页-确认发起")
    @PostMapping("/affirm/start")
    public R affirmStart(Long id,
                    @RequestBody @NotNull SaveStaffContractRenewalDTO dto) throws Exception {
        return R.ok(service.affirmStart(id, dto));
    }

    @ApiOperation(value = "分页列表-确认发起")
    @PutMapping("/affirm/{id}")
    public R affirm(@PathVariable("id") Long id) throws Exception {
        return R.ok(service.affirm(id));
    }


    @ApiOperation(value = "分页列表-删除")
    @DeleteMapping("/{id}")
    public R deleteStaffRenewContract(@PathVariable("id") Long id) {
        return R.ok(service.removeById(id));
    }

    @ApiOperation(value = "新增合同续签-保存")
    @PostMapping
    public R<Long> addRenewContract(@RequestBody SaveStaffContractRenewalDTO dto) {
        return R.ok(service.add(dto));
    }

    @ApiOperation(value = "更新合同续签-保存")
    @PutMapping("/{id}")
    public R<Long> updateRenewContract(@PathVariable("id") Long id,
                                       @RequestBody @NotNull SaveStaffContractRenewalDTO dto) throws Exception {
        return R.ok(service.updateInfo(id, dto));
    }

    @ApiOperation(value = "更新合同续签-保存")
    @GetMapping("/{id}")
    public R<StaffContractRenewalInfoVO> getDetail(@PathVariable("id") Long id) {
        return R.ok(service.getDetail(id));
    }
}
