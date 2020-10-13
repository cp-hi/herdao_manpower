

package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.JobLevelDTO;
import net.herdao.hdp.manpower.mpclient.dto.familyStatus.FamilyStatusListDto;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.listener.FamilystatusListener;
import net.herdao.hdp.manpower.mpclient.listener.ImportExcelListener;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.mpclient.service.FamilystatusService;
import org.apache.commons.io.IOUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;


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
public class FamilystatusController {

    private final  FamilystatusService familystatusService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param familystatus 员工家庭成员
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_familystatus_view')" )
    public R getFamilystatusPage(Page page, Familystatus familystatus) {
        return R.ok(familystatusService.page(page, Wrappers.query(familystatus)));
    }


    /**
     * 通过id查询员工家庭成员
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_familystatus_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(familystatusService.getById(id));
    }

    /**
     * 新增员工家庭成员
     * @param familystatus 员工家庭成员
     * @return R
     */
    @ApiOperation(value = "新增员工家庭成员", notes = "新增员工家庭成员")
    @SysLog("新增员工家庭成员" )
    @PostMapping("/saveFamily")
   // @PreAuthorize("@pms.hasPermission('mpclient_familystatus_add')" )
    public R saveFamily(@RequestBody Familystatus familystatus) {
        return R.ok(familystatusService.save(familystatus));
    }

    /**
     * 修改员工家庭成员
     * @param familystatus 员工家庭成员
     * @return R
     */
    @ApiOperation(value = "修改员工家庭成员", notes = "修改员工家庭成员")
    @SysLog("修改员工家庭成员" )
    @PutMapping("/updateFamily")
    // @PreAuthorize("@pms.hasPermission('mpclient_familystatus_edit')" )
    public R updateById(@RequestBody Familystatus familystatus) {
        return R.ok(familystatusService.updateById(familystatus));
    }

    /**
     * 通过id删除员工家庭成员
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工家庭成员", notes = "通过id删除员工家庭成员")
    @SysLog("通过id删除员工家庭成员" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_familystatus_del')" )
    public R removeById(@PathVariable Long id) {
        Boolean b =  familystatusService.removeById(id);
        return R.ok(b);
    }

    /**
     * 员工家庭情况分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "员工教育经历分页", notes = "员工教育经历分页")
    @PostMapping("/findFamilyStatusPage")
    @OperationEntity(operation = "员工教育经历分页" ,clazz = Organization.class )
    @ApiImplicitParams({
          @ApiImplicitParam(name="orgId",value="组织ID"),
          @ApiImplicitParam(name="staffName",value="员工姓名"),
          @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findFamilyStatusPage(Page page, String orgId,String staffName, String staffCode) {
        Page pageResult = familystatusService.findFamilyStatusPage(page, orgId, staffName, staffCode);
        return R.ok(pageResult);
    }

    @ApiOperation("导入家庭情况")
    @SysLog("导入家庭情况")
    @PostMapping("/importFamilystatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "要导入的文件"),
            @ApiImplicitParam(name = "type", value = "操作类型，add:批量新增 update:批量修改"),
    })
    public R importData(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file, ImportExcelListener.ImportTypeEnum type) throws Exception {
        ImportExcelListener listener = new ImportExcelListener(familystatusService, type);
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, FamilyStatusListDto.class, listener).sheet().doRead();
            IOUtils.closeQuietly(inputStream);
            return R.ok(" easyexcel读取上传文件成功");
        } catch (Exception ex) {
            ExcelUtils.export2Web(response, "家庭情况错误信息", "家庭情况错误信息", FamilyStatusListDto.class, listener.dataList);
            return R.failed(ex.getMessage());
        }
    }
}
