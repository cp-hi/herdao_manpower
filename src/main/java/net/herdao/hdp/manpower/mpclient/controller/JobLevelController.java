package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.JobLevelFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.JobLevelListDTO;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.JobLevelBatchDTO;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName JobLevelController
 * @Description JobLevelController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 19:06
 * @Version 1.0
 */


@RestController
@RequestMapping("/client/jobLevel")
@Api(tags = "职级管理")
public class JobLevelController extends NewBaseController<JobLevel,JobLevelListDTO,JobLevelFormDTO, JobLevelBatchDTO> {

    @Autowired
    private JobLevelService jobLevelService;

    @Autowired
    public void setEntityService(JobLevelService jobLevelService) {
        super.entityService = jobLevelService;
    }

    @Override
    protected Class getImportClass() {
        return JobLevelBatchDTO.class;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(jobLevelService.jobLevelList(groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "jobGradeId", value = "职等ID"),
            @ApiImplicitParam(name = "jobLevelName", value = "搜索字符串"),
            @ApiImplicitParam(name = "type", value = "操作类型，0:或空查询 1:下载"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R page(HttpServletResponse response, Page page, JobLevel jobLevel, Integer type)
            throws Exception {
        return super.page(response,page,jobLevel,type);
    }
}
