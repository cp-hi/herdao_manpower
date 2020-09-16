
package net.herdao.hdp.mpclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.mpclient.entity.Organization;
import net.herdao.hdp.mpclient.entity.Post;
import net.herdao.hdp.mpclient.entity.User;
import net.herdao.hdp.mpclient.entity.Userpost;
import net.herdao.hdp.mpclient.service.OrganizationService;
import net.herdao.hdp.mpclient.service.PostService;
import net.herdao.hdp.mpclient.service.UserService;
import net.herdao.hdp.mpclient.service.UserpostService;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author Andy
 * @date 2020-09-09 15:31:20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/organization")
@Api(value = "organization", tags = "管理")
@Slf4j
public class OrganizationController {

    private final OrganizationService organizationService;

    private final PostService postService;

    private final UserService userService;


    /**
     * 通过id查询组织架构详情
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询组织架构详情", notes = "通过id查询组织架构详情")
    @GetMapping("/fetchOrg/{id}")
    @OperationEntity(operation = "通过id查询组织架构详情" ,clazz = Organization.class )
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="组织架构主键ID")
    })
    public R fetchOrg(@PathVariable("id") String id) {
        Organization organization = organizationService.getById(id);

        if (null != organization){
            QueryWrapper<Organization> wrapper = Wrappers.query();
            wrapper.eq("id",organization.getParentId());
            //获取父组织
            Organization parentOrg = organizationService.getOne(wrapper);
            if (null != parentOrg){
                organization.setParentName(parentOrg.getOrgName());
            }
        }

        return R.ok(organization);
    }

    /**
     * 新增组织架构
     *
     * @param organization
     * @return R
     */
    @ApiOperation(value = "新增组织架构", notes = "新增组织架构")
    @SysLog("新增组织架构")
    @PostMapping("/saveOrg")
    @Transactional
    @OperationEntity(operation = "新增组织架构" ,clazz = Organization.class )
    //@PreAuthorize("@pms.hasPermission('oa_organization_add')" )
    public R saveOrg(@RequestBody Organization organization) {
        if (null != organization) {
            //查询岗位负责人姓名 mp_post表
            Post post = postService.getById(organization.getChargeOrg());
            if (null != post) {
                organization.setPostName(post.getPostName());
            }

            //查询组织负责人
            User user = userService.getById(organization.getOrgChargeWorkNo());
            if (null != user) {
                organization.setUserName(user.getUserName());
            }

            QueryWrapper<Organization> wrapper = Wrappers.query();
            wrapper.eq("id",organization.getParentId());
            //获取父组织
            Organization parentOrg = organizationService.getOne(wrapper);
            if (null != parentOrg){
                if (null != parentOrg.getOrgTreeLevel()){
                    //当前新增组织的组织树层级数+1
                    organization.setOrgTreeLevel(parentOrg.getOrgTreeLevel()+1);
                }
            }
        }
        organizationService.save(organization);

        return R.ok(organization);
    }

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
     * 通过id删除
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除")
    //@DeleteMapping("/{orgOid}" )
    @PreAuthorize("@pms.hasPermission('oa_organization_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(organizationService.removeById(id));
    }


    /**
     * 查询子组织架构表
     *
     * @param parentId 组织架构表父id
     * @return R
     */
    @ApiOperation(value = "查询子组织架构表", notes = "查询子组织架构表")
    @GetMapping("/selectOrganizationListByParentOid")
    public R selectOrganizationListByParentOid(String parentId) {
        List<Organization> list = organizationService.selectOrganizationListByParentOid(parentId);
        return R.ok(list);
    }

    /**
     * 查询根组织架构树
     *
     * @return R
     */
    @ApiOperation(value = "查询根组织架构树", notes = "查询根组织架构树")
    @PostMapping("/findAllOrganizations")
    @OperationEntity(operation = "查询根组织架构树，点击切换启用状态 。（默认展示两级架构，根组织及其下一层子组织。)" ,clazz = Organization.class )
    public R findAllOrganizations(@RequestBody Organization condition) {
        List<Organization> list = organizationService.findAllOrganizations(condition);
        return R.ok(list);
    }

    /**
     * 高级查询根组织架构（废弃 2020/09/11)
     *
     * @return R
     */
    @ApiOperation(value = "高级查询根组织架构", notes = "高级查询根组织架构")
    @GetMapping("/findOrganizationByCondition")
    public R findOrganizationByCondition(Organization condition) {
        List<Organization> list = organizationService.findOrganizationByCondition(condition);
        return R.ok(list);
    }

    /**
     * 关键字查询根组织架构
     *
     * @return R
     */
    @ApiOperation(value = "关键字查询根组织架构", notes = "关键字查询根组织架构")
    @GetMapping("/findOrganizationByKeyWords")
    public R findOrganizationByKeyWords(String keyword) {
        if (!keyword.isEmpty()) {
            List<Organization> list = organizationService.findOrganizationByKeyWords(keyword);
            return R.ok(list);
        }

        return R.ok(null);
    }


    /**
     * 查询或复制根组织架构详情(把当前组织架构树形结构带出来,当前组织的所有父组织和子组织)
     *
     * @return R
     * @orgOid 组织ID
     */
    @ApiOperation(value = "查询或复制根组织架构详情(把当前组织架构树形结构带出来,当前组织的所有父组织和子组织)", notes = "查询或复制根组织架构详情(把当前组织架构树形结构带出来,当前组织的所有父组织和子组织)")
    @GetMapping("/findOrganizationByOrgOid")
    public R findOrganizationByOrgOid(String id) {
        List<Organization> list = null;
        if (null != id) {
            list = organizationService.selectOrganByCurrentOrgOid(id);

        }

        return R.ok(list);
    }


    /**
     * 组织启用/停用
     *
     * @param organization
     * @return R
     */
    @ApiOperation(value = "组织启用/停用", notes = "组织启用/停用")
    @SysLog("组织启用/停用")
    @GetMapping("/stopOrStartOrgan")
    //@PreAuthorize("@pms.hasPermission('oa_organization_edit')" )
    public R stopOrStartOrgan(@RequestBody Organization organization) {
        return R.ok(organizationService.updateById(organization));
    }


    /**
     * 根据当前登录租户的租户ID 查询该组织架构和二级组织架构 存在多个根组织架构的情况
     * 默认加载展示2级组织架构
     *
     * @return R
     */
    @ApiOperation(value = "展示组织管理，点击切换启用状态 。（默认展示两级架构，根组织及其下一层子组织。)", notes = "展示组织管理，点击切换启用状态 。 （默认展示两级架构，根组织及其下一层子组织。)")
    @PostMapping("/findOrganization2LevelByCondition")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="组织架构主键ID"),
        @ApiImplicitParam(name="tenantId",value="租户ID"),
        @ApiImplicitParam(name="isStop",value="是否停用 ： 0 停用，1启用（默认），3全部"),
        @ApiImplicitParam(name="isRoot",value="是否加载根组织架构： ture 是 , false 否"),
    })
    public R findOrganization2LevelByCondition(@RequestBody Organization condition) {
        return organizationService.findOrganization2Level(condition);
    }


    /**
     *
     * 删除组织
     * @param condition
     * @return R
     */
    @ApiOperation(value = "删除组织", notes = "删除组织")
    @SysLog("删除组织")
    @PostMapping("/removeOrg")
    //@PreAuthorize("@pms.hasPermission('oa_organization_del')" )
    @Transactional
    public R removeOrg(@RequestBody Organization condition) {
        return removeOrg(condition);
    }

    /**
     * 点击展开组织架构树（默认两级） 分页查询
     * @param condition
     * @return R
     */
    //@ApiOperation(value = "点击展开组织架构树（默认两级）", notes = "点击展开组织架构树（默认两级）")
    @SysLog("点击展开组织架构树（默认两级）")
    @PostMapping("/getRecursionOrgByLevel")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="组织架构主键ID"),
            @ApiImplicitParam(name="tenantId",value="租户ID"),
            @ApiImplicitParam(name="isStop",value="是否停用 ： 0 停用，1启用（默认），3全部"),
            @ApiImplicitParam(name="orgTreeLevel",value="组织结构数层级(默认2级) （可选参数）"),
    })
    @OperationEntity(operation = "点击展开组织架构树（默认两级）" ,clazz = Organization.class )
    public R getRecursionOrgByLevel(@RequestBody Organization condition) {
        return organizationService.getRecursionOrgByLevel(condition);
    }

    /**
     * 组织启用/停用
     * @param condition
     * @return R
     */
    @ApiOperation(value = "组织启用/停用", notes = "组织启用/停用")
    @SysLog("组织启用/停用")
    @PostMapping("/startOrStopOrg")
    //@PreAuthorize("@pms.hasPermission('oa_organization_del')" )
    @Transactional
    public R startOrStopOrg(@RequestBody Organization condition) {
        /*组织启用/停用
        当组织下无在职员工，方能停用组织，组织停用后，员工入职、转正、调职将不能使用该组织
        组织停用后，才能操作删除组织，但组织变更记录不能删除
        组织停用后，将变更停用日期为当前时间
        组织启用后，将变更启用日期为当前时间*/
        organizationService.startOrStopOrg(condition);
        return R.ok("组织启用/停用成功");
    }


    /**
     * 分页查询组织架构
     * @param page 分页对象
     * @param organization
     * @return
     */
    @ApiOperation(value = "分页查询组织架构", notes = "分页查询组织架构")
    @PostMapping("/findOrgPage")
    @OperationEntity(operation = "分页查询组织架构" ,clazz = Organization.class )
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="组织架构主键ID"),
            @ApiImplicitParam(name="orgName",value="组织名称"),
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findOrgPage(Page page, @RequestBody Organization organization) {
        Page pageResult = organizationService.findOrgPage(page, organization);
        return R.ok(pageResult);
    }


}



