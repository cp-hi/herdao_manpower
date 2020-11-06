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
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.util.Date;

/**
 * 员工培训
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@TableName("mp_stafftrain")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工培训")
public class Stafftrain extends BaseModel<Stafftrain> {
    private static final long serialVersionUID = 1L;

    /**
     * 新平台新增
     */
    @TableId
    @ApiModelProperty(value="新平台新增")
    @ExcelIgnore
    private Long id;

    /**
     * 旧平台主键
     */
    @ApiModelProperty(value="旧平台主键")
    @ExcelIgnore
    private String staffTrainOid;

    /**
     * 培训时间
     */
    @ApiModelProperty(value="培训时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelIgnore
    private Date trainingTime;

    /**
     * 培训内容
     */
    @ApiModelProperty(value="培训内容")
    @ExcelIgnore
    private String trainingContent;

    /**
     * 所获证书
     */
    @ApiModelProperty(value="所获证书")
    @ExcelIgnore
    private String certificate;

    /**
     * 组织者
     */
    @ApiModelProperty(value="组织者")
    @ExcelIgnore
    private String organizer;

    /**
     * 员工外键(旧平台)
     */
    @ApiModelProperty(value="员工外键(旧平台)")
    @ExcelIgnore
    private String staffFk;

    /**
     * 员工外键
     */
    @ApiModelProperty(value="员工外键")
    @ExcelIgnore
    private Long staffId;

    /**
     * 新建用户

     */
    @ApiModelProperty(value="新建用户")
    @ExcelIgnore
    private String creatorCode;

    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    @ExcelIgnore
    private Date createdTime;

    /**
     * 最后修改人
     */
    @ApiModelProperty(value="最后修改人")
    @ExcelIgnore
    private String modifierCode;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间")
    @ExcelIgnore
    private Date modifiedTime;

    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    @ExcelIgnore
    private Long tenantId;

    /**
     * 预留字段1
     */
    @ApiModelProperty(value="预留字段1")
    @ExcelIgnore
    private String field1;

    /**
     * 预留字段2
     */
    @ApiModelProperty(value="预留字段2")
    @ExcelIgnore
    private String field2;

    /**
     * 预留字段3
     */
    @ApiModelProperty(value="预留字段3")
    @ExcelIgnore
    private String field3;

    /**
     * 预留字段4
     */
    @ApiModelProperty(value="预留字段4")
    @ExcelIgnore
    private String field4;

    /**
     * 预留字段5
     */
    @ApiModelProperty(value="预留字段5")
    @ExcelIgnore
    private String field5;

    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    @ExcelIgnore
    private Boolean delFlag;

    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间")
    @ExcelIgnore
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    @ExcelIgnore
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    /**
     * 培训时长
     */
    @ApiModelProperty(value="培训时长")
    @ExcelIgnore
    private String trainPeriod;

    /**
     * 培训总学时
     */
    @ApiModelProperty(value="培训总学时长")
    @ExcelIgnore
    private String trainLearnPeriod;

    /**
     * 培训总学分
     */
    @ApiModelProperty(value="培训总学分")
    @ExcelIgnore
    private String trainLearnScore;


    /**
     * 培训类型 内部培训；外部培训 下拉选择
     */
    @ApiModelProperty(value="培训类型 内部培训；外部培训 下拉选择")
    @ExcelIgnore
    private String trainType;

    /**
     * 培训单位
     */
    @ApiModelProperty(value="培训单位")
    @ExcelIgnore
    private String trainCompany;

    /**
     * 培训成绩
     */
    @ApiModelProperty(value="培训成绩")
    @ExcelIgnore
    private String score;

    /**
     * 证书名称
     */
    @ApiModelProperty(value="证书名称")
    @ExcelIgnore
    private String certificateName;

    /**
     * 证书编码
     */
    @ApiModelProperty(value="证书编码")
    @ExcelIgnore
    private String certificateCode;

    /**
     * 最近一次编辑的人的账号+工号
     */
    @ApiModelProperty(value="最近一次编辑的人的账号+工号")
    @ExcelIgnore
    private String operator;

    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间")
    @ExcelIgnore
    private Date operatorTime;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    @ExcelIgnore
    private String remarks;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID" ,hidden = true)
    private Long creatorId;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称" ,hidden = true)
    private String creatorName;

    /**
     * 修改ID
     */
    @ApiModelProperty(value = "修改ID" ,hidden = true)
    private Long modifierId;

    /**
     * 修改人(操作人姓名)
     */
    @ApiModelProperty(value="修改人(操作人姓名)")
    private String modifierName;

}
