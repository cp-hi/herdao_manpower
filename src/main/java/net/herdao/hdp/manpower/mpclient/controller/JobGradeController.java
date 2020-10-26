package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.*;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName JobGradeController
 * @Description 职等不需要做批量导入，所以只传了个Class
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 19:06
 * @Version 1.0
 */


@RestController
@AllArgsConstructor
@RequestMapping("/client/jobGrade")
@Api(tags = "职等管理")
public class JobGradeController extends NewBaseController<JobGrade, JobGradeListDTO, JobGradeFormDTO,Class> {

    private JobGradeService jobGradeService;

    @Autowired
    public void setEntityService(JobGradeService jobGradeService) {
        super.entityService = jobGradeService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "职等列表", notes = "用于下拉列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "集团id"),
    })
    public R<List<JobGradeShortDTO>> list(Long groupId) {
        return R.ok(jobGradeService.jobGradeList(  groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobGradeName", value = "字符串搜索"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<JobGradeListDTO>> page(HttpServletResponse response, Page page, JobGrade jobGrade, Integer type)
            throws Exception {
        return super.page(response,page,jobGrade,type);
    }




}
