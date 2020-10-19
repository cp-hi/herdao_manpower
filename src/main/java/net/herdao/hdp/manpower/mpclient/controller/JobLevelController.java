package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.JobLevelDTO;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.JobLevelFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.JobLevelListDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.PostDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobLevelImportVO;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
public class JobLevelController extends NewBaseController<JobLevel,JobLevelListDTO,JobLevelFormDTO> {

    @Autowired
    private JobLevelService jobLevelService;

    @Autowired
    public void setEntityService(JobLevelService jobLevelService) {
        super.newEntityService = jobLevelService;
    }

    @Override
    protected Class getImportClass() {
        return JobLevelImportVO.class;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(jobLevelService.jobLevelList(groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobGradeId", value = "职等ID"),
            @ApiImplicitParam(name = "jobLevelName", value = "搜索字符串"),
            @ApiImplicitParam(name = "type", value = "操作类型，0:或空查询 1:下载"),
    })
    public R page(HttpServletResponse response, Page page, JobLevel jobLevel, Integer type)
            throws Exception {
        return super.page(response,page,jobLevel,type);
    }
}
