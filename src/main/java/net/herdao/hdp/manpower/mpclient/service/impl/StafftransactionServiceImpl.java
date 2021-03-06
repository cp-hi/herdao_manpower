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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staff.StafftransactionDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffTrans.StafftransDTO;
import net.herdao.hdp.manpower.mpclient.entity.Stafftransaction;
import net.herdao.hdp.manpower.mpclient.mapper.StafftransactionMapper;
import net.herdao.hdp.manpower.mpclient.service.StafftransactionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异动情况
 *
 * @author andy
 * @date 2020-09-24 16:00:18
 */
@Service
@AllArgsConstructor
public class StafftransactionServiceImpl extends ServiceImpl<StafftransactionMapper, Stafftransaction> implements StafftransactionService {
    @Override
    public IPage findStaffTransPage(Page<StafftransDTO> page,StafftransDTO stafftransDTO, String searchText) {
    	Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("searchText", searchText);
		map.put("groupId",stafftransDTO.getGroupId());
		map.put("orgId",stafftransDTO.getOrgId());
		
        page = page.setRecords(this.baseMapper.findStaffTransPage(map));
        return page;
    }

    @Override
    public List<StafftransDTO> findStaffTrans(StafftransDTO stafftransDTO, String searchText) {
        List<StafftransDTO> list = this.baseMapper.findStaffTrans(stafftransDTO,searchText);
        return list;
    }

    @Override
    public List<StafftransactionDTO> findStafftransactionDto(Long staffid){
    	List<StafftransactionDTO> list = this.baseMapper.findStafftransactionDto(staffid);
        return list;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
