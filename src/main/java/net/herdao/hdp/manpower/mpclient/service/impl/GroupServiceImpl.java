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
import net.herdao.hdp.manpower.mpclient.dto.GroupFormDto;
import net.herdao.hdp.manpower.mpclient.dto.GroupListDto;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.entity.Staff;
import net.herdao.hdp.manpower.mpclient.mapper.GroupMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.utils.DtoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 集团表
 *
 * @author yangrr
 * @date 2020-09-11 11:57:16
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
    @Override
    public List<Map<String, String>> groupList() {
        return baseMapper.groupList();
    }

    @Override
    public IPage groupPage(Page page, Group group, String searchText){
        QueryWrapper<Group> wrapper =  Wrappers.query(group);
        if(searchText!=null && !"".equals(searchText)){
            wrapper.like("CONCAT(GROUP_NAME,GROUP_FULLNAME)", searchText);
        }
        IPage result = this.page(page, wrapper);
        List<Group> list = result.getRecords();
        List<GroupListDto> entityList = new ArrayList<>();
        GroupListDto entity;
        for(int i=0;i<list.size();i++){
            entity = DtoUtils.transferObject(list.get(i), GroupListDto.class);
            entityList.add(entity);
        }
        result.setRecords(entityList);
        return result;
    }

    @Override
    public boolean groupSave(GroupFormDto groupForm){
        Group group = new Group();
        BeanUtils.copyProperties(groupForm, group);
        return this.save(group);
    }

    @Override
    public boolean groupUpdate(GroupFormDto groupForm){
        Group group = new Group();
        BeanUtils.copyProperties(groupForm, group);
        return this.updateById(group);
    }

    @Override
    public GroupFormDto getGroupById(Long id){
        Group group = this.getById(id);
        GroupFormDto form = new GroupFormDto();
        BeanUtils.copyProperties(group, form);
        return form;
    }
}
