/*
 *    Copyright (c) 2018-2025, herdao All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the herdao.net developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liang
 */
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.ReportTemplate;
import net.herdao.hdp.manpower.mpclient.mapper.ReportTemplateMapper;
import net.herdao.hdp.manpower.mpclient.service.ReportTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 报表模板表
 *
 * @author hsh
 * @date 2020-11-28 11:02:34
 */
@Service
public class ReportTemplateServiceImpl extends ServiceImpl<ReportTemplateMapper, ReportTemplate> implements ReportTemplateService {
    @Autowired
    private ReportTemplateMapper reportTemplateMapper;
    /**
     * 分页查询
     */
    @Override
    public IPage<ReportTemplate> findReportTelatePage(Page page, ReportTemplate reportTemplate, String seachText) {
        return reportTemplateMapper.findReportTelatePage(page,reportTemplate,seachText);
    }
}
