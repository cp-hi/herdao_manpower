package net.herdao.hdp.manpower.mpclient.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentDTO;
import net.herdao.hdp.manpower.mpclient.dto.recruitment.RecruitmentFormVO;
import net.herdao.hdp.manpower.mpclient.entity.Recruitment;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.RecruitmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 人才表
 *
 * @author Andy
 * @date 2020-11-23 14:46:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recruitment" )
@Api(value = "recruitment", tags = "人才表管理")
public class RecruitmentController extends HdpBaseController {

    private final  RecruitmentService recruitmentService;

    @Override
    public HdpService getHdpService() {
        return recruitmentService;
    }

    /**
     * 快速编辑
     * @param id 主键id
     * @return R
     */
    @ApiOperation(value = "快速编辑", notes = "快速编辑")
    @GetMapping("/{id}" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="主键id")
    })
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitment_view')" )
    public R<Recruitment> getById(@PathVariable("id" ) Long id) {
        Recruitment recruitment = recruitmentService.getById(id);
        return R.ok(recruitment);
    }

    /**
     * 修改更新人才表
     * @param recruitmentFormVO 人才表
     * @return R
     */
    @ApiOperation(value = "快速编辑-保存", notes = "快速编辑-保存")
    @PostMapping("/update")
    //@PreAuthorize("@pms.hasPermission('mpclient_recruitment_add')" )
    public R<RecruitmentFormVO> update(@RequestBody RecruitmentFormVO recruitmentFormVO) {
        recruitmentFormVO = recruitmentService.updateRecruitment(recruitmentFormVO);
        return R.ok(recruitmentFormVO);
    }

    /**
     * 人才库列表
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "人才库列表", notes = "人才库列表")
    @GetMapping("/findRecruitmentPage")
    @ApiImplicitParams({
         @ApiImplicitParam(name="page",value="分页对象",required = true),
         @ApiImplicitParam(name="orgId",value="组织ID"),
         @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R<Page<RecruitmentDTO>> findRecruitmentPage(Page page, String orgId, String searchText) {
        Page recruitmentPage = recruitmentService.findRecruitmentPage(page, orgId, searchText);
        return R.ok(recruitmentPage);
    }


}
