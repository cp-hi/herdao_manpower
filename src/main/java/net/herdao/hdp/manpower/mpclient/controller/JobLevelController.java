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
public class JobLevelController extends BaseController<JobLevel> {

    @Autowired
    private JobLevelService jobLevelService;

    @Autowired
    public void setEntityService(JobLevelService jobLevelService) {
        super.entityService = jobLevelService;
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


    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobGradeId", value = "职等ID"),
            @ApiImplicitParam(name = "jobLevelName", value = "搜索字符串"),
    })
    public R page(Page page, JobLevel jobLevel) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        IPage p = jobLevelService.page(page, jobLevel);
        List<JobLevelListDTO> vos = DtoConverter.dto2vo(p.getRecords(), JobLevelListDTO.class);
        p.setRecords(vos);
        return R.ok(p);
    }

    @GetMapping("/formInfo/{id}")
    @ApiOperation(value = "表单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public R getFormInfo(@PathVariable Long id)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, NoSuchFieldException {
        IPage p = jobLevelService.page(new Page(), new JobLevel(id));
        JobLevelFormDTO data = null;
        if (p.getRecords().size() > 0)
            data = DtoConverter.dto2vo(p.getRecords().get(0), JobLevelFormDTO.class);
        return R.ok(data);
    }
}
