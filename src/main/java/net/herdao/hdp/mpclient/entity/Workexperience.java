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
 * 员工工作经历
 * @author andy
 * @date 2020-09-24 10:24:09
 */
@Data
@TableName("mp_workexperience")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工工作经历")
public class Workexperience extends BaseModel<Workexperience> {
private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String oid;
    /**
     * 起止时间
     */
    @ApiModelProperty(value="起止时间")
    private String period;
    /**
     * 单位名称
     */
    @ApiModelProperty(value="单位名称")
    private String companyName;
    /**
     * 岗位
     */
    @ApiModelProperty(value="岗位")
    private String post;
    /**
     * 单位性质
     */
    @ApiModelProperty(value="单位性质")
    private String unitNature;
    /**
     * 下属人数
     */
    @ApiModelProperty(value="下属人数")
    private Integer subordinates;
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
     * 人员外键
     */
    @ApiModelProperty(value="人员外键")
    private Integer staffOid;
    /**
     * 人员外键
     */
    @ApiModelProperty(value="人员外键")
    private Long staffId;
    /**
     * 主要业绩
     */
    @ApiModelProperty(value="主要业绩")
    private String mainPerformance;
    /**
     * 承担角色
     */
    @ApiModelProperty(value="承担角色")
    private String role;
    /**
     * 背景调查
     */
    @ApiModelProperty(value="背景调查")
    private String backgroundChecks;
    /**
     * 中心/项目
     */
    @ApiModelProperty(value="中心/项目")
    private String orgName;
    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    private LocalDateTime endDate;
    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间")
    private LocalDateTime beginDate;
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
     * modifierName
     */
    @ApiModelProperty(value="最后修改人")
    @TableField(exist = false)
    private String modifierName;
}
