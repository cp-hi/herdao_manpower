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
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 人才工作经历表
 *
 * @author Andy
 * @date 2020-11-26 09:18:33
 */
@Data
@TableName("mp_recruitment_workexperience")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "人才工作经历表")
public class RecruitmentWorkexperience extends Model<RecruitmentWorkexperience> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * 人才id
     */
    @ApiModelProperty(value="人才id")
    private Long recruitmentId;
    /**
     * 人才oid
     */
    @ApiModelProperty(value="人才oid")
    private String recruitmentOid;
    /**
     * 开始日期
     */
    @ApiModelProperty(value="开始日期")
    private LocalDateTime period;
    /**
     * 结束日期
     */
    @ApiModelProperty(value="结束日期")
    private LocalDateTime todate;
    /**
     * 单位名称
     */
    @ApiModelProperty(value="单位名称")
    private String companyName;
    /**
     * 岗位名称
     */
    @ApiModelProperty(value="岗位名称")
    private String post;
    /**
     * 现金收入(单位：万元)
     */
    @ApiModelProperty(value="现金收入(单位：万元)")
    private BigDecimal cashIncome;
    /**
     * 下属人数(单位：人)
     */
    @ApiModelProperty(value="下属人数(单位：人)")
    private BigDecimal subordinate;
    /**
     * 离职原因
     */
    @ApiModelProperty(value="离职原因")
    private String leaveReason;
    /**
     * 联系人
     */
    @ApiModelProperty(value="联系人")
    private String contact;
    /**
     * 联系人电话
     */
    @ApiModelProperty(value="联系人电话")
    private String contactTel;
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
    /**
     * 主要业绩
     */
    @ApiModelProperty(value="主要业绩")
    private String mainPerformance;
    /**
     * 旧人力oid
     */
    @ApiModelProperty(value="旧人力oid")
    private String oid;
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
    @TableLogic
    private Boolean delFlag;
    /**
     * 承担角色
     */
    @ApiModelProperty(value="承担角色")
    private String role;
 }
