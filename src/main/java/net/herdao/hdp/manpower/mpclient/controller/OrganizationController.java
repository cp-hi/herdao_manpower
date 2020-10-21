
package net.herdao.hdp.manpower.mpclient.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.listener.OrgExcelListener;
import net.herdao.hdp.manpower.mpclient.service.ExcelOperateRecordService;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import net.herdao.hdp.manpower.mpclient.service.PostService;
import net.herdao.hdp.manpower.mpclient.service.UserService;
import net.herdao.hdp.manpower.mpclient.utils.UUIDUtil;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationTreeVO;
import net.herdao.hdp.manpower.mpclient.vo.organization.OrganizationVO;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;


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

    private final OrganizationService orgService;

    private final SysDictItemService sysDictItemService;

    private final UserService userService;

    private final PostService postService;

    private final ExcelOperateRecordService excelOperateRecordService;

    private final OperationLogService operationLogService;


    /**
     * 通过id查询组织架构详情
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询组织架构详情", notes = "通过id查询组织架构详情")
    @GetMapping("/fetchOrg/{id}")
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="组织架构主键ID")
    })
    public R fetchOrg(@PathVariable("id") Long id) {
        Organization condition=new Organization();
        condition.setId(id);
        Organization result = orgService.findOrgDetails(condition);
        return R.ok(result);
    }
    
    /**
     * 新增组织
     * 
     * @author  shuling
	 * @date    2020-10-18 10:37:22
	 * @version 1.0
     */
	@ApiOperation(value = "组织信息", notes = "新增组织信息")
	@SysLog("新增组织信息")
	@PostMapping
	public R<Organization> save(@RequestBody OrganizationVO organizationVO) {
		return orgService.saveOrUpdateOrganization(organizationVO);
	}

	 /**
     * 修改组织
     * 
     * @author  shuling
	 * @date    2020-10-18 10:47:32
	 * @version 1.0
     */
	@ApiOperation(value = "组织信息", notes = "修改组织信息")
	@SysLog("修改组织信息")
	@PutMapping
	public R<Organization> updateById(@RequestBody OrganizationVO organizationVO) {
		return orgService.saveOrUpdateOrganization(organizationVO);
	}

    /**
     * 更新组织
     *
     */
    @ApiOperation(value = "组织信息", notes = "修改组织信息")
    @SysLog("修改组织信息")
    @PutMapping("/updateOrg")
    public R<Organization> updateOrg(@RequestBody OrganizationVO organizationVO) {
        return orgService.saveOrUpdateOrganization(organizationVO);
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
        return R.ok(orgService.removeById(id));
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
        List<Organization> list = orgService.selectOrganizationListByParentOid(parentId);
        return R.ok(list);
    }

    /**
     * 查询部门结构树
     * @return R
     */
    @ApiOperation(value = "查询部门结构树", notes = "查询部门结构树")
    @PostMapping("/fetchDeptTree")
    @OperationEntity(operation = "查询部门结构树" ,clazz = Organization.class )
    public R fetchDeptTree(@RequestBody Organization condition) {
        List<Organization> list = orgService.fetchDeptTree(condition);
        return R.ok(list);
    }

    /**
     * @description 查询组织树
     * 
     * @modify      shuling
     * @date        2020-10-19 19:29:33
     * @return      R
     */
    @ApiOperation(value = "查询组织树", notes = "查询组织树")
    @GetMapping("/findAllOrganizations")
    @OperationEntity(operation = "查询根组织架，默认展示两级架构", clazz = Organization.class)
    @ApiImplicitParams({
			        @ApiImplicitParam(name="orgCode",value="组织编码（查询子组织）"),
			        @ApiImplicitParam(name="searchText",value="模糊查询内容")
    })
	public R<List<OrganizationTreeVO>> findAllOrganizations(String orgCode, String searchText) {
		// 默认查询组织层级二级
		if (StrUtil.isBlank(orgCode) && StrUtil.isBlank(searchText)) {
			return R.ok(orgService.selectOrganizationTree());
		}
		return R.ok(orgService.organizationTreeList(orgCode, searchText));
	}

    /**
     * 高级查询根组织架构（废弃 2020/09/11)
     *
     * @return R
     */
    @ApiOperation(value = "高级查询根组织架构", notes = "高级查询根组织架构")
    @GetMapping("/findOrganizationByCondition")
    public R findOrganizationByCondition(Organization condition) {
        List<Organization> list = orgService.findOrganizationByCondition(condition);
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
            List<Organization> list = orgService.findOrganizationByKeyWords(keyword);
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
            list = orgService.selectOrganByCurrentOrgOid(id);

        }

        return R.ok(list);
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
        @ApiImplicitParam(name="isStop",value="是否停用  值：0 启用 、值：1 停用 、值：3 或者 NULL 查询全部 "),
        @ApiImplicitParam(name="isRoot",value="是否加载根组织架构： ture 是 , false 否"),
    })
    public R findOrganization2LevelByCondition(@RequestBody Organization condition) {
        return orgService.findOrganization2Level(condition);
    }

    /**
     * @description  删除组织
     * @modify      shuling
     * @date        2020-10-19 16:26:45
     * @param ids
     * @return
     */
	@ApiOperation(value = "删除组织", notes = "删除组织")
	@ApiImplicitParam(name = "ids", value = "组织id集合 例如： 1,2,3,4")
	@SysLog("删除组织")
	@PostMapping("/removeOrg")
	public R removeOrg(String ids) {
		return orgService.removeOrg(ids);
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
            @ApiImplicitParam(name="isStop",value="是否停用 ：值：0 启用 、值：1 停用 、值：3 或者 NULL 查询全部"),
            @ApiImplicitParam(name="orgTreeLevel",value="组织结构数层级(默认2级) （可选参数）"),
    })
    @OperationEntity(operation = "点击展开组织架构树（默认两级）" ,clazz = Organization.class )
    public R getRecursionOrgByLevel(@RequestBody Organization condition) {
        return orgService.getRecursionOrgByLevel(condition);
    }

    /**
     * 
     * @description 停用组织，当组织下无在职员工，方能停用组织，组织停用后，员工入职、转正、调职将不能使用该组织<br>
     *              组织停用后，才能操作删除组织，但组织变更记录不能删除
     *              组织停用后，将变更停用日期为当前时间
     *              组织启用后，将变更启用日期为当前时间
     * @modify      shuling
     * @date        2020-10-18 10:26:45
     * @param       id
     * @param       stopDateStr
     * @return
     */
    @ApiOperation(value = "停用组织", notes = "停用组织")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="组织id"),
        @ApiImplicitParam(name="stopDateStr",value="停用日期 例如： 2020-10-19")
    })
    @SysLog("停用组织")
    @PutMapping("/disable")
    public R disable(Long id, String stopDateStr) {
        return orgService.expectedDisable(id, stopDateStr);
    }
    
    
    /**
	 * @description 启用组织
     * @modify      shuling
     * @date        2020-10-18 10:26:45
     * @param       id
     * @param       startDateStr
     * @return
     */
    @ApiOperation(value = "启用组织", notes = "启用组织")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="组织id"),
        @ApiImplicitParam(name="startDateStr",value="启用日期 例如： 2020-10-19")
    })
    @SysLog("启用组织")
    @PutMapping("/enable")
    public R enable(Long id, String startDateStr) {
        return orgService.expectedEnable(id, startDateStr);
    }
    
    /**
     * 组织列表分页查询 
     * 
     * @modified shuling
     * @param page
     * @param stop 
     * @param searchText
     * @return
     */
	@ApiOperation(value = "组织列表分页查询", notes = "组织列表分页查询")
	@GetMapping("/findOrgPage")
	@OperationEntity(operation = "组织列表分页查询", clazz = Organization.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "orgCode", value = "组织编码（通过组织树查询传递）"),
						 @ApiImplicitParam(name = "stop", value = "是否停用 （值：0 启用 、值：1 停用 、值：3 或者 NULL 查询全部）"),
						 @ApiImplicitParam(name = "searchText", value = "模糊查询内容") })
	public R<Page<OrganizationVO>> findOrgPage(Page page, String orgCode, Integer stop, String searchText) {
		return R.ok(orgService.findOrgPage(page, orgCode, stop, searchText));
	}

    /**
     * 批量导入组织 (excel导入) TOTO 待完善
     * 
     * @modified shuling
     * @param file
     * @return R
     */
    @ApiOperation(value = "批量导入组织 (excel导入)", notes = "批量导入组织 (excel导入)")
    @PostMapping("/batchImportOrg")
    @ResponseBody
    @ApiImplicitParams({
	        @ApiImplicitParam(name = "file", value = "导入文件"),
	        @ApiImplicitParam(name = "importType", value = "导入类型，值： 0  批量新增； 值 1 批量修改"),
	})
    public R batchImportOrg(@RequestParam(value = "file") MultipartFile file, Integer importType){
    	try {
			InputStream inputStream = file.getInputStream();
			EasyExcel.read(inputStream, Organization.class,
								new OrgExcelListener(orgService,
													 sysDictItemService,
													 userService,
													 postService,
													 excelOperateRecordService)).sheet().doRead();
			return R.ok("导入成功！");
		} catch (IOException e) {
			return R.failed(e.getMessage());
		}
    }
    
    /**
     * 部门选择组件
     * 
     * @return
     */
    @ApiOperation(value = "部门选择组件", notes = "部门选择组件")
    @GetMapping("/selectOrganizationComponent")
    public R<?> selectOrganizationComponent() {
        return orgService.selectOrganizations();
    }


    @ApiOperation(value = "获取组织操作日志")
    @GetMapping("/getOrgLog/{objId}")
    public R getOrgLog(@PathVariable Long objId) {
        return R.ok(operationLogService.findByEntity(objId, Organization.class.getName()));
    }

    /**
     * 查询组织下所有人员
     * @author yangrr
     * @param page 分页对象
     * @param orgCode
     * @return
     */
    @ApiOperation(value = "查询组织下所有人员")
    @GetMapping("/getOrgStaffAll")
    public R<Page> selectOrgStaffAll(Page page, String orgCode) {
        page = page.setRecords(orgService.selectOrgStaffAll(page, orgCode));
        return R.ok(page);
    }

}



