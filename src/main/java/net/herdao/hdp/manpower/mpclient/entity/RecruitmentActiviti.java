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

package net.herdao.hdp.manpower.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才活动表
 *
 * @author Andy
 * @date 2020-12-02 20:12:40
 */
@Data
@TableName("mp_recruitment_activiti")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "人才活动表")
public class RecruitmentActiviti extends Model<RecruitmentActiviti> {
private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    @ApiModelProperty(value="主键ID")
    private Long id;
    /**
     * 活动开始时间
     */
    @ApiModelProperty(value="活动开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    /**
     * 活动结束时间
     */
    @ApiModelProperty(value="活动结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    /**
     * 活动内容
     */
    @ApiModelProperty(value="活动内容")
    private String content;
    /**
     * 成绩/成果
     */
    @ApiModelProperty(value="成绩/成果")
    private String result;
    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;
    /**
     * 预留字段1
     */
    @ApiModelProperty(value="预留字段1")
    private String field1;
    /**
     * 预留字段2
     */
    @ApiModelProperty(value="预留字段2")
    private String field2;
    /**
     * 预留字段3
     */
    @ApiModelProperty(value="预留字段3")
    private String field3;
    /**
     * 预留字段4
     */
    @ApiModelProperty(value="预留字段4")
    private String field4;
    /**
     * 预留字段5
     */
    @ApiModelProperty(value="预留字段5")
    private String field5;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value="逻辑删除")
    private Boolean delFlag;
    /**
     * 新建人工号
     */
    @ApiModelProperty(value="新建人工号")
    private String creatorCode;
    /**
     * 新建人
     */
    @ApiModelProperty(value="新建人")
    private String creatorName;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime creatorTime;
    /**
     * 修改人工号
     */
    @ApiModelProperty(value="修改人工号")
    private String modifierCode;
    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人")
    private String modifierName;
    /**
     * 修改人时间
     */
    @ApiModelProperty(value="修改人时间")
    private LocalDateTime modifierTime;
}
