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
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffUserpost.UserpostDTO;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import net.herdao.hdp.manpower.mpclient.mapper.UserpostMapper;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author andy
 * @date 2020-09-15 08:57:53
 */
@Service
@AllArgsConstructor
public class UserpostServiceImpl extends ServiceImpl<UserpostMapper, Userpost> implements UserpostService {
    private final RemoteUserService remoteUserService;

    @Override
    public List<Userpost> findUserPost(Userpost condition) {
        List<Userpost> list = this.baseMapper.findUserPost(condition);
        return list;
    }

    @Override
    public IPage findUserPostNowPage(Page<UserpostDTO> page,UserpostDTO userpostDTO,  String searchText) {
    	Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("searchText", searchText);
		map.put("groupId",userpostDTO.getGroupId());
		map.put("orgId",userpostDTO.getOrgId());
    	
        page = page.setRecords(this.baseMapper.findUserPostNowPage(map));
        return page;
    }

    @Override
    public List<UserpostDTO> findUserPostNow(UserpostDTO userpostDTO, String searchText) {
        List<UserpostDTO> list = this.baseMapper.findUserPostNow(userpostDTO,searchText);
        return list;
    }

    @Override
    public UserpostDTO findCurrentJob(Long staffid){
    	UserpostDTO userpostDTO = this.baseMapper.findCurrentJob(staffid);
    	return userpostDTO;
    }

    @Override
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {
        return null;
    }
}
