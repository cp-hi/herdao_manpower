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
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveApprovalDetailDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveApprovalSaveDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPositiveApproval;
import net.herdao.hdp.manpower.mpclient.mapper.StaffPositiveApprovalMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffPositiveApprovalService;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeUtils;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPage;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @param orgId 组织ID
     * @param searchText 关键字搜索
     * @param status 状态：1 填报中，2 审批中，3 已审批
     * @return
     */
    @Override
    public Page<StaffPositiveApprovalPageVO> getPositiveApprovalPageInfo(Page<StaffPositiveApproval> page,
                                                                         Long orgId,
                                                                         String status, String searchText) {
        Page<StaffPositiveApprovalPage> pageInfo = this.baseMapper.getPositiveApprovalPageInfo(page, orgId, status, searchText);
        return convert2PageVO(pageInfo);
    }


    /**
     * 适配数据库中获取的原始数据为传给前端的 vo
     *
     * @param page
     * @return
     */
    private Page<StaffPositiveApprovalPageVO> convert2PageVO(Page<StaffPositiveApprovalPage> page) {
        Page<StaffPositiveApprovalPageVO> pageVO = new Page<>();

        List<StaffPositiveApprovalPageVO> list = new ArrayList<>();
        for (StaffPositiveApprovalPage record : page.getRecords()) {
            StaffPositiveApprovalPageVO vo = new StaffPositiveApprovalPageVO();
            BeanUtils.copyProperties(record, vo);
            if (record.getEntryTime() != null) {
                vo.setEntryTime(LocalDateTimeUtils.convert2Long(record.getEntryTime()));
            }
            if (record.getPositiveTime() != null) {
                vo.setPositiveTime(LocalDateTimeUtils.convert2Long(record.getPositiveTime()));
            }
            if (record.getCreatorTime() != null) {
                vo.setCreatorTime(LocalDateTimeUtils.convert2Long(record.getCreatorTime()));
            }
            String updatedAt = LocalDateTimeUtils.convert2String(record.getModifierTime());
            vo.setUpdateInfo(MessageFormat.format("{0} 于 {1} 更新", record.getModifierName(), updatedAt));
            list.add(vo);
        }

        pageVO.setTotal(page.getTotal());
        pageVO.setSize(page.getSize());
        pageVO.setPages(page.getPages());
        pageVO.setCurrent(page.getCurrent());
        pageVO.setRecords(list);
        return pageVO;
    }


    /**
     * 通过id删除转正审批表
     *
     * @param inputIds id
     * @return R
     */
    @Override
    public void deleteById(String[] inputIds) {
        this.baseMapper.deleteBatchIds(Arrays.asList(inputIds));
    }


    /**
     * 新增转正审批表
     *
     * @param dto 转正审批表
     * @return
     */
    @Override
    public Long insert(StaffPositiveApprovalSaveDTO dto) {
        StaffPositiveApproval entity = new StaffPositiveApproval();
        BeanUtils.copyProperties(dto, entity);
        entity.setEntryTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getEntryTime()));
        entity.setPositiveTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getPositiveTime()));
        entity.setCreatorTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getCreatorTime()));
        entity.setStatus(StaffChangesApproveStatusConstants.FILLING_IN);
        entity.setDelFlag(false);
        return entity.getId();
    }


    /**
     * 获取转正详情
     * @param id 主键ID
     * @return
     */
    @Override
    public StaffPositiveApprovalInfoVO getStaffPositive(Long id) {
        StaffPositiveApprovalDetailDTO entity = this.baseMapper.getPositiveApprovalById(id);
        if (entity != null) {
            StaffPositiveApprovalInfoVO vo = new StaffPositiveApprovalInfoVO();
            BeanUtils.copyProperties(entity, vo);
            BeanUtils.copyProperties(entity, vo);
            vo.setEntryTime(LocalDateTimeUtils.convert2Long(entity.getEntryTime()));
            vo.setPositiveTime(LocalDateTimeUtils.convert2Long(entity.getPositiveTime()));
            vo.setCreatorTime(LocalDateTimeUtils.convert2Long(entity.getCreatorTime()));
            return vo;

        }
        return null;
    }


    /**
     * 新增、编辑页-确认发起
     *
     * @return
     */
    @Override
    public Long affirm(Long id) throws Exception {
        StaffPositiveApproval entity = this.baseMapper.selectOne(new LambdaQueryWrapper<StaffPositiveApproval>().
                eq(StaffPositiveApproval::getId, id).
                eq(StaffPositiveApproval::getStatus, StaffChangesApproveStatusConstants.FILLING_IN));
        if (entity == null) {
            throw new Exception("该转正审批记录已发起审批，请勿重复操作");
        }
        entity.setStatus(StaffChangesApproveStatusConstants.APPROVING);
        this.baseMapper.updateById(entity);
        return id;
    }


    /**
     * 编辑
     */
    @Override
    public Long updateStaffLeave(Long id, StaffPositiveApprovalSaveDTO dto) throws Exception {
        StaffPositiveApproval entity = this.baseMapper.selectOne(new LambdaQueryWrapper<StaffPositiveApproval>().
                eq(StaffPositiveApproval::getId, id).
                eq(StaffPositiveApproval::getStatus, StaffChangesApproveStatusConstants.FILLING_IN));
        if (entity == null) {
            throw new Exception("该审批记录不可编辑");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setEntryTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getEntryTime()));
        entity.setPositiveTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getPositiveTime()));
        entity.setCreatorTime(LocalDateTimeUtils.convert2LocalDateTime(dto.getCreatorTime()));
        this.baseMapper.updateById(entity);
        return id;
    }


    /**
     * 新增、编辑页-确认发起
     *
     * @param id
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Long affirmStart(Long id, StaffPositiveApprovalSaveDTO dto) throws Exception {
        if (id != null) {
            updateStaffLeave(id, dto);
        } else {
            id = insert(dto);
        }
        return affirm(id);
    }


}
