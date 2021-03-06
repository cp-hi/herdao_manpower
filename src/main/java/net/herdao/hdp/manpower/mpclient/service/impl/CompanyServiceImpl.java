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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.mpclient.dto.CompanyDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.CompanyListDTO;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.company.CompanyAddVM;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.company.CompanyUpdateVM;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationImportDTO;
import net.herdao.hdp.manpower.mpclient.entity.Company;
import net.herdao.hdp.manpower.mpclient.mapper.CompanyMapper;
import net.herdao.hdp.manpower.mpclient.service.CompanyService;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import net.herdao.hdp.manpower.sys.service.SysSequenceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 注册公司
 *
 * @author liang
 * @date 2020-09-15 17:10:12
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Autowired
    private SysSequenceService sysSequenceService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    @SuppressWarnings("all")
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {

        // 错误数组
        List<ExcelCheckErrDTO> errList = new ArrayList<>();

        // 批量新增、编辑组织信息
        List<Company> companyList = new ArrayList<>();

        List<OrganizationImportDTO> orgList = organizationService.selectAllOrganization();
        Map<String, Long> renderMap = new HashMap<>();
        if(ObjectUtil.isNotEmpty(orgList)) {
            orgList.forEach(org ->{
                renderMap.put(org.getOrgCode(), org.getId());
            });
        }

        if (importType == 0) {
            long code = sysSequenceService.getNext("company_code");
            for(int i=0;i<excelList.size();i++){
                CompanyAddVM entity = (CompanyAddVM)excelList.get(i);
                Company company = new Company();
                BeanUtils.copyProperties(entity, company);
                String companyCode = "GS" + code;
                company.setCompanyCode(companyCode);
                Long OrgId = renderMap.get(entity.getOrgCode());
                company.setOrgId(OrgId);
                code++;
                companyList.add(company);
            }
            sysSequenceService.updateSeq("company_code", code-1);
            //add
        }else {
            //update
            List<Company> companyAllList = this.list();
            Map<String, Long> renderCompanyMap = new HashMap<>();
            if(ObjectUtil.isNotEmpty(companyAllList)) {
                companyAllList.forEach(company ->{
                    renderCompanyMap.put(company.getCompanyCode(), company.getId());
                });
            }
            for(int i=0;i<excelList.size();i++){
                CompanyUpdateVM entity = (CompanyUpdateVM)excelList.get(i);
                Company company = new Company();
                BeanUtils.copyProperties(entity, company);
                Long OrgId = renderMap.get(entity.getOrgCode());
                company.setOrgId(OrgId);
                Long id = renderCompanyMap.get(company.getCompanyCode());
                company.setId(id);
                companyList.add(company);
            }
        }
        // 保存新增、修改组织信息
        if(ObjectUtil.isEmpty(errList)) {
            this.saveOrUpdateBatch(companyList, 200);
        }
        return errList;
    }

    @Override
    public IPage companyPage(Page page, CompanyListDTO company, String searchText){
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("searchText", searchText);
        map.put("isStop", company.getIsStop());
        page = page.setRecords(baseMapper.companyPage(map));
        return page;
    }

    @Override
    public boolean companySave(CompanyDetailDTO companyForm){
        Company company = new Company();
        BeanUtils.copyProperties(companyForm, company);
        long code = sysSequenceService.getNext("company_code");
        String companyCode = "GS" + code;
        company.setCompanyCode(companyCode);
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        company.setCreatorId(userInfo.getSysUser().getUserId());
        company.setCreatorName(userInfo.getSysUser().getAliasName());
        company.setCreatedTime(new Date());
        boolean flag = this.save(company);
        BeanUtils.copyProperties(company,companyForm);
        return flag;
    }

    @Override
    public boolean companyUpdate(CompanyDetailDTO companyForm){
        Company company = new Company();
        BeanUtils.copyProperties(companyForm, company);
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        company.setModifierId(userInfo.getSysUser().getUserId());
        company.setModifierName(userInfo.getSysUser().getAliasName());
        company.setModifiedTime(new Date());
        return this.updateById(company);
    }

    @Override
    public CompanyDetailDTO getCompanyById(Long id){
        return this.baseMapper.getCompanyById(id);
    }

    @Autowired
    private CompanyMapper mapper;
    @Override
    public void validityCheck(Long id, String msg) throws Exception {
        Company company = mapper.selectById(id);
        if (company == null) {
            throw new Exception(msg);
        }
    }
}
