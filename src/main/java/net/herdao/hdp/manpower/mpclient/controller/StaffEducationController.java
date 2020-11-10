package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.constant.ExcelDescriptionContants;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.*;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.mpclient.service.StaffeducationService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 员工教育经历
 * @author andy
 * @date 2020-09-23 17:22:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffEducation" )
@Api(value = "staffEducation", tags = "员工教育经历管理")
@Slf4j
public class StaffEducationController extends HdpBaseController {

    private final  StaffeducationService staffeducationService;

    @Override
    public HdpService getHdpService() {
        return staffeducationService;
    }

    @Override
    public Class getImportAddCls() {
        return StaffEduAddDTO.class;
    }

    @Override
    public Class getImportAddErrCls() {
        return StaffEduAddErrDTO.class;
    }

    @Override
    public Class getImportUpdateCls() {
        return StaffEduUpdateDTO.class;
    }

    @Override
    public Class getImportUpdateErrCls() {
        return StaffEduUpdateErrDTO.class;
    }

    @Override
    public String getExcelAddDescription() {
        return ExcelDescriptionContants.getEduAddDesc();
    }

    @Override
    public List getDownloadUpdateTemplateList() {
        List<StaffEducationDTO> list = this.staffeducationService.findStaffEducation(null, null);
        return list;
    }

    @Override
    public String getExcelUpdateDescription() {
        return ExcelDescriptionContants.getEduUpdateDesc();
    }

    @Override
    public String getExcelName() {
        return "员工教育";
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffeducation 员工教育经历
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffeducation_view')" )
    public R page(Page page, Staffeducation staffeducation) {
        return R.ok(staffeducationService.page(page, Wrappers.query(staffeducation)));
    }


    /**
     * 通过id查询员工教育经历
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_staffeducation_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffeducationService.getById(id));
    }

    /**
     * 通过员工id查询员工教育经历
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过staff_id查询", notes = "通过staff_id查询")
    @GetMapping("/staff/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staffeducation_view')" )
    public R getByStaffId(@PathVariable("id" ) Long id) {
        List<Staffeducation> eduList = staffeducationService.list(new QueryWrapper<Staffeducation>()
                .eq("STAFF_ID", id)
                .orderByDesc("BEGIN_DATE")
        );
        return R.ok(eduList);
    }

    /**
     * 新增员工教育经历
     * @param staffeducation 员工教育经历
     * @return R
     */
    @ApiOperation(value = "新增员工教育经历", notes = "新增员工教育经历")
    @PostMapping("/saveEducation")
   // @PreAuthorize("@pms.hasPermission('mpclient_staffeducation_add')" )
    public R saveEducation(@RequestBody Staffeducation staffeducation) {
        boolean status = staffeducationService.saveEdu(staffeducation);
        return R.ok(status);
    }

    /**
     * 修改员工教育经历
     * @param staffeducation 员工教育经历
     * @return R
     */
    @ApiOperation(value = "修改员工教育经历", notes = "修改员工教育经历")
    @PutMapping("/updateEducation")
    // @PreAuthorize("@pms.hasPermission('mpclient_staffeducation_edit')" )
    public R updateById(@RequestBody Staffeducation staffeducation) {
        boolean status = staffeducationService.updateEdu(staffeducation);
        return R.ok(status);
    }

    /**
     * 通过id删除员工教育经历
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工教育经历", notes = "通过id删除员工教育经历")
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_staffeducation_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(staffeducationService.removeById(id));
    }

    /**
     * 员工教育经历分页
     * @param page 分页对象
     * @param searchText 关键字搜索
     * @param staffId 员工工号
     * @return
     */
    @ApiOperation(value = "员工教育经历分页", notes = "员工教育经历分页")
    @GetMapping("/findStaffEducationPage")
     @ApiImplicitParams({
            @ApiImplicitParam(name="searchText",value="关键字搜索"),
            @ApiImplicitParam(name="staffId",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffEducationPage(Page page, String searchText,String staffId) {
        Page pageResult = staffeducationService.findStaffEducationPage(page, searchText, staffId);
        return R.ok(pageResult);
    }

    /**
     * 导出员工教育经历Excel
     * @param searchText 关键字搜索
     * @param staffId 员工工号
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工教育经历Excel", notes = "导出员工教育经历Excel")
    @PostMapping("/exportStaffEdu")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
        @ApiImplicitParam(name="staffId",value="员工工号")
    })
    public void exportStaffEdu(HttpServletResponse response, String searchText,String staffId) {
        try {
            List<StaffEducationDTO> list = staffeducationService.findStaffEducation(searchText,staffId);
            ExcelUtils.export2Web(response, "员工教育经历表", "员工教育经历表", StaffEducationDTO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            R.ok("导出失败");
        }

        R.ok("导出成功");
    }


}
