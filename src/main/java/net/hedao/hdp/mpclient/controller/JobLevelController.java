package net.hedao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.hedao.hdp.mpclient.entity.JobLevel;
import net.hedao.hdp.mpclient.entity.Post;
import net.hedao.hdp.mpclient.service.JobLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName JobLevelController
 * @Description JobLevelController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 19:06
 * @Version 1.0
 */


@RestController
@AllArgsConstructor
@RequestMapping("/client/joblevel")
@Api(tags = "职级管理")
public class JobLevelController {

    private JobLevelService jobLevelService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public R page(Page page, JobLevel jobLevel) {
        QueryWrapper<JobLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotBlank(jobLevel.getJobLevelCode()), "JOB_LEVEL_CODE", jobLevel.getJobLevelCode())
                .or()
                .like(StringUtils.isNotBlank(jobLevel.getJobLevelName()), "JOB_LEVEL_NAME", jobLevel.getJobLevelName());
        return R.ok(jobLevelService.page(page, queryWrapper));
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list() {
        return R.ok(jobLevelService.jobLevelList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(jobLevelService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody JobLevel jobLevel) throws Exception {
        jobLevelService.addOrUpdate(jobLevel);
        return R.ok(jobLevel);
    }

    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(jobLevelService.removeById(id));
    }
}
