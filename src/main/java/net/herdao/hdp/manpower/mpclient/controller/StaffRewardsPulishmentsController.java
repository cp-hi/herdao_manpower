package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.constant.ExcelDescriptionContants;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffRpDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffContract.StaffContractAddlErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffContract.StaffContractUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffContract.StaffContractUpdatelErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.StaffFamilyAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpAddErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpUpdateErrDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.StaffRewardsPulishmentsService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.UserUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工奖惩
 *
 * @author andy
 * @date 2020-09-25 16:26:20
 */
@RestController
@RequestMapping("/staffrewardspulishments" )
@Api(value = "staffrewardspulishments", tags = "员工奖惩管理")
@Slf4j
public class StaffRewardsPulishmentsController extends HdpBaseController {
    @Autowired
    private StaffRewardsPulishmentsService staffRpService;

    @Override
    public HdpService getHdpService() {
        return staffRpService;
    }

    @Override
    public Class getImportAddCls() {
        return StaffRpAddDTO.class;
    }

    @Override
    public Class getImportAddErrCls() {
        return StaffRpAddErrDTO.class;
    }

    @Override
    public Class getImportUpdateCls() {
        return StaffRpUpdateDTO.class;
    }

    @Override
    public Class getImportUpdateErrCls() {
        return StaffRpUpdateErrDTO.class;
    }

    @Override
    public String getExcelAddDescription() {
        return ExcelDescriptionContants.getRpAddDesc();
    }

    @Override
    public String getExcelUpdateDescription() {
        return ExcelDescriptionContants.getRpUpdateDesc();
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffRewardsPulishments 员工奖惩
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffrewardspulishments_view')" )
    public R page(Page page, StaffRewardsPulishments staffRewardsPulishments) {
        return R.ok(staffRpService.page(page, Wrappers.query(staffRewardsPulishments)));
    }

    /**
     * 员工奖惩分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    @ApiOperation(value = "员工奖惩分页", notes = "员工奖惩分页")
    @GetMapping("/findStaffRpPage")
    @OperationEntity(operation = "员工奖惩分页" ,clazz = Staffeducation.class )
    @ApiImplicitParams({
         @ApiImplicitParam(name="searchText",value="搜索关键字")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffRpPage(Page page,String searchText) {
        Page pageResult = staffRpService.findStaffRpPage(page, searchText);
        return R.ok(pageResult);
    }

    /**
     * 导出员工奖惩Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工奖惩Excel", notes = "导出员工奖惩Excel")
    @SysLog("导出员工奖惩Excel")
    @PostMapping("/exportStaffRp")
    @ApiImplicitParams({
         @ApiImplicitParam(name="searchText",value="搜索关键字")
    })
    public R exportStaffRp(HttpServletResponse response,String searchText) {
        try {
            List<StaffRpDTO> list = staffRpService.findStaffRp(searchText);
            ExcelUtils.export2Web(response, "员工奖惩情况表", "员工奖惩情况表", StaffRpDTO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }

    /**
     * 修改
     * @param staffRewardsPulishments
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @SysLog("修改" )
    @PutMapping("/updateRp" )
    public R updateById(@RequestBody StaffRewardsPulishments staffRewardsPulishments) {
        Integer userId = UserUtils.getUserId();
        staffRewardsPulishments.setModifiedTime(LocalDateTime.now());
        staffRewardsPulishments.setModifierCode(userId.toString());
        boolean status = staffRpService.updateById(staffRewardsPulishments);
        return R.ok(status);
    }

    /**
     * 新增员工奖惩
     * @param staffRp
     * @return R
     */
    @ApiOperation(value = "新增员工奖惩", notes = "新增员工奖惩")
    @SysLog("新增员工奖惩" )
    @PostMapping("/saveStaffRp" )
    public R saveStaffRp(@RequestBody StaffRewardsPulishments staffRp) {
        Integer userId = UserUtils.getUserId();
        staffRp.setCreatedTime(LocalDateTime.now());
        staffRp.setCreatorCode(userId.toString());
        boolean status = staffRpService.save(staffRp);
        return R.ok(status);
    }

    /**
     * 更新员工奖惩
     * @param staffRp
     * @return R
     */
    @ApiOperation(value = "更新员工奖惩", notes = "更新员工奖惩")
    @SysLog("更新员工奖惩" )
    @PostMapping("/updateStaffRp" )
    public R updateStaffRp(@RequestBody StaffRewardsPulishments staffRp) {
        Integer userId = UserUtils.getUserId();
        staffRp.setModifiedTime(LocalDateTime.now());
        staffRp.setModifierCode(userId.toString());
        boolean status = staffRpService.updateById(staffRp);
        return R.ok(status);
    }

    /**
     * 通过id查询员工奖惩表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工奖惩表", notes = "通过id查询员工奖惩表")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(staffRpService.getById(id));
    }

    /**
     * 通过id删除员工奖惩表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工奖惩表", notes = "通过id删除员工奖惩表")
    @SysLog("通过id删除员工奖惩表" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(staffRpService.removeById(id));
    }


}
