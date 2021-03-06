package net.herdao.hdp.manpower.mpclient.controller;

import cn.hutool.core.util.ObjectUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.constant.ExcelDescriptionContants;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainAddDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainAddErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StaffTrainUpdateDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrain.StafftrainDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftrain;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.StafftrainService;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public void initEasyExcelArgs(Class importAddCls, Class importAddErrCls, Class importUpdateCls, Class importUpdateErrCls, Integer excelIndex,
                                  Integer headRowNumber, List downloadUpdateTemplateList, String templateName, String excelDescription) {
        this.importAddCls = StaffTrainAddDTO.class;
        this.importAddErrCls = StaffTrainAddErrDTO.class;
        this.importUpdateCls = OrganizationUpdateDTO.class;
        this.importUpdateErrCls = StaffTrainUpdateDTO.class;
        this.excelName = "员工培训管理";
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
            list = this.stafftrainService.findStaffTrain(searchText,null);
        }else{
            list = this.stafftrainService.findStaffTrain(null,null);
        }
        return list;
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
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="关键字搜索"),
    })
    //@PreAuthorize("@pms.hasPermission('oa_organization_view')" )
    public R findStaffTrainPage(Page page, StafftrainDTO stafftrainDTO,String searchText) {
        return R.ok(stafftrainService.findStaffTrainPage(page,stafftrainDTO,searchText));
    }

    /**
     * 导出员工培训Excel
     * @param response
     * @return R
     */
    @ApiOperation(value = "导出员工培训Excel", notes = "导出员工培训Excel")
    @GetMapping("/exportTrain")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchText",value="关键字搜索")
    })
    @SneakyThrows
    public void exportTrain(HttpServletResponse response, StafftrainDTO stafftrainDTO, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        IPage pageResult = stafftrainService.findStaffTrainPage(page, stafftrainDTO, searchText);
        ExcelUtils.export2Web(response, "员工培训表", "员工培训表", StafftrainDTO.class, pageResult.getRecords());
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
        boolean status = stafftrainService.saveOrUpdate(staffTrain);
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
