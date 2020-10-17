
package net.herdao.hdp.manpower.mpclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import org.springframework.web.bind.annotation.*;

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
     * 修改
     *
     * @param organization
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @SysLog("修改")
    @PutMapping
    //@PreAuthorize("@pms.hasPermission('oa_organization_edit')" )
    public R updateById(@RequestBody Organization organization) {
        return R.ok(organizationService.updateById(organization));
    }

    /**
     * 根据id获取根节点
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "根据id获取根节点", notes = "根据id获取根节点")
    @GetMapping("/root/{id}")
    //@PreAuthorize("@pms.hasPermission('oa_organization_edit')" )
    public R selectOrgChartRoot(@PathVariable("id" ) Long id) {
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
    public R selectOrgChartChild(@PathVariable("id" ) Long id) {
        return R.ok(organizationService.selectOrgChartChild(id));
    }

}



