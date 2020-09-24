package net.herdao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.mpclient.entity.Pipeline;
import net.herdao.hdp.mpclient.service.PipelineService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.sys.annotation.OperationEntity;
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

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list() {
        return R.ok(pipelineService.pipelineList());
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R page(Page<Pipeline> page, String searchTxt) {
        return R.ok(pipelineService.page(page, searchTxt));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    public R getById(@PathVariable Long id) {
        return R.ok(pipelineService.getById(id));
    }


    @PostMapping
    public R save(@RequestBody Pipeline pipeline) {
        pipelineService.saveOrUpdate(pipeline);
        return R.ok(pipeline);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除", notes = "通过id删除")
    @OperationEntity(operation = "删除", key = "id", clazz = Pipeline.class)
    public R removeById(@PathVariable Long id) {
        return R.ok(pipelineService.removeById(id));
    }
}
