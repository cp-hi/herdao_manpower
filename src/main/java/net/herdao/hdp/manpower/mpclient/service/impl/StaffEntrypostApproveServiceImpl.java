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
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveDTO;
import net.herdao.hdp.manpower.mpclient.dto.entryApprove.EntryApproveFormDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffEntrypostApprove;
import net.herdao.hdp.manpower.mpclient.mapper.StaffEntrypostApproveMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffEntrypostApproveService;
import org.springframework.stereotype.Service;

/**
 * 录用审批表
 *
 * @author Andy
 * @date 2020-11-30 10:54:24
 */
@Service
public class StaffEntrypostApproveServiceImpl extends ServiceImpl<StaffEntrypostApproveMapper, StaffEntrypostApprove> implements StaffEntrypostApproveService {

    @Override
    public Page<EntryApproveDTO> findApprovePage(Page<EntryApproveDTO> page, String orgId, String searchText,String status) {
        Page<EntryApproveDTO> pageResult = this.baseMapper.findApprovePage(page, orgId, searchText, status);
        return pageResult;
    }

    @Override
    public EntryApproveFormDTO findApproveDetails(Long id) {
        EntryApproveFormDTO result = this.baseMapper.findApproveDetails(id);
        return result;
    }
}
