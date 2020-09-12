package com.hedao.hdp.mpclient.oa.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hedao.hdp.mpclient.oa.entity.Pipeline;
import com.hedao.hdp.mpclient.oa.entity.Section;
import com.hedao.hdp.mpclient.oa.service.PipelineService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PipelineController
 * @Description PipelineController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 18:20
 * @Version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client/pipeline")
public class PipelineController {

    private final PipelineService pipelineService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R page(Page page, Pipeline pipeline) {
        return R.ok(pipelineService.page(page, Wrappers.query(pipeline)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    public R getById(@PathVariable("id") Integer id)   {
        return R.ok(pipelineService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody Pipeline pipeline) throws Exception {
        pipelineService.addOrUpdate(pipeline);
        return R.ok(pipeline);
    }

    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable String id) {
        return R.ok(pipelineService.removeById(id));
    }
}
