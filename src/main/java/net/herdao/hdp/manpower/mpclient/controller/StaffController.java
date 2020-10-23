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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.herdao.hdp.manpower.mpclient.dto.StaffDTO;
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.*;
import net.herdao.hdp.manpower.mpclient.entity.*;
import net.herdao.hdp.manpower.mpclient.listener.StaffExcelListener;
import net.herdao.hdp.manpower.mpclient.service.*;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.utils.DtoUtils;
import net.herdao.hdp.manpower.mpclient.utils.ExcelUtils;
import net.herdao.hdp.manpower.mpclient.vo.StaffOrganizationComponentVO;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import org.springframework.web.multipart.MultipartFile;
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
public class StaffController {

    private final  StaffService staffService;
    
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
    public R getStaffPage(Page page, Staff staff, String tab, String searchText) {
        if("1".equals(tab)){

        }else if("2".equals(tab)){
            staff.setJobType("1");
        }else if("3".equals(tab)){
            staff.setJobType("2");
        }else if("4".equals(tab)){
            staff.setJobType("3");
        }
        return R.ok(staffService.staffPage(page, staff, searchText));
    }

    @ApiOperation(value = "导出员工信息", notes = "导出员工信息")
    @GetMapping("/export" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public void exportStaff(HttpServletResponse response, Page page, Staff staff, String tab, String searchText) {
        if("1".equals(tab)){

        }else if("2".equals(tab)){
            staff.setJobType("1");
        }else if("3".equals(tab)){
            staff.setJobType("2");
        }else if("4".equals(tab)){
            staff.setJobType("3");
        }
        page.setCurrent(1);
        page.setSize(50000);
        IPage result = staffService.page(page, Wrappers.query(staff));
        long total = result.getTotal();
        if(total>50000){
            return;
        }
        List<Staff> list = result.getRecords();
        List<StaffDTO> entityList = new ArrayList<>();
        StaffDTO entity;
        for(int i=0;i<list.size();i++){
            entity = DtoUtils.transferObject(list.get(i), StaffDTO.class);
            entityList.add(entity);
        }
        try {
            ExcelUtils.export2Web(response, "员工花名册", "员工花名册", StaffDTO.class,entityList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @ApiOperation("导入")
    @SysLog("导入")
    @PostMapping("/import")
    public R<String> importExcel(MultipartFile file, String editType){
        System.out.println(editType);
        try {
            EasyExcel.read(file.getInputStream(), StaffDTO.class,
                    new StaffExcelListener<StaffDTO, Staff>(staffService, Staff.class)).sheet().doRead();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return R.ok("导入成功");
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
     * 通过id查询员工表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_view')" )
    public R<StaffDetailDTO> getById(@PathVariable("id" ) Long id) {
        return R.ok(staffService.getStaffById(id));
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
    public R save(@RequestBody StaffDetailDTO staffForm) {
        return R.ok(staffService.staffSave(staffForm));
    }

    /**
     * 修改员工表
     * @param staffForm 员工表
     * @return R
     */
    @ApiOperation(value = "修改员工表", notes = "修改员工表")
    @SysLog("修改员工表" )
    @PutMapping
//    @PreAuthorize("@pms.hasPermission('mpclient_staff_edit')" )
    public R<Boolean> updateById(@RequestBody StaffDetailDTO staffForm) {
        return R.ok(staffService.staffUpdate(staffForm));
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
    @GetMapping("/selectStaffOrganizationComponent")
    public R<List<StaffOrganizationComponentVO>> selectStaffOrganizationComponent() {
        return staffService.selectStaffOrganizationComponent();
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

}
