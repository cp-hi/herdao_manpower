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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.MpStaffPositiveApproval;
import net.herdao.hdp.manpower.mpclient.mapper.MpStaffPositiveApprovalMapper;
import net.herdao.hdp.manpower.mpclient.service.MpStaffPositiveApprovalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Service
public class MpStaffPositiveApprovalServiceImpl extends ServiceImpl<MpStaffPositiveApprovalMapper, MpStaffPositiveApproval> implements MpStaffPositiveApprovalService {


    @Resource
    private MpStaffPositiveApprovalMapper mpStaffPositiveApprovalMapper;

    /**
     * 分页查询
     * @param page 分页对象
     * @param mpStaffPositiveApproval 转正审批表
     * @return
     */
    @Override
    public IPage<MpStaffPositiveApproval> getPageInfo(Page<MpStaffPositiveApproval> page, MpStaffPositiveApproval mpStaffPositiveApproval) {
        LambdaQueryWrapper<MpStaffPositiveApproval> queryWrapper = new LambdaQueryWrapper<>();

        //根据入职日期和状态查询
        if (mpStaffPositiveApproval.getEntryTime() != null &&
                StringUtils.isNotEmpty(mpStaffPositiveApproval.getStatus())) {
            queryWrapper.eq(MpStaffPositiveApproval::getEntryTime, mpStaffPositiveApproval.getEntryTime()).eq(MpStaffPositiveApproval::getStatus, mpStaffPositiveApproval.getStatus());
            return mpStaffPositiveApprovalMapper.selectPage(page, queryWrapper);
        }

        //根据入职日期查询
        if (mpStaffPositiveApproval.getEntryTime() != null) {
            queryWrapper.eq(MpStaffPositiveApproval::getEntryTime, mpStaffPositiveApproval.getEntryTime());
        }

        //根据状态查询
        if (StringUtils.isNotEmpty(mpStaffPositiveApproval.getStatus())) {
            queryWrapper.eq(MpStaffPositiveApproval::getStatus, mpStaffPositiveApproval.getStatus());
        }

        return mpStaffPositiveApprovalMapper.selectPage(page, queryWrapper);

    }



    /**
     * 通过id删除转正审批表
     * @param inputIds id
     * @return R
     */
    @Override
    public void deleteById(String[] inputIds ) {
        this.baseMapper.deleteBatchIds(Arrays.asList(inputIds));
    }

}
