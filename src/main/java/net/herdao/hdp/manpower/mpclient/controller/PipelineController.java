package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import net.herdao.hdp.manpower.mpclient.dto.pipeline.vo.PipelineFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.pipeline.vo.PipelineListDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.vo.SectionListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
public class PipelineController extends NewBaseController<Pipeline, PipelineListDTO, PipelineFormDTO,Class> {

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    public void setEntityService(PipelineService pipelineService) {
        super.newEntityService = pipelineService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(pipelineService.pipelineList(groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiOperation(value = "分页查询", notes = "" +
            /**
             *             *pipelineName       字符串搜索
             *             *current         当前页
             *             *size           每页条数
             *             typt 0 或空则查询  1 为下载
             **/
            "")
    public R page(HttpServletResponse response, Page page, Pipeline pipeline, Integer type) throws Exception {
        return super.page(response, page, pipeline, type);
    }
}
