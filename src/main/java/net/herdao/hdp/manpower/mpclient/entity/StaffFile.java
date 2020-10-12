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
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工附件表
 *
 * @author andy
 * @date 2020-09-30 10:39:45
 */
@Data
@TableName("mp_staff_file")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工附件表")
public class StaffFile extends BaseEntity<StaffFile> {
private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value="ID")
    private Long id;
    /**
     * 附件名
     */
    @ApiModelProperty(value="附件名")
    private String name;
    /**
     * 员工外键
     */
    @ApiModelProperty(value="员工外键")
    private Long staffId;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 新建用户

     */
    @ApiModelProperty(value="新建用户")
    private Long creatorId;
    /**
     * 新建时间

     */
    @ApiModelProperty(value="新建时间 ")
    private Date createdTime;
    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    private Long modifierId;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    private Date modifiedTime;
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
     * 员工附件2级分类ID （关联员工二级分类表）
     */
    @ApiModelProperty(value="员工附件2级分类ID （关联员工二级分类表）")
    private Long secondTypeId;

    /**
     * 上传ID(调用平台上传功能后返回的ID)
     */
    @ApiModelProperty(value="上传ID(调用平台上传功能后返回的ID)")
    private String fileId;

    /**
     * 文件类型
     */
    @ApiModelProperty(value="文件类型")
    private String fileType;
}
