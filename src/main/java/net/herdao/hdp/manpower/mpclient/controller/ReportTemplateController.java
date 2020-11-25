package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.ReportTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reportTemplate")
@Api(value = "reportTemplate", tags = "报表模板")
public class ReportTemplateController {
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R<IPage<ReportTemplate>> getReportTemplatePage(Page page, ReportTemplate reportTemplate, String searchText) {
        return null;
    }

    /**
     * 通过id查询报表模板
     *
     * @return R
     */
    @ApiOperation(value = "通过id查询报表模板", notes = "通过id查询报表模板")
    @GetMapping("/{id}")
    public R<ReportTemplate> getById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增报表模板
     *
     * @return R
     */
    @ApiOperation(value = "新增报表模板", notes = "新增报表模板")
    @PostMapping
    public R<Boolean> save(@RequestBody ReportTemplate reportTemplate) {
        return null;
    }

    /**
     * 修改报表模板
     *
     * @return R
     */
    @ApiOperation(value = "修改报表模板", notes = "修改报表模板")
    @PutMapping
    public R<Boolean> updateById(@RequestBody ReportTemplate reportTemplate) {
        return null;
    }

    /**
     * 通过id删除报表模板
     *
     * @return R
     */
    @ApiOperation(value = "通过id删除报表模板", notes = "通过id删除报表模板")
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return null;
    }
}
