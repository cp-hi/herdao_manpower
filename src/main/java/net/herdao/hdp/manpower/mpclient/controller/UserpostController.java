
package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;

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
 * @author andy
 * @date 2020-09-15 08:57:53
 */
@RestController
@AllArgsConstructor
@RequestMapping("/userpost" )
@Api(value = "userpost", tags = "用户岗位")
public class UserpostController {

    private final UserpostService userpostService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param userpost 
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('generator_userpost_view')" )
    public R getUserpostPage(Page page, Userpost userpost) {
        return R.ok(userpostService.page(page, Wrappers.query(userpost)));
    }


    /**
     * 通过id查询
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_userpost_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(userpostService.getById(id));
    }

    /**
     * 新增现任职情况
     * @param userpost 
     * @return R
     */
    @ApiOperation(value = "新增", notes = "新增")
    @SysLog("新增" )
    @PostMapping("/saveUserPostNow")
    //@PreAuthorize("@pms.hasPermission('generator_userpost_add')" )
    public R save(@RequestBody Userpost userpost) {
        boolean status = userpostService.saveUserPostNow(userpost);
        return R.ok(status);
    }

    /**
     * 修改
     * @param userpost 
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @SysLog("修改" )
    @PutMapping("/updateUserpostNow")
    //@PreAuthorize("@pms.hasPermission('generator_userpost_edit')" )
    public R updateById(@RequestBody Userpost userpost) {
        boolean status = userpostService.updateUserPostNow(userpost);
        return R.ok(status);
    }

    /**
     * 通过id删除
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除")
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('generator_userpost_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(userpostService.removeById(id));
    }

    /**
     * 现任职情况分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    @ApiOperation(value = "现任职情况分页", notes = "现任职情况分页")
    @GetMapping("/findUserPostNowPage")
    @OperationEntity(operation = "现任职情况分页" ,clazz = Stafftransaction.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号"),
            @ApiImplicitParam(name="staffId",value="员工id")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findUserPostNowPage(Page page, String orgId,String staffName, String staffCode ,String staffId) {
        Page pageResult = userpostService.findUserPostNowPage(page, orgId, staffName, staffCode,staffId);
        return R.ok(pageResult);
    }

    /**
     * 导出现任职情况Excel
     * @param  response
     * @return R
     */
    @ApiOperation(value = "导出现任职情况Excel", notes = "导出现任职情况Excel")
    @SysLog("导出现任职情况Excel" )
    @PostMapping("/exportStaffNowJob")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orgId",value="组织ID"),
            @ApiImplicitParam(name="staffName",value="员工姓名"),
            @ApiImplicitParam(name="staffCode",value="员工工号"),
            @ApiImplicitParam(name="staffId",value="员工id")
    })
    public R exportStaffNowJob(HttpServletResponse response, String orgId, String staffName, String staffCode ,String staffId) {
        try {
            List<UserpostDTO> list = userpostService.findUserPostNow(orgId, staffName, staffCode,staffId);
            ExcelUtils.export2Web(response, "现任职情况", "现任职情况表", UserpostDTO.class,list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }

}
