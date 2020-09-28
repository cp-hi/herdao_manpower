

package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import net.herdao.hdp.manpower.mpclient.service.UserposthistoryService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 员工岗位历史表
 *
 * @author yangrr
 * @date 2020-09-25 17:24:25
 */
@RestController
@AllArgsConstructor
@RequestMapping("/userposthistory" )
@Api(value = "userposthistory", tags = "员工岗位历史表管理")
public class UserposthistoryController {

    private final  UserposthistoryService userposthistoryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param userposthistory 员工岗位历史表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('mpclient_userposthistory_view')" )
    public R getUserposthistoryPage(Page page, Userposthistory userposthistory) {
        return R.ok(userposthistoryService.page(page, Wrappers.query(userposthistory)));
    }


    /**
     * 通过id查询员工岗位历史表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_userposthistory_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(userposthistoryService.getById(id));
    }

    /**
     * 新增员工岗位历史表
     * @param userposthistory 员工岗位历史表
     * @return R
     */
    @ApiOperation(value = "新增员工岗位历史表", notes = "新增员工岗位历史表")
    @SysLog("新增员工岗位历史表" )
    @PostMapping("/saveUserpostHis")
//    @PreAuthorize("@pms.hasPermission('mpclient_userposthistory_add')" )
    public R save(@RequestBody Userposthistory userposthistory) {
        boolean status = userposthistoryService.saveHistory(userposthistory);
        return R.ok(status);
    }

    /**
     * 修改员工岗位历史表
     * @param userposthistory 员工岗位历史表
     * @return R
     */
    @ApiOperation(value = "修改员工岗位历史表", notes = "修改员工岗位历史表")
    @SysLog("修改员工岗位历史表" )
    @PutMapping("/updateHis")
    //@PreAuthorize("@pms.hasPermission('mpclient_userposthistory_edit')" )
    public R updateById(@RequestBody Userposthistory userposthistory) {
        return R.ok(userposthistoryService.updateHistory(userposthistory));
    }

    /**
     * 通过id删除员工岗位历史表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工岗位历史表", notes = "通过id删除员工岗位历史表")
    @SysLog("通过id删除员工岗位历史表" )
    @DeleteMapping("/del/{id}" )
    // @PreAuthorize("@pms.hasPermission('mpclient_userposthistory_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(userposthistoryService.removeById(id));
    }


    /**
     * 历史职情况分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "历史职情况分页", notes = "历史职情况分页")
    @GetMapping("/findUserPostHistoryPage")
    @OperationEntity(operation = "历史职情况分页" ,clazz = Stafftransaction.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findUserPostHistoryPage(Page page, String orgId,String staffName, String staffCode) {
        Page pageResult = userposthistoryService.findUserPostHistoryPage(page, orgId, staffName, staffCode);
        return R.ok(pageResult);
    }

    /**
     * 导出历史任职情况Excel
     * @param  response
     * @return R
     */
    @ApiOperation(value = "导出历史任职情况Excel", notes = "导出历史任职情况Excel")
    @SysLog("导出历史任职情况Excel" )
    @PostMapping("/exportStaffJobHis")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号")
    })
    public R exportStaffJobHis(HttpServletResponse response, String orgId, String staffName, String staffCode) {
        try {
            List<UserpostDTO> list = userposthistoryService.findUserPostHistory(orgId, staffName, staffCode);
            ExcelUtils.export2Web(response, "历史任职情况", "历史任职情况", UserpostDTO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }

}
