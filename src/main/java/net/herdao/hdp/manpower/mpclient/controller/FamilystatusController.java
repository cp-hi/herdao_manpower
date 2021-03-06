package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.constant.ExcelDescriptionContants;
import net.herdao.hdp.manpower.mpclient.dto.staffFamily.*;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.service.FamilystatusService;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.FamilyStatusVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 员工家庭成员
 * @author andy
 * @date 2020-09-23 10:53:08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/familystatus" )
@Api(value = "familystatus", tags = "员工家庭成员管理")
@Slf4j
public class FamilystatusController extends HdpBaseController  {

    private final FamilystatusService familystatusService;

    private final RemoteUserService remoteUserService;

    @Override
    public HdpService getHdpService() {
        return familystatusService;
    }

    @Override
    public void initEasyExcelArgs(Class importAddCls, Class importAddErrCls, Class importUpdateCls, Class importUpdateErrCls, Integer excelIndex,
                                  Integer headRowNumber, List downloadUpdateTemplateList, String templateName, String excelDescription) {
        this.importAddCls = StaffFamilyAddDTO.class;
        this.importAddErrCls = StaffFamilyAddErrDTO.class;
        this.importUpdateCls = StaffFamilyUpdateDTO.class;
        this.importUpdateErrCls = StaffFamilyUpdateErrDTO.class;
        this.excelName = "员工家庭情况";
    }

    @Override
    public List getDownloadUpdateTemplateList(Map<String, Object> searchParams) {
        List<FamilyStatusVO> list = this.familystatusService.findFamilyStatus(null,null);
        return list;
    }

    @Override
    public String getExcelAddDescription() {
        return ExcelDescriptionContants.getFamilyAddDesc();
    }

    @Override
    public String getExcelUpdateDescription() {
        return ExcelDescriptionContants.getFamilyUpdateDesc();
    }


    /**
     * 分页查询
     * @param page 分页对象
     * @param familystatus 员工家庭成员
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_familystatus_view')" )
    public R page(Page page, Familystatus familystatus) {
        return R.ok(familystatusService.page(page, Wrappers.query(familystatus)));
    }

    /**
     * 员工家庭情况分页
     * @param page 分页对象
     * @param searchText 关键字搜索
     * @return
     */
    @ApiOperation(value = "员工家庭情况分页", notes = "员工家庭情况分页")
    @GetMapping("/findFamilyStatusPage")
    @ApiImplicitParams({
         @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findFamilyStatusPage(Page page, FamilyStatusListDTO familyStatusListDTO, String searchText) {
        return R.ok(familystatusService.findFamilyStatusPage(page,familyStatusListDTO, searchText));
    }

    /**
     * 导出家庭情况Excel
     * @param response
     * @param searchText 关键字搜索
     * @return R
     */
    @ApiOperation(value = "导出家庭情况Excel", notes = "导出家庭情况Excel")
    @GetMapping("/exportFamily")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    @SneakyThrows
    public void exportFamily(HttpServletResponse response, FamilyStatusListDTO familyStatusListDTO, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        IPage familyStatusPage = familystatusService.findFamilyStatusPage(page, familyStatusListDTO, searchText);
        ExcelUtils.export2Web(response, "家庭情况况表", "家庭情况表", FamilyStatusListDTO.class, familyStatusPage.getRecords());
    }

    /**
     * 新增或修改家庭情况
     * @param
     * @return R
     */
    @ApiOperation(value = "新增或修改家庭情况", notes = "新增或修改家庭情况")
    @PostMapping("/saveOrUpdate")
    public R saveOrUpdate(@RequestBody Familystatus familystatus) {
    	UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName=userInfo.getSysUser().getAliasName();
        String loginCode=userInfo.getSysUser().getUsername();
        Long userId = userInfo.getSysUser().getUserId();
        LocalDateTime now = LocalDateTime.now();

    	if(familystatus.getId()!=null){
    		familystatus.setCreatorId(userId);
    		familystatus.setCreatorCode(loginCode);
    		familystatus.setCreatorName(userName);
    		familystatus.setCreatedTime(now);
    	}
		familystatus.setModifierId(userId);
		familystatus.setModifierCode(loginCode);
		familystatus.setModifierName(userName);
		familystatus.setModifiedTime(now);

        boolean status = familystatusService.saveOrUpdate(familystatus);
        return R.ok(status);
    }

    /**
     * 通过id删除员工家庭成员表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工家庭成员表", notes = "通过id删除员工家庭成员表")
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(familystatusService.removeById(id));
    }

    /**
     * 通过id查询员工家庭成员表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工家庭成员表", notes = "通过id查询员工家庭成员表")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('demo_demo_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(familystatusService.getById(id));
    }

}
