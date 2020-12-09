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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.constant.StaffChangesApproveStatusConstants;
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveApprovalSaveDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveListDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPositiveApproval;
import net.herdao.hdp.manpower.mpclient.mapper.StaffPositiveApprovalMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffPositiveApprovalService;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPageVO;
import net.herdao.hdp.manpower.sys.utils.BeanConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Service
public class StaffPositiveApprovalServiceImpl extends ServiceImpl<StaffPositiveApprovalMapper, StaffPositiveApproval> implements StaffPositiveApprovalService {


    @Resource
    private StaffPositiveApprovalMapper staffPositiveApprovalMapper;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffPositiveApproval 转正审批表
     * @return
     */
    @Override
    public Page<StaffPositiveApprovalPageVO> getPageInfo(Page<StaffPositiveApproval> page, StaffPositiveListDTO staffPositiveApproval) {
        LambdaQueryWrapper<StaffPositiveApproval> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(StaffPositiveApproval::getDelFlag,0);
        //根据入职日期和状态查询
        if (staffPositiveApproval.getEntryTime() != null &&
                StringUtils.isNotEmpty(staffPositiveApproval.getStatus())) {
            queryWrapper.like(StaffPositiveApproval::getEntryTime, staffPositiveApproval.getEntryTime()).
                    like(StaffPositiveApproval::getStatus, staffPositiveApproval.getStatus());

            return BeanConvertUtils.
                    convertPageTo(staffPositiveApprovalMapper.selectPage(page, queryWrapper),
                            StaffPositiveApprovalPageVO::new);
        }

        //根据入职日期查询
        if (staffPositiveApproval.getEntryTime() != null) {
            queryWrapper.like(StaffPositiveApproval::getEntryTime, staffPositiveApproval.getEntryTime());
        }

        //根据状态查询
        if (StringUtils.isNotEmpty(staffPositiveApproval.getStatus())) {
            queryWrapper.like(StaffPositiveApproval::getStatus, staffPositiveApproval.getStatus());
        }

        return BeanConvertUtils.
                convertPageTo(staffPositiveApprovalMapper.selectPage(page, queryWrapper),
                        StaffPositiveApprovalPageVO::new);
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


    /**
     * 新增转正审批表
     * @param staffPositiveApproval 转正审批表
     * @return R
     */
    @Override
    public Long insert(StaffPositiveApprovalSaveDTO staffPositiveApproval) {
        StaffPositiveApproval entity = new StaffPositiveApproval();
        BeanUtils.copyProperties(staffPositiveApproval, entity);
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        entity.setDelFlag(false);
        return entity.getId();
    }

}
