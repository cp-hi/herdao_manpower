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
import net.herdao.hdp.manpower.mpclient.dto.GroupDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.GroupListDTO;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.group.GroupAddVM;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.mapper.GroupMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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
    @SuppressWarnings("all")
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {

        // 错误数组
        List<ExcelCheckErrDTO> errList = new ArrayList<>();

        // 批量新增、编辑组织信息
        List<Group> groupList = new ArrayList<>();

        if (importType == 0) {
            for(int i=0;i<excelList.size();i++){
                GroupAddVM entity = (GroupAddVM)excelList.get(i);
                Group group = new Group();
                BeanUtils.copyProperties(entity, group);
                groupList.add(group);
            }
            //add
        }else {
            //update
        }
        // 保存新增、修改组织信息
        if(ObjectUtil.isEmpty(errList)) {
            this.saveOrUpdateBatch(groupList, 200);
        }
        return errList;
    }

    @Override
    public List<Map<String, String>> groupList() {
        return baseMapper.groupList();
    }

    @Override
    public IPage groupPage(Page page, GroupListDTO group, String searchText){
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("searchText", searchText);
        map.put("isStop", group.getIsStop());
        page = page.setRecords(baseMapper.groupPage(map));
        return page;
    }

    @Override
    public boolean groupSave(GroupDetailDTO groupForm){
        Group group = new Group();
        BeanUtils.copyProperties(groupForm, group);
        return this.save(group);
    }

    @Override
    public boolean groupUpdate(GroupDetailDTO groupForm){
        Group group = new Group();
        BeanUtils.copyProperties(groupForm, group);
        return this.updateById(group);
    }

    @Override
    public GroupDetailDTO getGroupById(Long id){
        Group group = this.getById(id);
        GroupDetailDTO form = new GroupDetailDTO();
        BeanUtils.copyProperties(group, form);
        return form;
    }
}
