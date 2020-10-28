package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffcontractDTO;
import net.herdao.hdp.manpower.mpclient.entity.OrgReport;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.service.StaffcontractService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.UserUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 员工合同签订
 * @author andy
 * @date 2020-09-27 09:15:28
 */
@RestController
@RequestMapping("/staffcontract" )
@Api(value = "staffcontract", tags = "员工合同签订管理")
@Slf4j
public class StaffcontractController  {
    @Autowired
    private StaffcontractService staffcontractService;

    /**
     * 新增员工培训
     * @param staffcontract
     * @return R
     */
    @ApiOperation(value = "新增员工合同", notes = "新增员工合同")
    @SysLog("新增员工合同" )
    @GetMapping("/saveContract" )
    public R saveContract(@RequestBody Staffcontract staffcontract) {
        Integer userId = UserUtils.getUserId();
        staffcontract.setCreatedTime(LocalDateTime.now());
        staffcontract.setCreatorCode(userId.toString());
        boolean status = staffcontractService.save(staffcontract);
        return R.ok(status);
    }

    /**
     * 修改
     * @param staffcontract
     * @return R
     */
    @ApiOperation(value = "修改员工合同", notes = "修改员工合同")
    @SysLog("修改员工合同" )
    @GetMapping("/updateContract" )
    public R updateContract(@RequestBody Staffcontract staffcontract) {
        Integer userId = UserUtils.getUserId();
        staffcontract.setModifiedTime(LocalDateTime.now());
        staffcontract.setModifierCode(userId.toString());
        boolean status = staffcontractService.updateById(staffcontract);
        return R.ok(status);
    }

    /**
     * 通过id删除员工合同
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工合同", notes = "通过id删除员工合同")
    @SysLog("通过id删除员工合同" )
    @DeleteMapping("/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_stafftrain_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(staffcontractService.removeById(id));
    }


    /**
     * 分页查询
     * @param page 分页对象
     * @param staffcontract 员工合同签订
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffcontract_view')" )
    public R page(Page page, Staffcontract staffcontract) {
        return R.ok(staffcontractService.page(page, Wrappers.query(staffcontract)));
    }


    /**
     * 员工合同签订分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    @ApiOperation(value = "员工合同签订分页", notes = "员工合同签订分页")
    @GetMapping("/findStaffContractPage")
    @OperationEntity(operation = "员工合同签订分页" ,clazz = Staffeducation.class )
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键词")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffContractPage(Page page, String searchText) {
        Page pageResult = staffcontractService.findStaffContractPage(page, searchText);
        return R.ok(pageResult);
    }

    /**
     * 导出员工合同Excel
     * @param  response
     * @return R
     */
    @ApiOperation(value = "导出员工合同Excel", notes = "导出员工合同Excel")
    @SysLog("导出员工合同Excel" )
    @PostMapping("/exportStaffContact")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键词")
    })
    public R exportStaffContact(HttpServletResponse response, String searchText) {
        try {
             ExcelUtils.export2Web(response, "导出员工合同表", "员工合同签订表", StaffcontractDTO.class, staffcontractService.findStaffContract(searchText));
        } catch (Exception e) {
             log.error("导出员工合同Excel失败",e.getMessage());
             return R.failed("导出员工合同Excel失败");
        }

        return R.ok("导出成功");
    }

}
