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

package net.herdao.hdp.manpower.mpclient.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import net.herdao.hdp.manpower.mpclient.dto.CompanyDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.CompanyListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Company;

/**
 * 注册公司
 *
 * @author liang
 * @date 2020-09-15 17:10:12
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 注册公司分页列表
     *
     * @param map
     * @return
     */
    List<CompanyListDTO> companyPage(Map<String, Object> map);

    
    CompanyDetailDTO getCompanyById(@Param("id") Long id);
}
