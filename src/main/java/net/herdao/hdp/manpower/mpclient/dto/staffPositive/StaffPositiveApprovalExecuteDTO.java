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

package net.herdao.hdp.manpower.mpclient.dto.staffPositive;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Data
//@TableName("mp_staff_positive_approval")
@ApiModel(value = "转正审批-执行转正DTO")
public class StaffPositiveApprovalExecuteDTO {
    private static final long serialVersionUID = 1L;


    /**
     * 录用审批id
     */
    @TableId
    @ApiModelProperty(value="录用审批id")
    private Long id;

    /**
     * 转正申请id
     */
    private Long staffpositiveapplicationId;

    /**
     * 员工id
     */
    private Long userId;

    /**
     * 执行转正
     */
    @ApiModelProperty(value = "审批同意执行转正:5。审批不同意，不执行转正：6")
    private String status;


    /**
     * 是否通知
     */
    @ApiModelProperty(value = "是否通知(true:通知,false:不通知)")
    private Boolean whetherOrNotNoticeOf;

}
