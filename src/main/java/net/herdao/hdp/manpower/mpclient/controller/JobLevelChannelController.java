package net.herdao.hdp.manpower.mpclient.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.entity.JobLevelChannel;
import net.herdao.hdp.manpower.mpclient.entity.User;
import net.herdao.hdp.manpower.mpclient.listener.UserExcelListener;
import net.herdao.hdp.manpower.mpclient.service.JobLevelChannelService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/client/jobLevelChannel")
@Api(tags = "职级通道管理")
public class JobLevelChannelController {

    private JobLevelChannelService jobLevelChannelService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public R page(Page page, JobLevelChannel jobLevelChannel) {
        QueryWrapper<JobLevelChannel> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotBlank(jobLevelChannel.getJobLevelChannelCode()), "JOB_LEVEL_CHANNEL_CODE", jobLevelChannel.getJobLevelChannelCode())
                .or()
                .like(StringUtils.isNotBlank(jobLevelChannel.getJobLevelChannelName()), "JOB_LEVEL_CHANNEL_NAME", jobLevelChannel.getJobLevelChannelName());
        return R.ok(jobLevelChannelService.page(page, queryWrapper));
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list() {
        return R.ok(jobLevelChannelService.jobLevelChannelList() );
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(jobLevelChannelService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody JobLevelChannel jobLevelChannel) throws Exception {
        jobLevelChannelService.saveOrUpdate(jobLevelChannel);
        return R.ok(jobLevelChannel);
    }

    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        return R.ok(jobLevelChannelService.removeById(id));
    }


    @ApiOperation("导入")
    @SysLog("导入")
    @PostMapping("/import")
    public R importData(@RequestParam(value = "file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
//            EasyExcel.read(inputStream, User.class, new UserExcelListener(userService)).sheet().doRead();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return R.ok(" easyexcel读取上传文件成功");
    }

}
