package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.JobGrade;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.service.JobGradeService;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobGradeFormVO;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobGradeListVO;
import net.herdao.hdp.manpower.mpclient.vo.jobLevel.JobGradeShortVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@RequestMapping("/client/jobGrade")
@Api(tags = "职等管理")
public class JobGradeController extends BaseController<JobGrade, JobGradeListVO, JobGradeFormVO> {

    @Autowired
    JobGradeService jobGradeService;

    @Override
    protected EntityService getEntityService() {
        return jobGradeService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "职等列表", notes = "用于下拉列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "集团id"),
            @ApiImplicitParam(name = "needBlank", value = "需要添加一个全部，1为需要，不填或0则不需要"),
    })
    public R<List<JobGradeShortVO>> list(Long groupId, Integer needBlank) {
        List<JobGradeShortVO> list = jobGradeService.jobGradeList(groupId);
        if (Integer.valueOf(1).equals(needBlank))
            list.add(0, new JobGradeShortVO("全部", groupId));
        return R.ok(list);
    }

    @Override
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobGradeName", value = "字符串搜索"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载，下载时把上一个返回的total当成size传递"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<JobGradeListVO>> page(HttpServletResponse response, @ApiIgnore Page page, JobGrade jobGrade, Integer type)
            throws Exception {
        return super.page(response, page, jobGrade, type);
    }
    
    /**
     * 通过id删除职级
     *
     * @param id id
     * @return R
     */
    @Override
    @ApiOperation(value = "通过id删除职级", notes = "通过id删除职级")
    @SysLog("通过id删除职级")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.ok(jobGradeService.removeById(id));

    }
}
