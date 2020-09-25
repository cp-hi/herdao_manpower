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
 * 员工教育经历
 *
 * @author andy
 * @date 2020-09-23 17:59:39
 */
@Data
@TableName("mp_staffeducation")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工教育经历")
public class Staffeducation extends BaseModel<Staffeducation> {
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
     * 起止时间
     */
    @ApiModelProperty(value="起止时间")
    private String period;
    /**
     * 学校名称
     */
    @ApiModelProperty(value="学校名称")
    private String schoolName;
    /**
     * 专业
     */
    @ApiModelProperty(value="专业")
    private String professional;
    /**
     * 学历与学位
     */
    @ApiModelProperty(value="学历与学位")
    private String educationDegree;
    /**
     * 学习形式
     */
    @ApiModelProperty(value="学习形式")
    private String learnForm;
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
    private String staffId;
    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    private LocalDateTime endDate;
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
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    private LocalDateTime beginDate;

    /**
     * modifierName
     */
    @ApiModelProperty(value="最后修改人")
    @TableField(exist = false)
    private String modifierName;

    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建用户")
    @TableField(exist = false)
    private String createName;

    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;

}
