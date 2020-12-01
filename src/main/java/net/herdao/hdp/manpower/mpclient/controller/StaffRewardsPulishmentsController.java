package net.herdao.hdp.manpower.mpclient.controller;

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
import net.herdao.hdp.manpower.mpclient.dto.staffRp.*;
import net.herdao.hdp.manpower.mpclient.entity.StaffRewardsPulishments;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.StaffRewardsPulishmentsService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.UserUtils;
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
 * 员工奖惩
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
    public void initEasyExcelArgs(Class importAddCls, Class importAddErrCls, Class importUpdateCls, Class importUpdateErrCls, Integer excelIndex,
                                  Integer headRowNumber, List downloadUpdateTemplateList, String templateName, String excelDescription) {
        this.importAddCls = StaffRpAddDTO.class;
        this.importAddErrCls = StaffRpAddErrDTO.class;
        this.importUpdateCls = StaffRpUpdateDTO.class;
        this.importUpdateErrCls = StaffRpUpdateErrDTO.class;
        this.excelName = "员工奖惩";
    }

    @Override
    public List getDownloadUpdateTemplateList(Map<String, Object> searchParams){
        List<StaffRpDTO> list = this.staffRpService.findStaffRp(null,null);
        return list;
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
    public R findStaffRpPage(Page page,StaffRpDTO staffRpDTO,String searchText) {
        Page pageResult = staffRpService.findStaffRpPage(page,staffRpDTO, searchText);
        return R.ok(pageResult);
    }

    /**
     * 导出员工奖惩Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工奖惩Excel", notes = "导出员工奖惩Excel")
    @GetMapping("/exportStaffRp")
    @ApiImplicitParams({
         @ApiImplicitParam(name="searchText",value="搜索关键字")
    })
    @SneakyThrows
    public void exportStaffRp(HttpServletResponse response, StaffRpDTO staffRpDTO, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        Page pageResult = staffRpService.findStaffRpPage(page, staffRpDTO, searchText);
        ExcelUtils.export2Web(response, "员工奖惩情况表", "员工奖惩情况表", StaffRpDTO.class, pageResult.getRecords());
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
        SysUser sysUser = SysUserUtils.getSysUser();
        staffRp.setCreatorName(sysUser.getUsername());
        staffRp.setCreatedTime(LocalDateTime.now());
        staffRp.setCreatorCode(sysUser.getUsername());
        staffRp.setCreatorId(sysUser.getUserId());
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
        SysUser sysUser = SysUserUtils.getSysUser();
        staffRp.setModifierName(sysUser.getUsername());
        staffRp.setModifiedTime(LocalDateTime.now());
        staffRp.setModifierCode(sysUser.getUsername());
        staffRp.setModifierId(sysUser.getUserId());
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
     *
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
