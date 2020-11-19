/*
 *    Copyright (c) 2018-2025, hdp All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: hdp
 */

package net.herdao.hdp.manpower.mpclient.controller;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.staff.StaffAddVM;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.staff.StaffUpdateVM;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffListDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffPracticeDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffProTitleDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffQuickEditDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StaffWorkYearDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftransactionDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffUserpost.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffWork.WorkexperienceDTO;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.service.HdpService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;

import javax.servlet.http.HttpServletResponse;


/**
 * 员工表
 *
 * @author yangrr
 * @date 2020-09-23 18:10:29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staff" )
@Api(value = "staff", tags = "员工表管理")
@Slf4j
public class StaffController extends HdpBaseController{

    private final  StaffService staffService;

    @Override
    public HdpService getHdpService() {
        return this.staffService;
    }

    @Override
    public Class getImportAddCls() {
        return StaffAddVM.class;
    }

    @Override
    public Class getImportUpdateCls() {
        return StaffUpdateVM.class;
    }
    private static final Map<String,String> jobTypeMap = new HashMap<>();
    {
        jobTypeMap.put("2","1");
        jobTypeMap.put("3","2");
        jobTypeMap.put("4","7");
        jobTypeMap.put("5","97");
        jobTypeMap.put("6","98");
    }
    /**
     * 分页查询
     * @param page 分页对象
     * @param staff 员工表
     * @param tab 页签
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R getStaffPage(Page page, StaffListDTO staff, String tab, String searchText) {
        staff.setJobType(jobTypeMap.get(tab));
        return R.ok(staffService.staffPage(page, staff, searchText));
    }
    @ApiOperation(value = "员工花名册导出", notes = "员工花名册导出")
    @GetMapping("/export" )
    @SneakyThrows
    public void export(HttpServletResponse response, StaffListDTO staff, String tab, String searchText) {
        Page page = new Page();
        page.setSize(-1);
        staff.setJobType(jobTypeMap.get(tab));
        IPage iPage = staffService.staffPage(page, staff, searchText);
        ExcelUtils.export2Web(response, "员工花名册", "员工花名册", StaffListDTO.class, iPage.getRecords());
    }

    /**
     * 花名册员工数量
     */
    @ApiOperation(value = "查询员工数量", notes = "查询员工数量")
    @GetMapping("/count" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R getStaffCount() {
        return R.ok(staffService.queryCount());
    }

    /**
     * 通过id查询快速编辑员工
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询快速编辑员工", notes = "通过id查询快速编辑员工")
    @GetMapping("/quickedit/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R<StaffQuickEditDTO> getQuickById(@PathVariable("id" ) Long id) {
        Staff staff = staffService.getById(id);
        StaffQuickEditDTO entity = new StaffQuickEditDTO();
        BeanUtils.copyProperties(staff, entity);
        return R.ok(entity);
    }

    /**
     * 修改员工表
     * @param quickEdit 员工表
     * @return R
     */
    @ApiOperation(value = "花名册快速编辑员工", notes = "快速编辑员工")
    @SysLog("快速编辑员工" )
    @PutMapping("/quickedit" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_edit')" )
    public R<Boolean> updateById(@RequestBody StaffQuickEditDTO quickEdit) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(quickEdit, staff);
        return R.ok(staffService.updateById(staff));
    }

    /**
     * 通过id查询员工表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询个人主页", notes = "通过id查询")
    @GetMapping("/staffhomepage/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R getHomePage(@PathVariable("id" ) Long id) {
        return R.ok(staffService.getHomePage(id));
    }

    /**
     * 通过id查询员工表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工详情", notes = "通过id查询")
    @GetMapping("/staffdetail/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R getStaffDetail(@PathVariable("id" ) Long id) {
        return R.ok(staffService.getStaffDetail(id));
    }

    /**
     * 通过id查询员工劳资情况
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工劳资情况", notes = "通过id查询")
    @GetMapping("/staffwelfare/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R getStaffWelfare(@PathVariable("id" ) Long id) {
        return R.ok(staffService.getStaffWelfare(id));
    }

    /**
     * author lift
     * 通过id查询员工工作情况
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工工作情况", notes = "通过id查询")
    @GetMapping("/staffwork/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R getStaffWork(@PathVariable("id" ) Long id) {
        Staff staff = staffService.getById(id);
        Map<String, Object> map = staffService.getStaffWork(id);
        return R.ok(map);
    }
    
    /**
     * author lift
     * 通过id查询工作情况-目前任职
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询工作情况-目前任职", notes = "通过id查询工作情况-目前任职")
    @GetMapping("/getStaffWorkCurrentJob/{id}" )
    public R<UserpostDTO> getStaffWorkCurrentJob(@PathVariable("id" ) Long id) {
        Staff staff = staffService.getById(id);
        UserpostDTO userpostDTO = staffService.getStaffWorkCurrentJob(id);
        return R.ok(userpostDTO);
    }

    /**
     * author lift
     * 通过id查询工作情况-异动情况
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询工作情况-异动情况", notes = "通过id查询工作情况-异动情况")
    @GetMapping("/getStafftransaction/{id}" )
    public R<List<StafftransactionDTO>> getStafftransaction(@PathVariable("id" ) Long id) {
        List<StafftransactionDTO> stafftransactionDTOList = staffService.getStafftransaction(id);
        return R.ok(stafftransactionDTOList);
    }
    
    /**
     * author lift
     * 通过id查询工作情况-工作年限
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询工作情况-工作年限", notes = "通过id查询工作情况-工作年限")
    @GetMapping("/getStaffWorkYear/{id}" )
    public R<StaffWorkYearDTO> getStaffWorkYear(@PathVariable("id" ) Long id) {
    	StaffWorkYearDTO staffWorkYearDTO = staffService.getStaffWorkYear(id);
        return R.ok(staffWorkYearDTO);
    }

    /**
     * author lift
     * 通过id查询工作情况-工作经历
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询工作情况-工作经历", notes = "通过id查询工作情况-工作经历")
    @GetMapping("/getStaffWorkexperience/{id}" )
    public R<List<WorkexperienceDTO>> getWorkexperience(@PathVariable("id" ) Long id) {
    	List<WorkexperienceDTO> workexperienceDTOList = staffService.getWorkexperienceDTO(id);
        return R.ok(workexperienceDTOList);
    }

    /**
     * author lift
     * 通过id查询工作情况-实习记录
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询工作情况-实习记录", notes = "通过id查询工作情况-实习记录")
    @GetMapping("/getStaffPractice/{id}" )
    public R<StaffPracticeDTO> getStaffPractice(@PathVariable("id" ) Long id) {
    	StaffPracticeDTO staffPracticeDTO = staffService.getStaffPractice(id);
        return R.ok(staffPracticeDTO);
    }
    
//    /**
//     * author lift
//     * 修改实习记录
//     * @param staffPracticeDTO 实习记录
//     * @return R
//     */
//    @ApiOperation(value = "修改实习记录", notes = "修改实习记录")
//    @SysLog("修改实习记录" )
//    @PostMapping("/updateStaffPractice" )
//    public R updateStaffPractice(@RequestBody StaffPracticeDTO staffPracticeDTO) {
//        return R.ok(staffService.updateStaffPractice(staffPracticeDTO));
//    }

    /**
     * author lift
     * 通过id查询工作情况-职称及职业资格
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询工作情况-职称及职业资格", notes = "通过id查询工作情况-职称及职业资格")
    @GetMapping("/getStaffProTitle/{id}" )
    public R<List<StaffProTitleDTO>> getStaffProTitle(@PathVariable("id" ) Long id) {
    	List<StaffProTitleDTO> staffProTitleList = staffService.getStaffProTitle(id);
        return R.ok(staffProTitleList);
    }
    
    /**
     * author lift
     * 修改员工工作年限
     * @param staffWorkYearDTO 工作年限
     * @return R
     */
    @ApiOperation(value = "修改员工工作年限", notes = "修改员工工作年限")
    @SysLog("修改员工工作年限" )
    @PostMapping("/updateStaffWorkYear" )
    public R updateStaffWorkYear(@RequestBody StaffWorkYearDTO staffWorkYearDTO) {
        return R.ok(staffService.updateStaffWorkYear(staffWorkYearDTO));
    }

    /**
     * 新增员工表
     * @param staffForm 员工信息
     * @return R
     */
    @ApiOperation(value = "新增员工表", notes = "新增员工表")
    @SysLog("新增员工表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_add')" )
    public R<StaffDetailDTO> save(@RequestBody StaffDetailDTO staffForm) {
        return R.ok(staffService.staffSave(staffForm));
    }

    /**
     * 通过id删除员工表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工表", notes = "通过id删除员工表")
    @SysLog("通过id删除员工表" )
    @DeleteMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_del')" )
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(staffService.removeById(id));
    }
    
    
    /**
     * 员工选择组件

     * @return
     */
    @ApiOperation(value = "员工选择组件", notes = "不传参数默认加载一级组织信息，传orgCode 查询子组织和员工信息，"
    		     + "传batchSelectOrgCodes 根据组织编码查询员工，传staffCodes 根据员工工号查询员工信息， 模糊查询只查询员工信息")
    @GetMapping("/selectStaffOrganizationComponent")
    @ApiImplicitParams({@ApiImplicitParam(name="orgCode", value="组织编码"),
    					@ApiImplicitParam(name="batchSelectOrgCodes", value="批量选中组织编码，多个采用：“,”分隔开， 例如：001,002"),
    					@ApiImplicitParam(name="searchText", value="模糊查询条件")
    					
    })
    public R<List> selectStaffOrganizationComponent(String orgCode, String batchSelectOrgCodes, String searchText) {
    	return staffService.selectStaffOrganizationComponent(orgCode, batchSelectOrgCodes, null, searchText);
    }
    
    /**
     * 根据员工工号查询员工信息

     * @return
     */
    @ApiOperation(value = "根据员工ids查询员工信息", notes = "staffIds 根据员工ids查询员工信息")
    @GetMapping("/selectStaffByStaffIds")
    @ApiImplicitParam(name="staffIds", value="员工ID，多个采用：“,”分隔开， 例如：1001, 1002")
    public R<List> selectStaffByStaffIds(String staffIds) {
    	return staffService.selectStaffOrganizationComponent(null, null, staffIds, null);
    }
    

    /**
     * 通过id查询员工工作年限
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询员工工作情况", notes = "通过id查询")
    @GetMapping("/getStaffWorkLimit/{id}" )
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="员工ID")
    })
    public R getStaffWorkLimit(@PathVariable("id" ) Long id) {
        Staff staff = staffService.getById(id);
        if (staff!=null){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            if (staff.getWorkDate()!=null){
                Integer workSeniority = DateUtils.getYearByNowFloor(staff.getWorkDate().format(df));
                staff.setWorkSeniority(new BigDecimal(workSeniority.toString()));
            }

            if (staff.getEntryTime()!=null){
                Integer companySeniority = DateUtils.getYearByNowFloor(staff.getEntryTime().format(df));
                staff.setCompanySeniority(new BigDecimal(companySeniority.toString()));
            }

            if (staff.getEntryThreeGroupsTime()!=null){
                Integer threeGroupsSeniority = DateUtils.getYearByNowFloor(staff.getEntryThreeGroupsTime().format(df));
                staff.setThreeGroupsSeniority(new BigDecimal(threeGroupsSeniority.toString()));
            }

            //实际工作工龄
            if (staff.getWorkSeniority()!=null && staff.getNoWorkingSeniority()!=null){
                BigDecimal realWorkAge = staff.getWorkSeniority().subtract(staff.getNoWorkingSeniority());
                staff.setRealWorkAge(realWorkAge);
            }
        }

        return R.ok(staff);
    }

    /**
     * 获取员工头像
     * @return
     */
    @GetMapping("/getPhotoByCode")
    public R<String> getPhotoByCode(String staffCode) {
        List<Staff> list = staffService.list(new QueryWrapper<Staff>()
                .eq("STAFF_CODE", staffCode)
                .last("limit 1")
        );
        String photoAddr = "";
        if(list!=null && list.size()!=0){
            photoAddr = list.get(0).getPhotoAddr();
        }
        return R.ok(photoAddr);
    }

}
