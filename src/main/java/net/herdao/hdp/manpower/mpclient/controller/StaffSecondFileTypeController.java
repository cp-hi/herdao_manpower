

package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.StaffFileTypeDto;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import net.herdao.hdp.manpower.mpclient.service.StaffSecondFileTypeService;
import net.herdao.hdp.manpower.mpclient.utils.UserUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 员工附件二级分类
 *
 * @author andy
 * @date 2020-09-29 11:17:03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffsecondfiletype" )
@Api(value = "staffsecondfiletype", tags = "员工附件二级分类管理")
public class StaffSecondFileTypeController {

    private final  StaffSecondFileTypeService staffSecondFileTypeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffSecondFileType 员工附件二级分类
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffsecondfiletype_view')" )
    public R getStaffSecondFileTypePage(Page page, StaffSecondFileType staffSecondFileType) {
        return R.ok(staffSecondFileTypeService.page(page, Wrappers.query(staffSecondFileType)));
    }


    /**
     * 通过id查询员工附件二级分类
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('mpclient_staffsecondfiletype_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffSecondFileTypeService.getById(id));
    }

    /**
     * 新增员工附件二级分类
     * @param staffSecondFileType 员工附件二级分类
     * @return R
     */
    @ApiOperation(value = "新增员工附件二级分类", notes = "新增员工附件二级分类")
    @SysLog("新增员工附件二级分类" )
    @PostMapping("/saveStaffFileType")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffsecondfiletype_add')" )
    public R save(@RequestBody StaffSecondFileType staffSecondFileType) {
        boolean status = staffSecondFileTypeService.saveStaffFileType(staffSecondFileType);
        return R.ok(status);
    }

    /**
     * 修改员工附件二级分类
     * @param staffSecondFileType 员工附件二级分类
     * @return R
     */
    @ApiOperation(value = "修改员工附件二级分类", notes = "修改员工附件二级分类")
    @SysLog("修改员工附件二级分类" )
    @PutMapping("/updateStaffFile")
    //@PreAuthorize("@pms.hasPermission('mpclient_staffsecondfiletype_edit')" )
    public R updateById(@RequestBody StaffSecondFileType staffSecondFileType) {
        boolean flag = staffSecondFileTypeService.updateStaffFileType(staffSecondFileType);
        return R.ok(flag);
    }

    /**
     * 通过id删除员工附件二级分类
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工附件二级分类", notes = "通过id删除员工附件二级分类")
    @SysLog("通过id删除员工附件二级分类" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_staffsecondfiletype_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(staffSecondFileTypeService.removeById(id));
    }

    /**
     * 员工附件分类分页
     * @param page 分页对象
     * @param page entity
     * @return
     */
    @ApiOperation(value = "员工附件分类分页", notes = "员工附件分类分页")
    @GetMapping("/findStaffFileTypePage")
    @OperationEntity(operation = "员工附件分类分页" ,clazz = Staffeducation.class )
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgId",value="组织ID"),
        @ApiImplicitParam(name="staffName",value="员工姓名"),
        @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffFileTypePage(Page page, StaffFileTypeDto entity) {
        Page pageResult = staffSecondFileTypeService.findStaffFileTypePage(page, entity);
        return R.ok(pageResult);
    }
}
