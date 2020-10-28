

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
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffRpDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffRp.StaffRpUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainExcelErrDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.handler.EasyExcelSheetWriteHandler;
import net.herdao.hdp.manpower.mpclient.listener.EasyExcelListener;
import net.herdao.hdp.manpower.mpclient.service.StafftrainService;
import net.herdao.hdp.manpower.mpclient.utils.EasyExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.UserUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 员工培训
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@RestController
@RequestMapping("/stafftrain" )
@Api(value = "stafftrain", tags = "员工培训管理")
@Slf4j
public class StafftrainController{

    @Autowired
    private StafftrainService stafftrainService;


    /**
     * 分页查询
     * @param page 分页对象
     * @param stafftrain 员工培训
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_stafftrain_view')" )
    public R getStafftrainPage(Page page, Stafftrain stafftrain) {
        return R.ok(stafftrainService.page(page, Wrappers.query(stafftrain)));
    }

    /**
     * 通过id删除员工培训
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工培训", notes = "通过id删除员工培训")
    @SysLog("通过id删除员工培训" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_stafftrain_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(stafftrainService.removeById(id));
    }

    /**
     * 员工培训分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    @ApiOperation(value = "员工培训分页", notes = "员工培训分页")
    @GetMapping("/findStaffTrainPage")
    @OperationEntity(operation = "员工培训分页" ,clazz = Stafftrain.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffTrainPage(Page page, String searchText) {
        Page pageResult = stafftrainService.findStaffTrainPage(page,searchText);
        return R.ok(pageResult);
    }

    /**
     * 导出员工培训Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工培训Excel", notes = "导出员工培训Excel")
    @SysLog("导出员工培训Excel")
    @PostMapping("/exportTrain")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    public void exportTrain(HttpServletResponse response,String searchText) {
        try {
            List<StafftrainDTO> list = stafftrainService.findStaffTrain(searchText);
            ExcelUtils.export2Web(response, "员工培训表", "员工培训表", StafftrainDTO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            R.ok("导出失败");
        }

        R.ok("导出成功");
    }

    /**
     * 修改
     * @param stafftrain
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @SysLog("修改" )
    @PutMapping("/updateTrain" )
    public R updateById(@RequestBody Stafftrain stafftrain) {
        Integer userId = UserUtils.getUserId();
        stafftrain.setModifiedTime(new Date());
        stafftrain.setModifierCode(userId.toString());
        boolean status = stafftrainService.updateById(stafftrain);
        return R.ok(status);
    }

    /**
     * 新增员工培训
     * @param staffTrain
     * @return R
     */
    @ApiOperation(value = "新增员工培训", notes = "新增员工培训")
    @SysLog("新增员工培训" )
    @GetMapping("/saveStaffTrain" )
    public R saveStaffTrain(@RequestBody Stafftrain staffTrain) {
        String username = UserUtils.getUsername();
        Integer userId = UserUtils.getUserId();
        staffTrain.setCreatedTime(new Date());
        staffTrain.setCreatorCode(userId.toString());
        boolean status = stafftrainService.save(staffTrain);
        return R.ok(status);
    }

    /**
     * 批量导入员工培训（excel导入)
     * @param file
     * @return R
     */
    @ApiOperation(value = "批量导入员工培训 (excel导入)", notes = "批量导入员工培训 (excel导入)")
    @PostMapping("/batchImportTrain")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "导入文件"),
            @ApiImplicitParam(name = "importType", value = "导入类型，值： 0  批量新增； 值 1 批量修改"),
    })
    public R batchImportTrain(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file, Integer importType) {
        try {
            EasyExcelListener easyExcelListener = new EasyExcelListener(stafftrainService, StaffTrainAddDTO.class,importType);
            EasyExcelFactory.read(file.getInputStream(), StaffTrainAddDTO.class, easyExcelListener).sheet().headRowNumber(2).doRead();
            List<ExcelCheckErrDTO> errList = easyExcelListener.getErrList();
            if (!errList.isEmpty()) {
                // 包含错误信息就导出错误信息
                List<StaffTrainExcelErrDTO> excelErrDtos = errList.stream().map(excelCheckErrDto -> {
                    StaffTrainExcelErrDTO excelErrDto = JSON.parseObject(JSON.toJSONString(excelCheckErrDto.getT()), StaffTrainExcelErrDTO.class);
                    excelErrDto.setErrMsg(excelCheckErrDto.getErrMsg());
                    return excelErrDto;
                }).collect(Collectors.toList());
                EasyExcelUtils.webWriteExcel(response, excelErrDtos, StaffTrainExcelErrDTO.class, "批量导入员工培训错误信息");
            }
            return R.ok("导入成功！");
        } catch (IOException e) {
            log.error("导入失败",e.toString());
            return R.failed(e.getMessage());
        }
    }

    /**
     * 通过id查询员工奖惩表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工奖惩表", notes = "通过id查询员工奖惩表")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(stafftrainService.getById(id));
    }

    /**
     * 下载员工培训新增、编辑模板
     * @param response
     * @param importType
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "下载员工培训新增、编辑模板")
    @GetMapping("/downloadTemplate")
    @ApiImplicitParam(name = "importType", value = "导入类型，值： 0  批量新增； 值 1 批量修改")
    public R downloadTemplate(HttpServletResponse response, Integer importType) {
        if (importType!=null){
            if (importType==0){
                try {
                    EasyExcelUtils.webWriteExcel(response, new ArrayList<>(), StaffTrainAddDTO.class, "批量新增员工培训模板",
                            new EasyExcelSheetWriteHandler(8 , stafftrainService.getAddRemarks()));
                } catch (IOException e) {
                    e.printStackTrace();
                    R.failed("下载模板异常：" + e.getMessage());
                }
            }

            if (importType==1){
                List<StafftrainDTO> staffTrainList = stafftrainService.findStaffTrain(null);
                try {
                    EasyExcelUtils.webWriteExcel(response, staffTrainList, StaffTrainUpdateDTO.class, "批量编辑员工培训模板",
                            new EasyExcelSheetWriteHandler(8 , stafftrainService.getUpdateRemarks()));
                } catch (IOException e) {
                    e.printStackTrace();
                    R.failed("下载模板异常：" + e.getMessage());
                }
            }
        }

        return R.ok(null, "下载模板成功！");
    }
}
