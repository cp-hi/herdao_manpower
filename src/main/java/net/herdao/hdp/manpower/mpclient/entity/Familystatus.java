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

package net.herdao.hdp.manpower.mpclient.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.time.LocalDateTime;

/**
 * 员工家庭成员
 *
 * @author andy
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
    @ExcelIgnore
    private Long id;
    /**
     * 
     */
    @ApiModelProperty(value="oid")
    @ExcelIgnore
    private String oid;
    /**
     * 姓名
     */
    @ExcelIgnore
    @ApiModelProperty(value="姓名")
    private String name;
    /**
     * 关系
     */
    @ExcelIgnore
    @ApiModelProperty(value="关系")
    private String relations;
    /**
     * 年龄
     */
    @ExcelIgnore
    @ApiModelProperty(value="年龄")
    private Integer age;
    /**
     * 职业
     */
    @ExcelIgnore
    @ApiModelProperty(value="职业")
    private String professional;
    /**
     * 工作单位/就读学校
     */
    @ExcelIgnore
    @ApiModelProperty(value="工作单位/就读学校")
    private String workUnit;
    /**
     * 新建用户
     */
    @ExcelIgnore
    @ApiModelProperty(value="新建用户")
    private String creatorCode;
    /**
     * 新建时间
     */
    @ExcelIgnore
    @ApiModelProperty(value="新建时间")
    private LocalDateTime createdTime;
    /**
     * 最后修改人
     */
    @ExcelIgnore
    @ApiModelProperty(value="最后修改人")
    private String modifierCode;
    /**
     * 最后修改时间
     */
    @ExcelIgnore
    @ApiModelProperty(value="最后修改时间")
    private LocalDateTime modifiedTime;
    /**
     * 人员外键
     */
    @ExcelIgnore
    @ApiModelProperty(value="人员外键")
    private String staffOid;
    /**
     * 人员外键
     */
    @ExcelIgnore
    @ApiModelProperty(value="人员外键")
    private Long staffId;
    /**
     * 出生日期
     */
    @ExcelIgnore
    @ApiModelProperty(value="出生日期")
    private LocalDateTime birthday;
    /**
     * 租户ID
     */
    @ExcelIgnore
    @ApiModelProperty(value="租户ID")
    private Long tenantId;

    /**
     * 预留字段1
     */
    @ExcelIgnore
    @ApiModelProperty(value="预留字段1")
    private String field1;

    /**
     * 预留字段2
     */
    @ExcelIgnore
    @ApiModelProperty(value="预留字段2")
    private String field2;

    /**
     * 预留字段3
     */
    @ExcelIgnore
    @ApiModelProperty(value="预留字段3")
    private String field3;

    /**
     * 预留字段4
     */
    @ExcelIgnore
    @ApiModelProperty(value="预留字段4")
    private String field4;

    /**
     * 预留字段5
     */
    @ExcelIgnore
    @ApiModelProperty(value="预留字段5")
    private String field5;

    /**
     * 是否删除
     */
    @ExcelIgnore
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;

    /**
     * 所在地址
     */
    @ExcelIgnore
    @ApiModelProperty(value="所在地址")
    private String address;

    /**
     * 修改人(操作人姓名)
     */
    @ExcelIgnore
    @ApiModelProperty(value="修改人(操作人姓名)")
    private String modifierName;

    /**
     * 员工姓名
     */
    @ExcelIgnore
    @ApiModelProperty(value="员工姓名")
    @TableField(exist = false)
    private String staffName;

    /**
     * 员工工号
     */
    @ExcelIgnore
    @ApiModelProperty(value="员工工号")
    @TableField(exist = false)
    private String staffCode;
 }
