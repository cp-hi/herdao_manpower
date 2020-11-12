package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.constant.ExcelDescriptionContants;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.*;
import net.herdao.hdp.manpower.mpclient.entity.Workexperience;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.WorkexperienceService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 员工工作经历
 * @author andy
 * @date 2020-09-24 10:24:09
 */
@RestController
@RequestMapping("/workexperience" )
@Api(value = "workexperience", tags = "员工工作经历管理")
@Slf4j
public class WorkexperienceController extends HdpBaseController {

    @Override
    public HdpService getHdpService() {
        return workexperienceService;
    }

    @Override
    public Class getImportAddCls() {
        return StaffWorkAddDTO.class;
    }

    @Override
    public Class getImportAddErrCls() {
        return StaffWorkAddErrDTO.class;
    }

    @Override
    public Class getImportUpdateCls() {
        return StaffWorkUpdateDTO.class;
    }

    @Override
    public Class getImportUpdateErrCls() {
        return StaffWorkUpdateErrDTO.class;
    }

    @Override
    public String getExcelAddDescription() {
        return ExcelDescriptionContants.getWorkAddDesc();
    }

    @Override
    public List getDownloadUpdateTemplateList() {
        List<WorkexperienceDTO> list = this.workexperienceService.findStaffWork(null, null);
        return list;
    }

    @Override
    public String getExcelUpdateDescription() {
        return ExcelDescriptionContants.getWorkUpdateDesc();
    }

    @Override
    public String getExcelName() {
        return "员工工作经历";
    }

    @Autowired
    private WorkexperienceService workexperienceService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param workexperience 员工工作经历
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_workexperience_view')" )
    public R page(Page page, Workexperience workexperience) {
        return R.ok(workexperienceService.page(page, Wrappers.query(workexperience)));
    }

    /**
     * 新增员工工作经历
     * @param workexperienceDTO 员工工作经历
     * @return R
     */
    @ApiOperation(value = "新增员工工作经历", notes = "新增员工工作经历")
    @SysLog("新增员工工作经历" )
    @PostMapping("/saveWorkDTO")
    public R saveWorkDTO(@RequestBody WorkexperienceDTO workexperienceDTO) {
        boolean flag = workexperienceService.saveWorkDTO(workexperienceDTO);
        return R.ok(flag);
    }

    /**
     * 修改员工工作经历
     * @param  workexperienceDTO
     * @return R
     */
    @ApiOperation(value = "修改员工工作经历", notes = "修改员工工作经历")
    @SysLog("修改员工工作经历" )
    @PutMapping("/updateWorkDTO")
    public R updateWorkDTO(@RequestBody WorkexperienceDTO workexperienceDTO) {
        boolean flag = workexperienceService.updateWorkDTO(workexperienceDTO);
        return R.ok(flag);
    }

    /**
     * 员工工作经历分页
     * @param page 分页对象
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "员工工作经历分页", notes = "员工工作经历分页")
    @GetMapping("/findStaffWorkPage")
    @ApiImplicitParams({
          @ApiImplicitParam(name="searchText",value="关键字搜索"),
          @ApiImplicitParam(name="staffId",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffWorkPage(Page page,WorkexperienceDTO workexperienceDTO, String searchText) {
        Page pageResult = workexperienceService.findStaffWorkPage(page, workexperienceDTO,searchText);
        return R.ok(pageResult);
    }

    /**
     * 新增员工工作经历
     * @param workexperience 员工工作经历
     * @return R
     */
    @ApiOperation(value = "新增员工工作经历", notes = "新增员工工作经历")
    @SysLog("新增员工工作经历" )
    @PostMapping("/saveWork")
    public R saveWork(@RequestBody Workexperience workexperience) {
        boolean flag = workexperienceService.saveWork(workexperience);
        return R.ok(flag);
    }

    /**
     * 编辑员工工作经历
     * @param workexperience 员工工作经历
     * @return R
     */
    @ApiOperation(value = "编辑员工工作经历", notes = "编辑员工工作经历")
    @SysLog("编辑员工工作经历" )
    @PostMapping("/updateWork")
    public R updateWork(@RequestBody Workexperience workexperience) {
        boolean flag = workexperienceService.updateWork(workexperience);
        return R.ok(flag);
    }

    /**
     * 导出员工工作经历Excel
     * @param  response
     * @param searchText
     * @return R
     */
    @ApiOperation(value = "导出员工工作经历Excel", notes = "导出员工工作经历Excel")
    @SysLog("导出员工工作经历" )
    @PostMapping("/exportStaffWork")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="搜索关键字"),
        @ApiImplicitParam(name="staffId",value="员工工号")
    })
    public R exportStaffWork(HttpServletResponse response,WorkexperienceDTO workexperienceDTO, String searchText) {
        try {
            Page page =new Page();
            page.setSize(-1);
            Page pageResult = workexperienceService.findStaffWorkPage(page, workexperienceDTO,searchText);
            ExcelUtils.export2Web(response, "员工工作经历", "员工工作经历表", WorkexperienceDTO.class,pageResult.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }

    /**
     * 通过id删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Long id) {
        return R.ok(workexperienceService.removeById(id));
    }

    /**
     * 通过id查询员工工作经历表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工工作经历表", notes = "通过id查询员工工作经历表")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(workexperienceService.getById(id));
    }


}
