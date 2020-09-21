package net.herdao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.mpclient.entity.JobGrade;
import net.herdao.hdp.mpclient.service.JobGradeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName JobGradeController
 * @Description JobGradeController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 19:06
 * @Version 1.0
 */


@RestController
@AllArgsConstructor
@RequestMapping("/client/jobGrade")
@Api(tags = "职等管理")
public class JobGradeController {

    private JobGradeService jobGradeService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public R page(Page page, JobGrade jobGrade) {
        QueryWrapper<JobGrade> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotBlank(jobGrade.getJobGradeCode()), "JOB_GRADE_CODE", jobGrade.getJobGradeCode())
                .or()
                .like(StringUtils.isNotBlank(jobGrade.getJobGradeName()), "JOB_GRADE_NAME", jobGrade.getJobGradeName());
        return R.ok(jobGradeService.page(page, queryWrapper));
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list() {
        return R.ok(jobGradeService.jobGradeList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(jobGradeService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody JobGrade jobGrade) throws Exception {
        jobGradeService.saveOrUpdate(jobGrade);
        return R.ok(jobGrade);
    }

    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(jobGradeService.removeById(id));
    }
}
