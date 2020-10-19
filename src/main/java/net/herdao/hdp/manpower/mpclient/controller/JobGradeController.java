package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.JobGradeDTO;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.JobLevelDTO;
import net.herdao.hdp.manpower.mpclient.dto.jobLevel.vo.*;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.entity.JobLevel;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import net.herdao.hdp.manpower.mpclient.service.JobLevelService;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class JobGradeController extends BaseController<JobGrade> {

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

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public R page(Page page, JobGrade jobGrade) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        IPage p = jobGradeService.page(page, jobGrade);
        List<JobGradeDTO> records = p.getRecords();
        List<JobGradeListDTO> vos = DtoConverter.dto2vo(p.getRecords(), JobGradeListDTO.class);
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
        IPage p = jobGradeService.page(new Page(), new JobGrade(id));
        JobGradeFormDTO data = null;
        if (p.getRecords().size() > 0)
            data = DtoConverter.dto2vo(p.getRecords().get(0), JobGradeFormDTO.class);
        return R.ok(data);
    }


}
