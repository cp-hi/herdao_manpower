package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.constant.ExcelDescriptionContants;
import net.herdao.hdp.manpower.mpclient.dto.staffContract.*;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.StaffcontractService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * 员工合同签订
 * @author andy
 * @date 2020-09-27 09:15:28
 */
@RestController
@RequestMapping("/staffcontract" )
@Api(value = "staffcontract", tags = "员工合同签订管理")
@Slf4j
public class StaffcontractController extends HdpBaseController {
    @Autowired
    private StaffcontractService staffcontractService;

    @Override
    public HdpService getHdpService() {
        return staffcontractService;
    }

    @Override
    public void initEasyExcelArgs(Class importAddCls, Class importAddErrCls, Class importUpdateCls, Class importUpdateErrCls, Integer excelIndex,
                                  Integer headRowNumber, List downloadUpdateTemplateList, String templateName, String excelDescription) {
        this.importAddCls = StaffContractAddDTO.class;
        this.importAddErrCls = StaffContractAddlErrDTO.class;
        this.importUpdateCls = StaffContractUpdateDTO.class;
        this.importUpdateErrCls = StaffContractUpdatelErrDTO.class;
        this.excelName = "员工签订";
    }

    @Override
    public List getDownloadUpdateTemplateList(Map<String, Object> searchParams) {
        List<StaffcontractDTO> list = this.staffcontractService.findStaffContract(null,null);
        return list;
    }

    @Override
    public String getExcelAddDescription() {
        return ExcelDescriptionContants.getContractAddDesc();
    }

    @Override
    public String getExcelUpdateDescription() {
        return ExcelDescriptionContants.getContractUpdateDesc();
    }


    /**
     * 新增员工培训
     * @param staffcontract
     * @return R
     */
    @ApiOperation(value = "新增员工合同", notes = "新增员工合同")
    @SysLog("新增员工合同" )
    @PostMapping("/saveContract" )
    public R saveContract(@RequestBody Staffcontract staffcontract) {
        SysUser sysUser = SysUserUtils.getSysUser();
        staffcontract.setCreatedTime(LocalDateTime.now());
        staffcontract.setCreatorCode(sysUser.getUsername());
        staffcontract.setCreatorName(sysUser.getAliasName());
        staffcontract.setCreatorId(sysUser.getUserId());
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
    @PostMapping("/updateContract" )
    public R updateContract(@RequestBody Staffcontract staffcontract) {
        SysUser sysUser = SysUserUtils.getSysUser();
        staffcontract.setModifiedTime(LocalDateTime.now());
        staffcontract.setModifierCode(sysUser.getUsername());
        staffcontract.setModifierName(sysUser.getAliasName());
        staffcontract.setModifierId(sysUser.getUserId());
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
    public R findStaffContractPage(Page page,StaffcontractDTO staffcontractDTO, String searchText) {
        return R.ok(staffcontractService.findStaffContractPage(page,staffcontractDTO, searchText));
    }

    /**
     * 导出员工合同Excel
     * @param  response
     * @return R
     */
    @ApiOperation(value = "导出员工合同Excel", notes = "导出员工合同Excel")
    @SysLog("导出员工合同Excel" )
    @GetMapping("/exportStaffContact")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键词")
    })
    @SneakyThrows
    public void exportStaffContact(HttpServletResponse response, StaffcontractDTO staffcontractDTO, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        IPage pageResult = staffcontractService.findStaffContractPage(page, staffcontractDTO, searchText);
        ExcelUtils.export2Web(response, "导出员工合同表", "员工合同签订表", StaffcontractDTO.class, pageResult.getRecords());
    }

    /**
     * 通过id查询员工合同表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工合同表", notes = "通过id查询员工合同表")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('demo_demo_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(staffcontractService.getById(id));
    }

}
