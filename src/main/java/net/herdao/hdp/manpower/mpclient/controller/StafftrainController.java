

package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.StaffeducationListDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.listener.ImportExcelListener;
import net.herdao.hdp.manpower.mpclient.service.StaffcontractService;
import net.herdao.hdp.manpower.mpclient.service.StafftrainService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.StafftrainVO;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;


/**
 * 员工培训
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@RestController
@RequestMapping("/stafftrain" )
@Api(value = "stafftrain", tags = "员工培训管理")
public class StafftrainController extends BaseController<Stafftrain> {

    @Autowired
    private StafftrainService stafftrainService;

    @Autowired
    public void setEntityService( StafftrainService stafftrainService) {
        super.entityService = stafftrainService;
    }

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

    @ApiOperation("导入员工培训")
    @SysLog("导入员工培训")
    @PostMapping("/importStaffTrain")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "要导入的文件"),
            @ApiImplicitParam(name = "importType", value = "0:新增，1编辑"),
    })
    public R importStaffTrain(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file, Integer importType) throws Exception {
        ImportExcelListener listener = new ImportExcelListener(stafftrainService,10, importType);
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, StafftrainVO.class, listener).sheet().doRead();
            IOUtils.closeQuietly(inputStream);
        } catch (Exception ex) {
            ExcelUtils.export2Web(response, "员工培训错误信息", "员工培训错误信息", StafftrainVO.class, listener.getDataList());
            //return R.failed("导入员工教育经历失败",ex.getMessage());
        }
       // return R.ok("导入员工教育经历成功");
        return null;
    }
}
