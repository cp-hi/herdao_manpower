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

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工职称
 *
 * @author andy
 * @date 2020-10-10 16:37:15
 */
@Data
@TableName("mp_staff_pro_title")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工职称")
public class StaffProTitle extends BaseModel<StaffProTitle> {
private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;
    /**
     * 职称
     */
    @ApiModelProperty(value="职称")
    private String titleName;
    /**
     * 评定单位
     */
    @ApiModelProperty(value="评定单位")
    private String assessmentUnit;
    /**
     * 职业资格
     */
    @ApiModelProperty(value="职业资格")
    private String professionalQualifications;
    /**
     * 职称证号
     */
    @ApiModelProperty(value="职称证号")
    private String titleCode;
    /**
     * 资质挂靠单位
     */
    @ApiModelProperty(value="资质挂靠单位")
    private String qualitificationUnit;
    /**
     * 发证时间
     */
    @ApiModelProperty(value="发证时间")
    private LocalDateTime certificateTime;
    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建人工号")
    private String creatorCode;
    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    private LocalDateTime createdTime;
    /**
     * 新建用户
     */
    @ApiModelProperty(value="新建人")
    private String creatorName;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value="修改人工号")
    private String modifierCode;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="修改时间")
    private LocalDateTime modifiedTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人")
    private String modifierName;
    /**
     * 人员外键
     */
    @ApiModelProperty(value="人员外键")
    private Long staffId;
    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;
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
    }
