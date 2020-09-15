
package net.hedao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.hedao.hdp.mpclient.entity.Userpost;
import net.hedao.hdp.mpclient.service.UserpostService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;

import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author liang
 * @date 2020-09-15 08:57:53
 */
@RestController
@AllArgsConstructor
@RequestMapping("/userpost" )
@Api(value = "userpost", tags = "管理")
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
     * 新增
     * @param userpost 
     * @return R
     */
    @ApiOperation(value = "新增", notes = "新增")
    @SysLog("新增" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_userpost_add')" )
    public R save(@RequestBody Userpost userpost) {
        return R.ok(userpostService.save(userpost));
    }

    /**
     * 修改
     * @param userpost 
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @SysLog("修改" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_userpost_edit')" )
    public R updateById(@RequestBody Userpost userpost) {
        return R.ok(userpostService.updateById(userpost));
    }

    /**
     * 通过id删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_userpost_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(userpostService.removeById(id));
    }

}
