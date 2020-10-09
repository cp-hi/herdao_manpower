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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.mpclient.dto.FormDto;
import net.herdao.hdp.manpower.mpclient.entity.Company;
import net.herdao.hdp.manpower.mpclient.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 页面组件接口
 * @author andy
 * @date 2020-09-15 17:10:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/form" )
@Api(value = "form", tags = "页面组件接口")
public class FormController {

    /**
     * 页面组件接口list
     * @param name 组件
     * @return
     */
    @ApiOperation(value = "页面组件接口list", notes = "页面组件接口list")
    @GetMapping("/getForm" )
    //@PreAuthorize("@pms.hasPermission('mpclient_mpcompany_view')" )
    public R getForm(String name) {
        List<FormDto> formDtoList=new ArrayList<>();

        if (name!=null && name.equals("组织报表")){
            FormDto formDto1 = new FormDto();
            formDto1.setName("组织架构表");
            Map<String,Object> operateMap1=new HashMap<>();
            operateMap1.put("download","/orgReport/exportOrg");
            operateMap1.put("preview"," /orgReport/findOrgReportView");
            formDto1.setOperateMap(operateMap1);
            formDtoList.add(formDto1);

            FormDto formDto2 = new FormDto();
            formDto2.setName("组织编制情况表");
            Map<String,Object> operateMap2=new HashMap<>();
            operateMap2.put("download","");
            operateMap2.put("preview","");
            formDto2.setOperateMap(operateMap2);
            formDtoList.add(formDto2);

            FormDto formDto3 = new FormDto();
            formDto3.setName("织职级统计表");
            Map<String,Object> operateMap3=new HashMap<>();
            operateMap3.put("download"," /orgReport/exportOrgJobLevel");
            operateMap3.put("preview","/orgReport/fetchOrgJobLevelView");
            formDto3.setOperateMap(operateMap3);
            formDtoList.add(formDto3);

            FormDto formDto4 = new FormDto();
            formDto4.setName("组织明细信息表");
            Map<String,Object> operateMap4=new HashMap<>();
            operateMap4.put("download","/orgReport/exportDetailsOrg");
            operateMap4.put("preview","/orgReport/findOrgReportView");
            formDto4.setOperateMap(operateMap4);
            formDtoList.add(formDto4);
        }

        return R.ok(formDtoList);
    }



}
