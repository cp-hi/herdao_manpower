

package net.herdao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.mpclient.entity.OrgSituation;
import net.herdao.hdp.mpclient.service.OrgSituationService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 组织概况报表
 * @author andy
 * @date 2020-09-21 15:10:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orgSituation" )
@Api(value = "orgSituation", tags = "组织概况报表")
public class OrgSituationController {

    private final  OrgSituationService orgSituationService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param orgSituation 
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_orgsituation_view')" )
    public R getOrgSituationPage(Page page, OrgSituation orgSituation) {
        return R.ok(orgSituationService.page(page, Wrappers.query(orgSituation)));
    }


    /**
     * 通过id查询
     * @param fullTimeJobCount id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{fullTimeJobCount}" )
    @PreAuthorize("@pms.hasPermission('mpclient_orgsituation_view')" )
    public R getById(@PathVariable("fullTimeJobCount" ) Integer fullTimeJobCount) {
        return R.ok(orgSituationService.getById(fullTimeJobCount));
    }

    /**
     * 新增
     * @param orgSituation 
     * @return R
     */
    @ApiOperation(value = "新增", notes = "新增")
    @SysLog("新增" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('mpclient_orgsituation_add')" )
    public R save(@RequestBody OrgSituation orgSituation) {
        return R.ok(orgSituationService.save(orgSituation));
    }

    /**
     * 修改
     * @param orgSituation 
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @SysLog("修改" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('mpclient_orgsituation_edit')" )
    public R updateById(@RequestBody OrgSituation orgSituation) {
        return R.ok(orgSituationService.updateById(orgSituation));
    }

    /**
     * 通过id删除
     * @param fullTimeJobCount id
     * @return R
     */
    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping("/{fullTimeJobCount}" )
    @PreAuthorize("@pms.hasPermission('mpclient_orgsituation_del')" )
    public R removeById(@PathVariable Integer fullTimeJobCount) {
        return R.ok(orgSituationService.removeById(fullTimeJobCount));
    }

    /**
     * 获取组织概况
     * @param orgSituation
     * @return R
     */
    @ApiOperation(value = "获取组织概况", notes = "获取组织概况")
    @SysLog("获取组织概况" )
    @PostMapping("/fetchOrgSituation")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orgCode", value = "组织代码"),
    })
    public R fetchOrgSituation(@RequestBody OrgSituation orgSituation) {
        OrgSituation result = orgSituationService.fetchOrgSituation(orgSituation);
        return R.ok(result);
    }

    /**
     * 获取员工任职类型分布
     * @param orgSituation
     * @return R
     */
    @ApiOperation(value = "获取员工任职类型分布", notes = "获取员工任职类型分布")
    @SysLog("获取员工任职类型分布" )
    @PostMapping("/fetchOrgSituationByJobType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgCode", value = "组织代码"),
    })
    public R fetchOrgSituationByJobType(@RequestBody OrgSituation orgSituation) {
        OrgSituation result = orgSituationService.fetchOrgSituationByJobType(orgSituation);
        return R.ok(result);
    }
}
