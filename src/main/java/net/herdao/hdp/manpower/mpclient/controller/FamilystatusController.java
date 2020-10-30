package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.constant.ExcelDescriptionContants;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffContract.*;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.StaffFamilyAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.StaffFamilyAddErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.StaffFamilyUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.StaffFamilyUpdateErrDTO;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.handler.EasyExcelSheetWriteHandler;
import net.herdao.hdp.manpower.mpclient.listener.EasyExcelListener;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.utils.EasyExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.FamilyStatusVO;
import net.herdao.hdp.manpower.mpclient.service.FamilystatusService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 员工家庭成员
 * @author andy
 * @date 2020-09-23 10:53:08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/familystatus" )
@Api(value = "familystatus", tags = "员工家庭成员管理")
@Slf4j
public class FamilystatusController extends HdpBaseController  {

    private FamilystatusService familystatusService;

    @Override
    public HdpService getHdpService() {
        return familystatusService;
    }

    @Override
    public Class getImportAddCls() {
        return StaffFamilyAddDTO.class;
    }

    @Override
    public Class getImportAddErrCls() {
        return StaffFamilyAddErrDTO.class;
    }

    @Override
    public Class getImportUpdateCls() {
        return StaffFamilyUpdateDTO.class;
    }

    @Override
    public Class getImportUpdateErrCls() {
        return StaffFamilyUpdateErrDTO.class;
    }

    @Override
    public String getExcelAddDescription() {
        return ExcelDescriptionContants.getFamilyAddDesc();
    }

    @Override
    public String getExcelUpdateDescription() {
        return ExcelDescriptionContants.getFamilyUpdateDesc();
    }

    @Override
    public List getDownloadUpdateTemplateList() {
        List<FamilyStatusVO> list = this.familystatusService.findFamilyStatus(null);
        return list;
    }

    @Override
    public String getTemplateName() {
        return "员工家庭情况";
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param familystatus 员工家庭成员
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_familystatus_view')" )
    public R page(Page page, Familystatus familystatus) {
        return R.ok(familystatusService.page(page, Wrappers.query(familystatus)));
    }

    /**
     * 员工家庭情况分页
     * @param page 分页对象
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "员工教育经历分页", notes = "员工教育经历分页")
    @GetMapping("/findFamilyStatusPage")
    @ApiImplicitParams({
         @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findFamilyStatusPage(Page page, String searchText) {
        Page pageResult = familystatusService.findFamilyStatusPage(page, searchText);
        return R.ok(pageResult);
    }

    /**
     * 导出家庭情况Excel
     * @param response
     * @param searchText 关键字搜索
     * @return R
     */
    @ApiOperation(value = "导出家庭情况Excel", notes = "导出家庭情况Excel")
    @SysLog("导出家庭情况Excel")
    @PostMapping("/exportFamily")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    public void exportFamily(HttpServletResponse response, String searchText) {
        try {
            List<FamilyStatusVO> list = familystatusService.findFamilyStatus(searchText);
            ExcelUtils.export2Web(response, "家庭情况况表", "家庭情况表", FamilyStatusVO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            R.ok("导出失败");
        }

        R.ok("导出成功");
    }

    /**
     * 新增或修改家庭情况
     * @param
     * @return R
     */
    @ApiOperation(value = "新增或修改家庭情况", notes = "新增或修改家庭情况")
    @SysLog("新增或修改家庭情况" )
    @PostMapping("/saveOrUpdate")
    public R saveOrUpdate(@RequestBody Familystatus familystatus) {
        boolean status = familystatusService.saveOrUpdate(familystatus);
        return R.ok(status);
    }

    /**
     * 通过id删除员工家庭成员表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工家庭成员表", notes = "通过id删除员工家庭成员表")
    @SysLog("通过id删除员工家庭成员表" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(familystatusService.removeById(id));
    }

    /**
     * 通过id查询员工家庭成员表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工家庭成员表", notes = "通过id查询员工家庭成员表")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('demo_demo_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(familystatusService.getById(id));
    }

}
