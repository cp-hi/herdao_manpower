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
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffContract.StaffcontractDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffContract.StaffContractAddDTO;
import net.herdao.hdp.manpower.mpclient.entity.Company;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.mapper.StaffcontractMapper;
import net.herdao.hdp.manpower.mpclient.service.CompanyService;
import net.herdao.hdp.manpower.mpclient.service.StaffService;
import net.herdao.hdp.manpower.mpclient.service.StaffcontractService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.mpclient.utils.ImportCheckUtils;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工合同签订
 *
 * @author andy
 * @date 2020-09-27 09:15:28
 */
@Service
@AllArgsConstructor
public class StaffcontractServiceImpl extends ServiceImpl<StaffcontractMapper, Staffcontract> implements StaffcontractService {

    private StaffService staffService;

    private SysDictItemService itemService;

    private CompanyService companyService;

    @Override
    public Page<StaffcontractDTO> findStaffContractPage(Page<StaffcontractDTO> page, String searchText) {
        Page<StaffcontractDTO> list = this.baseMapper.findStaffContractPage(page, searchText);
        return list;
    }

    @Override
    public List<StaffcontractDTO> findStaffContract(String searchText) {
        List<StaffcontractDTO> list = this.baseMapper.findStaffContract(searchText);
        return list;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        StringBuffer errMsg = new StringBuffer();
        List<ExcelCheckErrDTO> errList = new ArrayList<>();
        List<Staffcontract> staffContractList=new ArrayList<Staffcontract>();

        // 新增校验
        if (importType == 0) {
            checkAdd(excelList, errMsg, errList, staffContractList);
        }

        // 编辑校验
        if (importType == 1) {
            checkUpdate(excelList, errMsg, errList, staffContractList);
        }

        if(ObjectUtil.isEmpty(errList)) {
            this.saveOrUpdateBatch(staffContractList);
        }
        return errList;
    }

    /**
     * 新增校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param staffContractList
     */
    private void checkAdd(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Staffcontract> staffContractList) {
        for (int i = 0; i < excelList.size(); i++) {
            Staffcontract staffcontract=new Staffcontract();
            StaffContractAddDTO addDTO = (StaffContractAddDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId);

            //校检时间
            String pattern= ImportCheckUtils.checkDate(errMsg, addDTO.getStartDate(),addDTO.getEndDate());

            //校检合同期限类型
            SysDictItem contractTypeDicItem = ImportCheckUtils.checkDicItem(errMsg, "HTQXLX", addDTO.getContractType(), itemService);
            if(null != contractTypeDicItem){
                addDTO.setContractType(contractTypeDicItem.getValue());
            }

            //校检合同是否生效 0生效 ，1失效
            if (addDTO.getContractStatus()!=null){
                if (addDTO.getContractStatus().equals("是")){
                    addDTO.setNewest(false);
                }else if (addDTO.getContractStatus().equals("否")){
                    addDTO.setNewest(true);
                }else{
                    ImportCheckUtils.appendStringBuffer(errMsg, "请填写合同是否生效 : 是或否");
                }
            }

            //校检合同签订主体。
            Company company = companyService.getOne(
                new QueryWrapper<Company>()
                        .eq("COMPANY_NAME", addDTO.getCompany())

            );
            if (null==company){
                ImportCheckUtils.appendStringBuffer(errMsg, "合同签订主体不存在："+addDTO.getCompany()+";");
            }else {
                addDTO.setCompanyCode(company.getCompanyCode());
            }

            //检查数据库是否存在记录，且唯一记录。
            List<Staffcontract> checkList = super.list(
                new QueryWrapper<Staffcontract>()
                        .eq("staff_id", staffId)
                        .eq("start_date", addDTO.getStartDate())
                        .eq("end_date", addDTO.getEndDate())
            );
            if (!checkList.isEmpty()&&checkList.size()>=1){
                ImportCheckUtils.appendStringBuffer(errMsg, "合同签订表存在多条此记录，因此不可新增；");
            }

            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, staffcontract);
                staffcontract.setStartDate(DateUtils.parseDate(addDTO.getStartDate(),pattern));
                staffcontract.setEndDate(DateUtils.parseDate(addDTO.getEndDate(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                staffcontract.setCreatedTime(LocalDateTime.now());
                staffcontract.setCreatorCode(sysUser.getUserId().toString());

                staffContractList.add(staffcontract);
            }
        }
    }

    /**
     * 新增校验
     * @param excelList
     * @param errMsg
     * @param errList
     * @param staffContractList
     */
    private void checkUpdate(List excelList, StringBuffer errMsg, List<ExcelCheckErrDTO> errList, List<Staffcontract> staffContractList) {
        for (int i = 0; i < excelList.size(); i++) {
            Staffcontract staffcontract=new Staffcontract();
            StaffContractAddDTO addDTO = (StaffContractAddDTO) excelList.get(i);

            //校检员工
            Long staffId = ImportCheckUtils.checkStaff(errMsg, addDTO.getStaffCode(), addDTO.getStaffName(),staffService);
            addDTO.setStaffId(staffId);

            //校检时间
            String pattern= ImportCheckUtils.checkDate(errMsg, addDTO.getStartDate(),addDTO.getEndDate());

            //校检合同期限类型
            SysDictItem contractTypeDicItem = ImportCheckUtils.checkDicItem(errMsg, "HTQXLX", addDTO.getContractType(), itemService);
            if(null != contractTypeDicItem){
                addDTO.setContractType(contractTypeDicItem.getValue());
            }

            //校检合同是否生效 0生效 ，1失效
            if (addDTO.getContractStatus()!=null){
                if (addDTO.getContractStatus().equals("是")){
                    addDTO.setNewest(false);
                }else if (addDTO.getContractStatus().equals("否")){
                    addDTO.setNewest(true);
                }else{
                    ImportCheckUtils.appendStringBuffer(errMsg, "请填写合同是否生效 : 是或否");
                }
            }

            //校检合同签订主体。
            Company company = companyService.getOne(
                    new QueryWrapper<Company>()
                            .eq("COMPANY_NAME", addDTO.getCompany())

            );
            if (null==company){
                ImportCheckUtils.appendStringBuffer(errMsg, "合同签订主体不存在："+addDTO.getCompany()+";");
            }else {
                addDTO.setCompanyCode(company.getCompanyCode());
            }

            //检查数据库是否存在记录，且唯一记录。
            List<Staffcontract> checkList = super.list(
                    new QueryWrapper<Staffcontract>()
                            .eq("staff_id", staffId)
                            .eq("start_date", addDTO.getStartDate())
                            .eq("end_date", addDTO.getEndDate())
            );
            if (checkList.isEmpty()){
                ImportCheckUtils.appendStringBuffer(errMsg, "合同签订表中不存在此记录，因此不可编辑更新；");
            }else if (!checkList.isEmpty()&&checkList.size()>1){
                ImportCheckUtils.appendStringBuffer(errMsg, "合同签订表中存在多条此记录，因此不可编辑更新；");
            }else{
                addDTO.setId(checkList.get(0).getId());
            }


            if (errMsg.length() > 0) {
                errList.add(new ExcelCheckErrDTO(addDTO, errMsg.toString()));
            }else {
                BeanUtils.copyProperties(addDTO, staffcontract);
                staffcontract.setStartDate(DateUtils.parseDate(addDTO.getStartDate(),pattern));
                staffcontract.setEndDate(DateUtils.parseDate(addDTO.getEndDate(),pattern));

                SysUser sysUser = SysUserUtils.getSysUser();
                staffcontract.setModifiedTime(LocalDateTime.now());
                staffcontract.setModifierCode(sysUser.getUserId().toString());

                staffContractList.add(staffcontract);
            }
        }
    }
}
