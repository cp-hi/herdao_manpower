package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.pipeline.vo.PipelineListDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.vo.SectionListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Pipeline;
import net.herdao.hdp.manpower.mpclient.service.PipelineService;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/client/pipeline")
public class PipelineController extends BaseController<Pipeline> {

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    public void setEntityService(PipelineService pipelineService) {
        super.entityService = pipelineService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(pipelineService.pipelineList(groupId));
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R page(Page page, @RequestBody Pipeline pipeline) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        IPage p = pipelineService.page(page, pipeline);
        List<PipelineListDTO> vos = DtoConverter.dto2vo(p.getRecords(), PipelineListDTO.class);
        p.setRecords(vos);
        return R.ok(p);
    }

}
