/*
 *    Copyright (c) 2018-2025, hdp All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: hdp
 */

package net.herdao.hdp.mpclient.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.mpclient.entity.base.BaseModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 异动情况
 *
 * @author andy
 * @date 2020-09-24 16:00:18
 */
@Data
@TableName("mp_stafftransaction")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "异动情况")
public class Stafftransaction extends BaseModel<Stafftransaction> {
private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String oid;
    /**
     * 异动时间
     */
    @ApiModelProperty(value="异动时间")
    private LocalDateTime tranTime;
    /**
     * 异动类型 下拉数据：异动类型
     */
    @ApiModelProperty(value="异动类型 下拉数据：异动类型")
    private String tranType;
    /**
     * 异动原因
     */
    @ApiModelProperty(value="异动原因")
    private String tranReason;
    /**
     * 调往组织外键
     */
    @ApiModelProperty(value="调往组织外键")
    private String outUnitOid;
    /**
     * 调来原组织外键
     */
    @ApiModelProperty(value="调来原组织外键")
    private String comeUnitOid;
    /**
     * 调往组织外键
     */
    @ApiModelProperty(value="调往组织外键")
    private Long outUnitId;
    /**
     * 调来原组织外键
     */
    @ApiModelProperty(value="调来原组织外键")
    private Long comeUnitId;
    /**
     * 调往公司名称 异动前
     */
    @ApiModelProperty(value="调往公司名称 异动前")
    private String outUnitName;
    /**
     * 调来原公司名称 异动后
     */
    @ApiModelProperty(value="调来原公司名称 异动后")
    private String comeUnitName;
    /**
     * 调往集团名称
     */
    @ApiModelProperty(value="调往集团名称")
    private String outGroupName;
    /**
     * 调来原集团名称
     */
    @ApiModelProperty(value="调来原集团名称")
    private String comeGroupName;
    /**
     * 调往中心名称
     */
    @ApiModelProperty(value="调往中心名称")
    private String outCenterName;
    /**
     * 调来原中心名称
     */
    @ApiModelProperty(value="调来原中心名称")
    private String comeCenterName;
    /**
     * 是否最新
     */
    @ApiModelProperty(value="是否最新")
    private Boolean newest;
    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    private String creatorCode;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime createdTime;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private String modifierCode;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;
    /**
     * 所属部门外键
     */
    @ApiModelProperty(value="所属部门外键")
    private String orgDeptOid;
    /**
     * 岗位外键
     */
    @ApiModelProperty(value="岗位外键")
    private String postOid;
    /**
     * 人员外键
     */
    @ApiModelProperty(value="人员外键")
    private String staffOid;
    /**
     * 岗位外键
     */
    @ApiModelProperty(value="岗位外键")
    private Long postId;
    /**
     * 人员外键
     */
    @ApiModelProperty(value="人员外键")
    private Long staffId;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
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
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    @TableField(exist = false)
    private String createName;

 }
