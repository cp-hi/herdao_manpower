package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.constant.ExcelDescriptionContants;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.*;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.StafftrainService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.utils.UserUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 员工培训
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@RestController
@RequestMapping("/stafftrain" )
@Api(value = "stafftrain", tags = "员工培训管理")
@Slf4j
public class StafftrainController extends HdpBaseController {

    @Autowired
    private StafftrainService stafftrainService;

    @Override
    public HdpService getHdpService() {
        return stafftrainService;
    }

    @Override
    public Class getImportAddCls() {
        return StaffTrainAddDTO.class;
    }

    @Override
    public Class getImportAddErrCls() {
        return StaffTrainAddErrDTO.class;
    }

    @Override
    public Class getImportUpdateCls() {
        return StaffTrainUpdateDTO.class;
    }

    @Override
    public Class getImportUpdateErrCls() {
        return StaffTrainUpdateErrDTO.class;
    }

    @Override
    public String getExcelAddDescription() {
        return ExcelDescriptionContants.getTrainAddDesc();
    }

    @Override
    public String getExcelUpdateDescription() {
        return ExcelDescriptionContants.getTrainUpdateDesc();
    }

    @Override
    public List getDownloadUpdateTemplateList(Map<String, Object> searchParams) {
        List<StafftrainDTO> list=null;
        if (ObjectUtil.isNotNull(searchParams)){
            String searchText = searchParams.get("searchText").toString();
            list = this.stafftrainService.findStaffTrain(searchText);
        }else{
            list = this.stafftrainService.findStaffTrain(null);
        }
        return list;
    }

    @Override
    public String getExcelName() {
        return "员工培训";
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param stafftrain 员工培训
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('mpclient_stafftrain_view')" )
    public R getStafftrainPage(Page page, Stafftrain stafftrain) {
        return R.ok(stafftrainService.page(page, Wrappers.query(stafftrain)));
    }

    /**
     * 通过id删除员工培训
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工培训", notes = "通过id删除员工培训")
    @SysLog("通过id删除员工培训" )
    @DeleteMapping("/del/{id}" )
    //@PreAuthorize("@pms.hasPermission('mpclient_stafftrain_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(stafftrainService.removeById(id));
    }

    /**
     * 员工培训分页
     * @param page 分页对象
     * @param searchText
     * @return
     */
    @ApiOperation(value = "员工培训分页", notes = "员工培训分页")
    @GetMapping("/findStaffTrainPage")
    @OperationEntity(operation = "员工培训分页" ,clazz = Stafftrain.class )
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffTrainPage(Page page, StafftrainDTO stafftrainDTO,String searchText) {
        Page pageResult = stafftrainService.findStaffTrainPage(page,stafftrainDTO,searchText);
        return R.ok(pageResult);
    }

    /**
     * 导出员工培训Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工培训Excel", notes = "导出员工培训Excel")
    @SysLog("导出员工培训Excel")
    @PostMapping("/exportTrain")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    public void exportTrain(HttpServletResponse response,StafftrainDTO stafftrainDTO,String searchText) {
        try {
            Page page =new Page();
            page.setSize(-1);
            Page pageResult = stafftrainService.findStaffTrainPage(page,stafftrainDTO,searchText);
            ExcelUtils.export2Web(response, "员工培训表", "员工培训表", StafftrainDTO.class,pageResult.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            R.ok("导出失败");
        }

        R.ok("导出成功");
    }

    /**
     * 修改
     * @param stafftrain
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @SysLog("修改" )
    @PostMapping("/updateTrain" )
    public R updateById(@RequestBody Stafftrain stafftrain) {
        SysUser sysUser = SysUserUtils.getSysUser();
        stafftrain.setModifierName(sysUser.getUsername());
        stafftrain.setModifiedTime(new Date());
        stafftrain.setModifierCode(sysUser.getUsername());
        stafftrain.setModifierId(sysUser.getUserId());
        boolean status = stafftrainService.updateById(stafftrain);
        return R.ok(status);
    }

    /**
     * 新增员工培训
     * @param staffTrain
     * @return R
     */
    @ApiOperation(value = "新增员工培训", notes = "新增员工培训")
    @SysLog("新增员工培训" )
    @PostMapping("/saveStaffTrain" )
    public R saveStaffTrain(@RequestBody Stafftrain staffTrain) {
        SysUser sysUser = SysUserUtils.getSysUser();
        staffTrain.setCreatorName(sysUser.getAliasName());
        staffTrain.setCreatedTime(new Date());
        staffTrain.setCreatorCode(sysUser.getUsername());
        staffTrain.setCreatorId(sysUser.getUserId());

        boolean status = stafftrainService.save(staffTrain);
        return R.ok(status);
    }

    /**
     * 通过id查询员工奖惩表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工奖惩表", notes = "通过id查询员工奖惩表")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(stafftrainService.getById(id));
    }

}
