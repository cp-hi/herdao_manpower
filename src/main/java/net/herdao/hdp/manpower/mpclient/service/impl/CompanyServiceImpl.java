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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.CompanyDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.CompanyListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Company;
import net.herdao.hdp.manpower.mpclient.mapper.CompanyMapper;
import net.herdao.hdp.manpower.mpclient.service.CompanyService;
import net.herdao.hdp.manpower.mpclient.utils.DtoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册公司
 *
 * @author liang
 * @date 2020-09-15 17:10:12
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Override
    public IPage companyPage(Page page, Company company, String searchText){
        QueryWrapper<Company> wrapper =  Wrappers.query(company);
        if(searchText!=null && !"".equals(searchText)){
            wrapper.like("CONCAT(COMPANY_NAME,COMPANY_CODE)", searchText);
        }
        IPage result = this.page(page, wrapper);
        List<Company> list = result.getRecords();
        List<CompanyListDTO> entityList = new ArrayList<>();
        CompanyListDTO entity;
        for(int i=0;i<list.size();i++){
            entity = DtoUtils.transferObject(list.get(i), CompanyListDTO.class);
            entityList.add(entity);
        }
        result.setRecords(entityList);
        return result;
    }

    @Override
    public boolean companySave(CompanyDetailDTO companyForm){
        Company company = new Company();
        BeanUtils.copyProperties(companyForm, company);
        return this.save(company);
    }

    @Override
    public boolean companyUpdate(CompanyDetailDTO companyForm){
        Company company = new Company();
        BeanUtils.copyProperties(companyForm, company);
        return this.updateById(company);
    }

    @Override
    public CompanyDetailDTO getCompanyById(Long id){
        Company company = this.getById(id);
        CompanyDetailDTO form = new CompanyDetailDTO();
        BeanUtils.copyProperties(company, form);
        return form;
    }

}
