package net.herdao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.mpclient.entity.Demo;
import net.herdao.hdp.mpclient.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * demo 表 controller
 *
 * @author liang
 * @date 2020-08-06 15:18:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client" )
@Api(value = "client", tags = "demo 表管理")
public class DemoController {

    private final DemoService demoService;

    @ApiOperation(value = "测试", notes = "试试")
    @GetMapping("/shishi" )
    public R getDemoPage(String shishi) {
        return R.ok("这是试试   ： " + shishi + "  " + new Date());
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param demo demo 表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('demo_demo_view')" )
    public R getDemoPage(Page page, Demo demo) {
        return R.ok(demoService.page(page, Wrappers.query(demo)));
    }


    /**
     * 通过id查询demo 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('demo_demo_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(demoService.getById(id));
    }

    /**
     * 新增demo 表
     * @param demo demo 表
     * @return R
     */
    @ApiOperation(value = "新增demo 表", notes = "新增demo 表")
    @SysLog("新增demo 表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('demo_demo_add')" )
    public R save(@RequestBody Demo demo) {
        return R.ok(demoService.save(demo));
    }

    /**
     * 修改demo 表
     * @param demo demo 表
     * @return R
     */
    @ApiOperation(value = "修改demo 表", notes = "修改demo 表")
    @SysLog("修改demo 表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('demo_demo_edit')" )
    public R updateById(@RequestBody Demo demo) {
        return R.ok(demoService.updateById(demo));
    }

    /**
     * 通过id删除demo 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除demo 表", notes = "通过id删除demo 表")
    @SysLog("通过id删除demo 表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('demo_demo_del')" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(demoService.removeById(id));
    }

}
