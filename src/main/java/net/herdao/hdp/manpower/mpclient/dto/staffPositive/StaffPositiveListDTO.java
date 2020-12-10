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

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 转正审批表
 *
 * @author cp
 * @date 2020-12-08 11:02:30
 */
@Data
//@TableName("mp_staff_positive_approval")
@ApiModel(value = "转正审批分页DTO")
public class StaffPositiveListDTO {
private static final long serialVersionUID = 1L;

    /**
     * 录用审批id
     */
    @TableId
    @ApiModelProperty(value="录用审批id")
    private Long id;

    /**
     * 入职日期
     */
    @ApiModelProperty(value="组织id")
    private LocalDateTime orgId;


    /**
     * 其他
     */
    @ApiModelProperty(value="查询文本")
    private BigDecimal searchText;
    /**
     * 状态
     */
    @ApiModelProperty(value="状态")
    @NotNull
    private String status;



}
