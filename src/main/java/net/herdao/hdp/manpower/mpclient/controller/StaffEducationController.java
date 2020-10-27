package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.StaffeducationListDTO;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffEducationDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEduAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEduExcelErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainExcelErrDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.listener.EasyExcelListener;
import net.herdao.hdp.manpower.mpclient.listener.ImportExcelListener;
import net.herdao.hdp.manpower.mpclient.utils.EasyExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.UserUtils;
import net.herdao.hdp.manpower.mpclient.vo.StaffeducationVO;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.mpclient.service.StaffeducationService;
import org.apache.commons.io.IOUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
public class StaffEducationController {

    private final  StaffeducationService staffeducationService;

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
    @SysLog("新增员工教育经历" )
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
    @SysLog("修改员工教育经历" )
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
    @SysLog("通过id删除员工教育经历" )
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
    @OperationEntity(operation = "员工教育经历分页" ,clazz = Staffeducation.class )
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
    @SysLog("导出员工教育经历Excel")
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

    /**
     * 批量导入员工教育（excel导入)
     * @param file
     * @return R
     */
    @ApiOperation(value = "批量导入员工教育 (excel导入)", notes = "批量导入员工教育 (excel导入)")
    @GetMapping("/batchImportEdu")
    @ResponseBody
    @ApiImplicitParams({ @ApiImplicitParam(name = "file", value = "导入文件"),
        @ApiImplicitParam(name = "importType", value = "导入类型，值： 0  批量新增； 值 1 批量修改"),
    })
    public R batchImportEdu(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file, Integer importType) {
        try {
            EasyExcelListener easyExcelListener = new EasyExcelListener(staffeducationService, StaffEduAddDTO.class,importType);
            EasyExcelFactory.read(file.getInputStream(), StaffEduAddDTO.class, easyExcelListener).sheet().headRowNumber(2).doRead();
            List<ExcelCheckErrDTO> errList = easyExcelListener.getErrList();
            if (!errList.isEmpty()) {
                // 包含错误信息就导出错误信息
                List<StaffEduExcelErrDTO> excelErrDtos = errList.stream().map(excelCheckErrDto -> {
                    StaffEduExcelErrDTO excelErrDto = JSON.parseObject(JSON.toJSONString(excelCheckErrDto.getT()), StaffEduExcelErrDTO.class);
                    excelErrDto.setErrMsg(excelCheckErrDto.getErrMsg());
                    return excelErrDto;
                }).collect(Collectors.toList());
                EasyExcelUtils.webWriteExcel(response, excelErrDtos, StaffEduExcelErrDTO.class, "批量导入员工教育错误信息");
            }
            return R.ok("导入成功！");
        } catch (IOException e) {
            log.error("导入失败",e.toString());
            return R.failed(e.getMessage());
        }
    }

}
