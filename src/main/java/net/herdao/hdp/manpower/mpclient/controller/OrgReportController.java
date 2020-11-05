package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.utils.EasyExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.entity.JobLevelReport;
import net.herdao.hdp.manpower.mpclient.entity.OrgReport;
import net.herdao.hdp.manpower.mpclient.service.OrgReportService;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrgReportDetailVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrgReportVO;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 组织报表
 *
 * @author andy
 * @date 2020-09-22 10:13:05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orgReport" )
@Api(value = "orgReport", tags = "组织报表")
public class OrgReportController {

    private final OrgReportService orgReportService;

    /**
     * 分页查询
     *
     * @param page      分页对象
     * @param orgReport 组织报表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('mpclient_orgreport_view')")
    public R getOrgReportPage(Page page, OrgReport orgReport) {
        return R.ok(orgReportService.page(page, Wrappers.query(orgReport)));
    }

    /**
     * 通过id查询组织报表
     *
     * @param orgName id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{orgName}")
    @PreAuthorize("@pms.hasPermission('mpclient_orgreport_view')")
    public R getById(@PathVariable("orgName") String orgName) {
        return R.ok(orgReportService.getById(orgName));
    }

    /**
     * 新增组织报表
     *
     * @param orgReport 组织报表
     * @return R
     */
    @ApiOperation(value = "新增组织报表", notes = "新增组织报表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('mpclient_orgreport_add')")
    public R save(@RequestBody OrgReport orgReport) {
        return R.ok(orgReportService.save(orgReport));
    }

    /**
     * 修改组织报表
     *
     * @param orgReport 组织报表
     * @return R
     */
    @ApiOperation(value = "修改组织报表", notes = "修改组织报表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('mpclient_orgreport_edit')")
    public R updateById(@RequestBody OrgReport orgReport) {
        return R.ok(orgReportService.updateById(orgReport));
    }

    /**
     * 通过id删除组织报表
     *
     * @param orgName id
     * @return R
     */
    @ApiOperation(value = "通过id删除组织报表", notes = "通过id删除组织报表")
    @DeleteMapping("/{orgName}")
    @PreAuthorize("@pms.hasPermission('mpclient_orgreport_del')")
    public R removeById(@PathVariable String orgName) {
        return R.ok(orgReportService.removeById(orgName));
    }

    /**
     * 组织架构预览
     *
     * @param condition 组织架构
     * @return R
     */
    @ApiOperation(value = "组织架构预览", notes = "组织架构预览s")
    @PostMapping("/findOrgReportView")
    public R findOrgReportView(@RequestBody OrgReport condition) {
        return R.ok(orgReportService.findOrgReportView(condition));
    }

    /**
     * 导出组织架构Excel
     *
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出组织架构Excel", notes = "导出组织架构Excel")
    @PostMapping("/exportOrg")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orgCode", value = "组织编码")
    })
    public R exportOrg(HttpServletResponse response, @RequestBody OrgReport condition) {
        try {
            List<OrgReportVO> list = orgReportService.exportOrg(condition);
            ExcelUtils.export2Web(response, "组织架构表", "组织架构表", OrgReportVO.class, list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }

    /**
     * 导出组织架构明细Excel
     * @param  response
     * @return R
     */
    @ApiOperation(value = "导出组织架构明细Excel", notes = "导出组织架构明细Excel")
    @PostMapping("/exportDetailsOrg")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgCode",value="组织编码")
    })
    public R exportDetailsOrg(HttpServletResponse response,@RequestBody OrgReport condition) {
        List<OrgReportDetailVO> list = orgReportService.exportDetailsOrg(condition);
        try {
            ExcelUtils.export2Web(response, "组织架构明细表", "组织架构明细表", OrgReportDetailVO.class, list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }

    /**
     * 导出组织架构预览
     * @param  response
     * @return R
     */
    @ApiOperation(value = "导出组织架构明细Excel", notes = "导出组织架构明细Excel")
    @PostMapping("/findOrgDetailsView")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgCode",value="组织编码")
    })
    public R findOrgDetailsView(HttpServletResponse response,@RequestBody OrgReport condition) {
        List<OrgReportDetailVO> list = orgReportService.findOrgDetailsView(condition);
        return R.ok(list);
    }

    /**
     * 组织职级统计导出Excel
     * @param  response
     * @return R
     */
    @ApiOperation(value = "组织职级统计导出Excel", notes = "组织职级统计导出Excel")
    @PostMapping("/exportOrgJobLevel")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orgCode",value="组织编码")
    })
    public R exportOrgJobLevel(HttpServletResponse response,@RequestBody JobLevelReport condition) {
        try {
            List<JobLevelReport> list = orgReportService.exportOrgJobLevel(condition);
            EasyExcelUtils.webWriteExcel(response, list, JobLevelReport.class, "组织职级统计表");
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("导出失败");
        }

        return R.ok("导出成功");
    }


    /**
     * 组织职级统计预览
     * @param condition 组织职级
     * @return R
     */
    @ApiOperation(value = "组织职级统计预览", notes = "组织职级统计预览")
    @PostMapping("/fetchOrgJobLevelView")
    public R fetchOrgJobLevelView(@RequestBody JobLevelReport condition) {
        List<JobLevelReport> list = orgReportService.fetchOrgJobLevel(condition);
        return R.ok(list);
    }

}
