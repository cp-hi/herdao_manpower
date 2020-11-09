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

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.dto.GroupDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.GroupListDTO;
import net.herdao.hdp.manpower.mpclient.dto.easyexcel.ExcelCheckErrDTO;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.group.GroupAddVM;
import net.herdao.hdp.manpower.mpclient.dto.excelVM.group.GroupUpdateVM;
import net.herdao.hdp.manpower.mpclient.dto.organization.OrganizationImportDTO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import net.herdao.hdp.manpower.mpclient.mapper.GroupMapper;
import net.herdao.hdp.manpower.mpclient.service.GroupService;
import net.herdao.hdp.manpower.mpclient.service.OrganizationService;
import net.herdao.hdp.manpower.sys.service.SysSequenceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private SysSequenceService sysSequenceService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    @SuppressWarnings("all")
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelCheckErrDTO> checkImportExcel(List excelList, Integer importType) {

        // 错误数组
        List<ExcelCheckErrDTO> errList = new ArrayList<>();

        // 批量新增、编辑组织信息
        List<Group> groupList = new ArrayList<>();

        List<OrganizationImportDTO> orgList = organizationService.selectAllOrganization();
        Map<String, Long> renderMap = new HashMap<>();
        if (ObjectUtil.isNotEmpty(orgList)) {
            orgList.forEach(org -> {
                renderMap.put(org.getOrgCode(), org.getId());
            });
        }
        if (importType == 0) {
            long code = sysSequenceService.getNext("group_code");
            for (int i = 0; i < excelList.size(); i++) {
                GroupAddVM entity = (GroupAddVM) excelList.get(i);
                Group group = new Group();
                BeanUtils.copyProperties(entity, group);
                String groupCode = "JT" + code;
                group.setGroupCode(groupCode);
                Long OrgId = renderMap.get(group.getOrgCode());
                group.setOrgId(OrgId);
                code++;
                groupList.add(group);
            }
            sysSequenceService.updateSeq("group_code", code - 1);
            //add
        } else {
            List<Group> groupAllList = this.list();
            Map<String, Long> renderGroupMap = new HashMap<>();
            if (ObjectUtil.isNotEmpty(groupAllList)) {
                groupAllList.forEach(group -> {
                    renderGroupMap.put(group.getGroupCode(), group.getId());
                });
            }
            for (int i = 0; i < excelList.size(); i++) {
                GroupUpdateVM entity = (GroupUpdateVM) excelList.get(i);
                Group group = new Group();
                BeanUtils.copyProperties(entity, group);
                Long OrgId = renderMap.get(group.getOrgCode());
                group.setOrgId(OrgId);
                Long id = renderGroupMap.get(group.getGroupCode());
                group.setId(id);
                groupList.add(group);
            }
        }
        // 保存新增、修改组织信息
        if (ObjectUtil.isEmpty(errList)) {
            if (groupList.size() != 0) {
                this.saveOrUpdateBatch(groupList, 200);
            }
        } else {
            //update
            return errList;
        }
        return errList;
    }

    @Override
    public List<Map<String, String>> groupList() {
        return baseMapper.groupList();
    }

    @Override
    public IPage groupPage(Page page, GroupListDTO group, String searchText) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("searchText", searchText);
        map.put("isStop", group.getIsStop());
        page = page.setRecords(baseMapper.groupPage(map));
        return page;
    }

    @Override
    public boolean groupSave(GroupDetailDTO groupForm) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName = userInfo.getSysUser().getAliasName();
        String loginCode = userInfo.getSysUser().getUsername();
        Long userId = userInfo.getSysUser().getUserId();

        Group group = new Group();
        BeanUtils.copyProperties(groupForm, group);
        long code = sysSequenceService.getNext("group_code");
        String groupCode = "JT" + code;
        group.setGroupCode(groupCode);

        //创建人工号、姓名、时间；修改人工号、姓名、时间
        DateTime now = DateTime.now();//LocalDateTime.now();
        group.setCreatorId(userId);
        group.setCreatorName(userName);
        group.setCreatedTime(now);
        group.setModifierId(userId);
        group.setModifierName(userName);
        group.setModifiedTime(now);

        return this.save(group);
    }

    @Override
    public boolean groupUpdate(GroupDetailDTO groupForm) {
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
        String userName = userInfo.getSysUser().getAliasName();
        String loginCode = userInfo.getSysUser().getUsername();
        Long userId = userInfo.getSysUser().getUserId();

        Group group = new Group();
        BeanUtils.copyProperties(groupForm, group);

        //修改人工号、姓名、时间
        DateTime now = DateTime.now();
        group.setModifierId(userId);
        group.setModifierName(userName);
        group.setModifiedTime(now);

        return this.updateById(group);
    }

    @Override
    public GroupDetailDTO getGroupById(Long id) {
        Group group = this.getById(id);
        GroupDetailDTO form = new GroupDetailDTO();
        BeanUtils.copyProperties(group, form);
        return form;
    }

    @Override
    public Group selectByName(String groupName, boolean need, StringBuffer buffer) {
        Group group = getOne(Wrappers.<Group>query().lambda().eq(Group::getGroupName, groupName));
        String errMsg = "";
        if (!need && null != group)  //不需要它但它不为空
            //添加分号，因为批量导入需要所有错误信息
            errMsg = "；已存在此集团" + groupName;
        if (need && null == group)  //需要它但它为空
            errMsg = "；不存在此集团" + groupName;
        buffer.append(errMsg);
        return group;
    }

    @SneakyThrows
    @Override
    public Group selectByName(String groupName, boolean need) {
        StringBuffer buffer = new StringBuffer();
        Group group = this.selectByName(groupName, need, buffer);
        if (StringUtils.isNotBlank(buffer))
            throw new Exception(buffer.toString());
        return group;
    }
}
