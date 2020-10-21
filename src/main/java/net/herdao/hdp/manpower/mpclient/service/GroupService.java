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

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.GroupDetailDTO;
import net.herdao.hdp.manpower.mpclient.entity.Group;

import java.util.List;
import java.util.Map;

/**
 * 集团表
 *
 * @author yangrr
 * @date 2020-09-11 11:57:16
 */
public interface GroupService extends IService<Group> {
    List<Map<String, String>> groupList();

    /**
     * @Author ljan
     * @param groupName
     * @return
     */
    default Group getGroupByName(String groupName) {
        Group group = this.getOne(new QueryWrapper<Group>()
                .eq("GROUP_NAME", groupName).ne("del_flag",1));
        if (null == group)
            throw new RuntimeException("不存在此集团：" + groupName);
        return group;
    }

    IPage groupPage(Page page, Group group, String searchText);

    boolean groupSave(GroupDetailDTO groupForm);

    boolean groupUpdate(GroupDetailDTO groupForm);

    GroupDetailDTO getGroupById(Long id);
}
