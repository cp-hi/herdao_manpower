

package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.StaffFileDTO;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.StaffFile;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;
import net.herdao.hdp.manpower.mpclient.service.StaffFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 员工附件表
 * @author andy
 * @date 2020-09-30 10:39:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/stafffile" )
@Api(value = "stafffile", tags = "员工附件表管理")
public class StaffFileController {

    private final  StaffFileService staffFileService;

    private final OperationLogService operationLogService;


    /**
     * 分页查询
     * @param page 分页对象
     * @param staffFile 员工附件表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    //@PreAuthorize("@pms.hasPermission('mpclient_stafffile_view')" )
    public R getStaffFilePage(Page page, StaffFile staffFile) {
        return R.ok(staffFileService.page(page, Wrappers.query(staffFile)));
    }

    /**
     * 通过id查询员工附件表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_stafffile_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffFileService.getById(id));
    }

    /**
     * 新增或修改员工附件表
     * @param staffFile 员工附件表
     * @return R
     */
    @ApiOperation(value = "新增或修改员工附件表", notes = "新增或修改员工附件表")
    @SysLog("新增或修改员工附件表" )
    @PostMapping("/saveOrUpdate")
    //@PreAuthorize("@pms.hasPermission('mpclient_stafffile_add')" )
    public R saveOrUpdate(@RequestBody StaffFile staffFile) {
        return R.ok(staffFileService.saveOrUpdate(staffFile));
    }

    /**
     * 修改员工附件表
     * @param staffFile 员工附件表
     * @return R
     */
    @ApiOperation(value = "修改员工附件表", notes = "修改员工附件表")
    @SysLog("修改员工附件表" )
    @PutMapping
    //@PreAuthorize("@pms.hasPermission('mpclient_stafffile_edit')" )
    public R updateById(@RequestBody StaffFile staffFile) {
        return R.ok(staffFileService.updateById(staffFile));
    }

    /**
     * 通过id删除员工附件表
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工附件表", notes = "通过id删除员工附件表")
    @SysLog("通过id删除员工附件表" )
    @DeleteMapping("/del/{id}" )
    //@OperationEntity(operation = "删除员工附件", key ="id" ,clazz = StaffFile.class)
    public R removeById(@PathVariable Long id,String extraKey,String module) {
        StaffFile entity=new StaffFile();
        entity.setId(id);
        if (StringUtils.isNotBlank(extraKey)){
            entity.setExtraKey(extraKey);
        }
        if (StringUtils.isNotBlank(module)){
            entity.setModule(module);
        }

        boolean status = staffFileService.delEntity(entity);
        return R.ok(status);
    }


    /**
     * 员工附件分页
     * @param page 分页对象
     * @param page entity
     * @return
     */
    @ApiOperation(value = "员工附件分页", notes = "员工附件分页")
    @GetMapping("/findStaffFilePage")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    public R findStaffFilePage(Page page, StaffFileDTO entity) {
        Page pageResult = staffFileService.findStaffFilePage(page, entity);
        return R.ok(pageResult);
    }


    /**
     * 查询员工上传附件
     * @param staffFile 员工附件表
     * @return
     */
    @ApiOperation(value = "查询员工上传附件", notes = "查询员工上传附件")
    @GetMapping("/findStaffFile" )
    //@PreAuthorize("@pms.hasPermission('mpclient_stafffile_view')" )
    public R findStaffFile(StaffFile staffFile) {
        List<StaffFile> list = staffFileService.list(Wrappers.query(staffFile));
        return R.ok(list);
    }

    @ApiOperation(value = "获取员工附件操作日志")
    @GetMapping("/getOperateLog")
    @ApiImplicitParams({
         @ApiImplicitParam(name="extraKey",value="额外信息"),
         @ApiImplicitParam(name="module",value="模块名")
    })
    public R getOperateLog(OperationLog operationLog) {
        List<OperationLog> list = operationLogService.findOperationLog(operationLog);
        return R.ok(list);
    }
}
