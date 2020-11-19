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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffUserpost.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Userposthistory;
import net.herdao.hdp.manpower.mpclient.mapper.UserposthistoryMapper;
import net.herdao.hdp.manpower.mpclient.service.UserposthistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工岗位历史表
 *
 * @author yangrr
 * @date 2020-09-25 17:24:25
 */
@Service
@AllArgsConstructor
public class UserposthistoryServiceImpl extends ServiceImpl<UserposthistoryMapper, Userposthistory> implements UserposthistoryService {

    @Override
    public Page<UserpostDTO> findUserPostHistoryPage(Page<UserpostDTO> page,UserpostDTO userpostDTO, String searchText) {
        Page<UserpostDTO> pageResult = this.baseMapper.findUserPostHistoryPage(page,userpostDTO, searchText);
        return pageResult;
    }

    @Override
    public List<UserpostDTO> findUserPostHistory(UserpostDTO userpostDTO,String searchText) {
        List<UserpostDTO> pageResult = this.baseMapper.findUserPostHistory(userpostDTO,searchText);
        return pageResult;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
