package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.vo.section.SectionBatchAddVO;
import net.herdao.hdp.manpower.mpclient.vo.section.SectionBatchUpdateVO;
import net.herdao.hdp.manpower.mpclient.vo.section.SectionFormVO;
import net.herdao.hdp.manpower.mpclient.vo.section.SectionListVO;
import net.herdao.hdp.manpower.mpclient.entity.Section;
import net.herdao.hdp.manpower.mpclient.service.SectionService;
import io.swagger.annotations.ApiOperation;
import net.herdao.hdp.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

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
public class SectionController extends BaseController<Section, SectionListVO, SectionFormVO>
        implements ExcelImportController<SectionBatchAddVO, SectionBatchUpdateVO> {

    @Autowired
    private SectionService sectionService;

    @Override
    public EntityService getEntityService() {
        return sectionService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "简要信息列表", notes = "用于下拉列表")
    public R list(Long groupId) {
        return R.ok(sectionService.sectionList(groupId));
    }

    @Override
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sectionName", value = "字符串搜索"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页条数"),
            @ApiImplicitParam(name = "type", value = "查询选项 ，不填为查询，1为下载，下载时把上一个返回的total当成size传递"),
    })
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<SectionListVO>> page(HttpServletResponse response, @ApiIgnore Page page, Section section, Integer type) throws Exception {
        return super.page(response, page, section, type);
    }
}
