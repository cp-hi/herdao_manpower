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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 报表模板表
 *
 * @author hsh
 * @date 2020-11-28 11:02:34
 */
@Data
@TableName("mp_report_template")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "报表模板表")
public class ReportTemplate extends Model<ReportTemplate> {
private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * 报表编码
     */
    @ApiModelProperty(value="报表编码")
    private String code;
    /**
     * 报表名称
     */
    @ApiModelProperty(value="报表名称")
    private String name;
    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String descr;
    /**
     * 模板文件路径
     */
    @ApiModelProperty(value="模板文件路径")
    private String uri;
    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;
    /**
     * 是否停用
     */
    @ApiModelProperty(value="是否停用")
    private Integer isStop;
    /**
     * 集团id
     */
    @ApiModelProperty(value="集团id")
    private Long groupId;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Long sortNo;
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
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field1;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field2;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field3;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field4;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String field5;
    }
