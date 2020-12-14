/*
 *    Copyright (c) 2018-2025, herdao All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the herdao.net developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liang
 */

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveApprovalDetailDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPositiveApproval;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Mapper
public interface StaffPositiveApprovalMapper extends BaseMapper<StaffPositiveApproval> {

    /**
     * 分页查询
     * @param page 分页对象
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @param status 状态：1 填报中，2 审批中，3 已审批
     * @return
     */
    Page<StaffPositiveApprovalPage> getPositiveApprovalPageInfo(Page<StaffPositiveApproval> page,
                                                                @Param("orgId") Long orgId,
                                                                @Param("status") String status,
                                                                @Param("searchText") String searchText);

    /**
     * 获取转正详情
     * @param id 主键ID
     * @return
     */
    StaffPositiveApprovalDetailDTO getPositiveApprovalById(Long id);
}
