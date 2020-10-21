package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffRpDTO;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.listener.ImportExcelListener;
import net.herdao.hdp.manpower.mpclient.service.StaffRewardsPulishmentsService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.UserUtils;
import net.herdao.hdp.manpower.mpclient.vo.StaffRpErrMsg;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Date;
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
public class StaffRewardsPulishmentsController extends BaseController<StaffRewardsPulishments> {
    @Autowired
    private StaffRewardsPulishmentsService staffRewardsPulishmentsService;

    @Autowired
    public void setEntityService(StaffRewardsPulishmentsService staffRewardsPulishmentsService) {
        super.entityService = staffRewardsPulishmentsService;
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
        return R.ok(staffRewardsPulishmentsService.page(page, Wrappers.query(staffRewardsPulishments)));
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
        Page pageResult = staffRewardsPulishmentsService.findStaffRpPage(page, searchText);
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
            List<StaffRpDTO> list = staffRewardsPulishmentsService.findStaffRp(searchText);
            ExcelUtils.export2Web(response, "员工奖惩情况表", "员工奖惩情况表", StaffRpDTO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }


    @ApiOperation("导入员工奖惩")
    @SysLog("导入员工奖惩")
    @PostMapping("/importStaffRp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "要导入的文件"),
            @ApiImplicitParam(name = "importType", value = "0:新增，1编辑"),
    })
    public R importStaffRp(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file, Integer importType) throws Exception {
        ImportExcelListener listener = new ImportExcelListener(staffRewardsPulishmentsService,null, importType);
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, StaffRpErrMsg.class, listener).sheet().doRead();
            IOUtils.closeQuietly(inputStream);
        } catch (Exception ex) {
            ExcelUtils.export2Web(response, "员工奖惩错误信息", "员工奖惩错误信息", StaffRpErrMsg.class, listener.getDataList());
           /* return R.failed(ex.getMessage());*/
        }

        /*return R.ok("easyexcel读取上传文件成功");*/
        return null;
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
        boolean status = staffRewardsPulishmentsService.updateById(staffRewardsPulishments);
        return R.ok(status);
    }


}
