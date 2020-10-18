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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.dto.FormDTO;
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
        List<FormDTO> formDTOList =new ArrayList<>();

        if (name!=null && name.equals("组织报表")){
            FormDTO formDTO1 = new FormDTO();
            formDTO1.setId("1");
            formDTO1.setName("组织架构表");
            Map<String,Object> operateMap1=new HashMap<>();
            operateMap1.put("download","/orgReport/exportOrg");
            operateMap1.put("preview","/orgReport/findOrgReportView");
            formDTO1.setOperateMap(operateMap1);
            formDTOList.add(formDTO1);

            FormDTO formDTO2 = new FormDTO();
            formDTO2.setId("2");
            formDTO2.setName("组织编制情况表");
            Map<String,Object> operateMap2=new HashMap<>();
            operateMap2.put("download","");
            operateMap2.put("preview","");
            formDTO2.setOperateMap(operateMap2);
            formDTOList.add(formDTO2);

            FormDTO formDTO3 = new FormDTO();
            formDTO3.setId("3");
            formDTO3.setName("织职级统计表");
            Map<String,Object> operateMap3=new HashMap<>();
            operateMap3.put("download","/orgReport/exportOrgJobLevel");
            operateMap3.put("preview","/orgReport/fetchOrgJobLevelView");
            formDTO3.setOperateMap(operateMap3);
            formDTOList.add(formDTO3);

            FormDTO formDTO4 = new FormDTO();
            formDTO4.setId("4");
            formDTO4.setName("组织明细信息表");
            Map<String,Object> operateMap4=new HashMap<>();
            operateMap4.put("download","/orgReport/exportDetailsOrg");
            operateMap4.put("preview","/orgReport/findOrgReportView");
            formDTO4.setOperateMap(operateMap4);
            formDTOList.add(formDTO4);
        }

        return R.ok(formDTOList);
    }



}
