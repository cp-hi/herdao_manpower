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
 * 员工家庭成员
 *
 * @author liang
 * @date 2020-09-23 10:53:08
 */
@Data
@TableName("mp_familystatus")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工家庭成员")
public class Familystatus extends BaseModel<Familystatus> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="主键ID")
    private Long id;
    /**
     * 
     */
    @ApiModelProperty(value="oid")
    private String oid;
    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String name;
    /**
     * 关系
     */
    @ApiModelProperty(value="关系")
    private String relations;
    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    private Integer age;
    /**
     * 职业
     */
    @ApiModelProperty(value="职业")
    private String professional;
    /**
     * 工作单位/就读学校
     */
    @ApiModelProperty(value="工作单位/就读学校")
    private String workUnit;
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
     * 人员外键
     */
    @ApiModelProperty(value="人员外键")
    private String staffOid;
    /**
     * 人员外键
     */
    @ApiModelProperty(value="人员外键")
    private Long staffId;
    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    private LocalDateTime birthday;
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
     * 所在地址
     */
    @ApiModelProperty(value="所在地址")
    private String address;

    /**
     * 修改人(操作人姓名)
     */
    @ApiModelProperty(value="修改人(操作人姓名)")
    private String modifierName;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工姓名")
    @TableField(exist = false)
    private String staffName;

    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @TableField(exist = false)
    private String staffCode;
 }
