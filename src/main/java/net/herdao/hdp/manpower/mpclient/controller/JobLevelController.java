package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.JobLevelDTO;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.listener.ImportExcelListener;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

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
@RequestMapping("/client/jobLevel")
@Api(tags = "职级管理")
public class JobLevelController {

    private JobLevelService jobLevelService;

    private GroupService groupService;

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
    public R list(Long groupId) {
        return R.ok(jobLevelService.jobLevelList(groupId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(jobLevelService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody JobLevel jobLevel) {
        jobLevelService.saveEntity(jobLevel);
        return R.ok(jobLevel);
    }

    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(jobLevelService.removeById(id));
    }

    @ApiOperation("导入")
    @SysLog("导入")
    @PostMapping("/import")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "要导入的文件"),
            @ApiImplicitParam(name = "type", value = "操作类型，0:批量新增 1:批量修改"),
    })
    public R importData(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file, Integer importType) throws Exception {
        ImportExcelListener listener = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            listener = new ImportExcelListener(jobLevelService, importType);
            EasyExcel.read(inputStream, JobLevelDTO.class, listener).sheet().doRead();
            return R.ok(" easyexcel读取上传文件成功");
        } catch (Exception ex) {
            ExcelUtils.export2Web(response, "职级导入错误信息", "职级导入错误信息",JobLevelDTO.class, listener.getDataList());
            return R.failed(ex.getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
