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

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.herdao.hdp.manpower.mpclient.dto.GroupDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.GroupListDTO;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 集团表
 *
 * @author yangrr
 * @date 2020-09-11 11:57:16
 */
@Mapper
@Component
public interface GroupMapper extends BaseMapper<Group> {
    List<Map<String, String>> groupList();

    /**
     * 集团分页列表
     *
     * @param map
     * @return
     */
    List<GroupListDTO> groupPage(Map<String, Object> map);

    /**
     * 组织获取所在集团
     * @param orgId
     * @return
     */
    Group getGroupByOrgId(Long orgId);
}
