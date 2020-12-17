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

package net.herdao.hdp.manpower.mpmobile.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.AttachFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用附件表
 *
 * @author Andy
 * @date 2020-12-15 10:55:40
 */
@Data
@TableName("mp_attach_file")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "通过id查询通用文件表数据")
public class AttachFileInfoDTO extends Model<AttachFile> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * 附件名称
     */
    @ApiModelProperty(value="附件名称")
    private String fileName;
    /**
     * 文件类型
     */
    @ApiModelProperty(value="文件类型")
    private String fileType;
    /**
     * 附件大小
     */
    @ApiModelProperty(value="附件大小")
    private String fileSize;

    /**
     * 文件上传ID (上传ftp后成功返回的ID)
     */
    @ApiModelProperty(value="文件上传ID (上传ftp后成功返回的ID)")
    private String fileId;
    /**
     * 文件所属字典类型
     */
    @ApiModelProperty(value="文件所属字典类型")
    private String moduleType;
    /**
     * 文件所属字典value值
     */
    @ApiModelProperty(value="文件所属字典value值")
    private String moduleValue;
    /**
     * 业务表ID(例如：人才表的主键ID)
     */
    @ApiModelProperty(value="业务表ID(例如：人才表的主键ID)")
    private String bizId;
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
