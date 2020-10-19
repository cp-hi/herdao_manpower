
package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartDTO;
import net.herdao.hdp.manpower.mpclient.dto.OrgChartFormDTO;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangrr
 * @date 2020-09-09 15:31:20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orgchart")
@Api(value = "orgchart", tags = "组织架构图")
@Slf4j
public class OrgChartController {

    private final OrganizationService organizationService;

    /**
     * 根据id获取根节点
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "根据id获取根节点", notes = "根据id获取根节点")
    @GetMapping("/root/{id}")
    //@PreAuthorize("@pms.hasPermission('oa_organization_edit')" )
    public R<OrgChartDTO> selectOrgChartRoot(@PathVariable("id" ) Long id) {
        return R.ok(organizationService.selectOrgChartRoot(id));
    }

    /**
     * 根据id获取子节点
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "根据id获取子节点", notes = "根据id获取子节点")
    @GetMapping("/child/{id}")
    //@PreAuthorize("@pms.hasPermission('oa_organization_edit')" )
    public R<List<OrgChartDTO>> selectOrgChartChild(@PathVariable("id" ) Long id) {
        return R.ok(organizationService.selectOrgChartChild(id));
    }

    /**
     * 新增组织架构图
     *
     * @param form
     * @return R
     */
    @ApiOperation(value = "快速新增", notes = "新增组织架构图")
    @SysLog("新增组织架构图" )
    @PostMapping("/save")
    //@PreAuthorize("@pms.hasPermission('oa_organization_edit')" )
    public R<Boolean> saveOrgChart(@RequestBody OrgChartFormDTO form) {
        return R.ok(organizationService.saveOrgChart(form));
    }

    /**
     * 修改组织架构图
     *
     * @param form
     * @return R
     */
    @ApiOperation(value = "快速编辑", notes = "修改组织架构图")
    @SysLog("修改组织架构图" )
    @PutMapping("/edit")
    //@PreAuthorize("@pms.hasPermission('oa_organization_edit')" )
    public R<Boolean> editOrgChart(@RequestBody OrgChartFormDTO form) {
        return R.ok(organizationService.editOrgChart(form));
    }

    /**
     * 根据id获取编辑数据
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "根据id获取编辑数据", notes = "根据id获取编辑数据")
    @GetMapping("/editQuery/{id}")
    //@PreAuthorize("@pms.hasPermission('oa_organization_edit')" )
    public R<OrgChartFormDTO> getById(@PathVariable("id" ) Long id) {
        Organization entity = organizationService.getById(id);
        OrgChartFormDTO form = new OrgChartFormDTO();
        BeanUtils.copyProperties(entity, form);
        return R.ok(form);
    }

}



