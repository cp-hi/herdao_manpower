

package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.familyStatus.FamilyStatusListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.listener.ImportExcelListener;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.FamilyStatusVO;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.mpclient.service.FamilystatusService;
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
 * 员工家庭成员
 *
 * @author andy
 * @date 2020-09-23 10:53:08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/familystatus" )
@Api(value = "familystatus", tags = "员工家庭成员管理")
public class FamilystatusController extends BaseController<Familystatus> {

    private final  FamilystatusService familystatusService;

    @Autowired
    public void setEntityService(FamilystatusService familystatusService) {
        super.entityService = familystatusService;
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
    @PostMapping("/findFamilyStatusPage")
    @OperationEntity(operation = "员工教育经历分页" ,clazz = Organization.class )
    @ApiImplicitParams({
         @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findFamilyStatusPage(Page page, String searchText) {
        Page pageResult = familystatusService.findFamilyStatusPage(page, searchText);
        return R.ok(pageResult);
    }

    @ApiOperation("导入家庭情况")
    @SysLog("导入家庭情况")
    @PostMapping("/importFamilystatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "要导入的文件"),
            @ApiImplicitParam(name = "importType", value = "0:新增，1编辑"),
    })
    public R importFamilystatus(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file,Integer importType) throws Exception {
        ImportExcelListener listener = new ImportExcelListener(familystatusService, importType);
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, FamilyStatusListDTO.class, listener).sheet().doRead();
            IOUtils.closeQuietly(inputStream);
            return R.ok(" easyexcel读取上传文件成功");
        } catch (Exception ex) {
            ExcelUtils.export2Web(response, "家庭情况错误信息", "家庭情况错误信息", FamilyStatusListDTO.class, listener.getDataList());
            return R.failed(ex.getMessage());
        }
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

}
