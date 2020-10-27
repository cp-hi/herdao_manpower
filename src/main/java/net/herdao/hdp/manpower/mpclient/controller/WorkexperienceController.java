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
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEduAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffEdu.StaffEduExcelErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.StaffWorkAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.StaffWorkExcelErrDTO;
import net.herdao.hdp.manpower.mpclient.listener.EasyExcelListener;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import net.herdao.hdp.manpower.mpclient.utils.EasyExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.mpclient.dto.staff.WorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.Workexperience;
import net.herdao.hdp.manpower.mpclient.service.WorkexperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 员工工作经历
 * @author andy
 * @date 2020-09-24 10:24:09
 */
@RestController
@RequestMapping("/workexperience" )
@Api(value = "workexperience", tags = "员工工作经历管理")
@Slf4j
public class WorkexperienceController {

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
    @OperationEntity(operation = "员工工作经历分页" ,clazz = Workexperience.class )
    @ApiImplicitParams({
          @ApiImplicitParam(name="searchText",value="关键字搜索"),
          @ApiImplicitParam(name="staffId",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffWorkPage(Page page, String searchText,String staffId) {
        Page pageResult = workexperienceService.findStaffWorkPage(page, searchText,staffId);
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
    public R exportStaffWork(HttpServletResponse response, String searchText,String staffId) {
        try {
            List<WorkexperienceDTO> list = workexperienceService.findStaffWork(searchText,staffId);
            ExcelUtils.export2Web(response, "员工工作经历", "员工工作经历表", WorkexperienceDTO.class,list);
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
    @PostMapping("/{id}" )
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

    /**
     * 批量导入员工工作经历（excel导入)
     * @param file
     * @return R
     */
    @ApiOperation(value = "批量导入员工工作经历(excel导入)", notes = "批量导入员工工作经历(excel导入)")
    @GetMapping("/batchImportWork")
    @ResponseBody
    @ApiImplicitParams({ @ApiImplicitParam(name = "file", value = "导入文件"),
        @ApiImplicitParam(name = "importType", value = "导入类型，值： 0  批量新增； 值 1 批量修改"),
    })
    public R batchImportWork(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file, Integer importType) {
        try {
            EasyExcelListener easyExcelListener = new EasyExcelListener(workexperienceService, StaffWorkAddDTO.class,importType);
            EasyExcelFactory.read(file.getInputStream(), StaffWorkAddDTO.class, easyExcelListener).sheet().headRowNumber(2).doRead();
            List<ExcelCheckErrDTO> errList = easyExcelListener.getErrList();
            if (!errList.isEmpty()) {
                // 包含错误信息就导出错误信息
                List<StaffWorkExcelErrDTO> excelErrDtos = errList.stream().map(excelCheckErrDto -> {
                    StaffWorkExcelErrDTO excelErrDto = JSON.parseObject(JSON.toJSONString(excelCheckErrDto.getT()), StaffWorkExcelErrDTO.class);
                    excelErrDto.setErrMsg(excelCheckErrDto.getErrMsg());
                    return excelErrDto;
                }).collect(Collectors.toList());
                EasyExcelUtils.webWriteExcel(response, excelErrDtos, StaffWorkExcelErrDTO.class, "批量导入员工工作经历错误信息");
            }
            return R.ok("导入成功！");
        } catch (IOException e) {
            log.error("导入失败",e.toString());
            return R.failed(e.getMessage());
        }
    }
}
