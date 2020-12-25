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

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveApprovalExecuteDTO;
import net.herdao.hdp.manpower.mpclient.dto.staffPositive.StaffPositiveApprovalSaveDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffPositiveApproval;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalInfoVO;
import net.herdao.hdp.manpower.mpclient.vo.staff.positive.StaffPositiveApprovalPageVO;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
public interface StaffPositiveApprovalService extends IService<StaffPositiveApproval> {

    /**
     * 分页查询
     * @return
     */
    Page<StaffPositiveApprovalPageVO> getPositiveApprovalPageInfo(Page<StaffPositiveApproval> page, Long orgId,
                                                                  String status, String searchText);

    /**
     * 通过id删除转正审批表
     */
    void deleteById(String[] inputIds);


    /**
     * 新增转正审批表
     * @return
     */
    Long insert(StaffPositiveApprovalSaveDTO staffPositiveApproval) throws Exception;

    /**
     * 获取转正详情
     */
    StaffPositiveApprovalInfoVO getStaffPositive(Long id) throws Exception;

    /**
     * 新增、编辑页-确认发起
     */
    Long affirm(Long id) throws Exception;

    /**
     * 编辑
     */
    Long updateStaffLeave(Long id, StaffPositiveApprovalSaveDTO dto) throws Exception;

    /**
     * 新增、编辑页-确认发起
     */
    Long affirmStart(Long id, StaffPositiveApprovalSaveDTO dto) throws Exception;

    /**
     * 执行转正
     * @param
     */
    Long execute(StaffPositiveApprovalExecuteDTO executeDTO);
}
