package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineBatchUpdateVO;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineFormVO;
import net.herdao.hdp.manpower.mpclient.vo.pipeline.PipelineListVO;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName PipelineController
 * @Description PipelineController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/12 18:20
 * @Version 1.0
 */
@RestController
@Api(tags = "管线管理")
@RequestMapping("/client/pipeline")
public class PipelineController extends BaseController<Pipeline, PipelineListVO, PipelineFormVO>
        implements ExcelImportController<PipelineBatchAddVO, PipelineBatchUpdateVO> {

    @Autowired
    private PipelineService pipelineService;

    @Override
    public EntityService getEntityService() {
        return pipelineService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(pipelineService.pipelineList(groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pipelineName", value = "字符串搜索"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载，下载时把上一个返回的total当成size传递"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<PipelineListVO>> page(HttpServletResponse response, @ApiIgnore Page page, Pipeline pipeline, Integer type) throws Exception {
        return super.page(response, page, pipeline, type);
    }
}
