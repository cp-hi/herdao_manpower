package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostListDTO;
import net.herdao.hdp.manpower.mpclient.dto.post.vo.PostSeqFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.SectionDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.vo.SectionFormDTO;
import net.herdao.hdp.manpower.mpclient.dto.section.vo.SectionListDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostSeq;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import net.herdao.hdp.manpower.mpclient.service.SectionService;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName SectionController
 * @Description SectionController
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 19:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/client/section")
@Api(tags = "板块管理")
public class SectionController extends NewBaseController<Section, SectionListDTO, SectionFormDTO,Class> {

    @Autowired
    private SectionService sectionService;

    @Autowired
    public void setEntityService(SectionService sectionService) {
        super.entityService = sectionService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(sectionService.sectionList(groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R page(HttpServletResponse response,Page page, Section section, Integer type) throws Exception {
        return super.page(response, page, section, type);
    }
}
