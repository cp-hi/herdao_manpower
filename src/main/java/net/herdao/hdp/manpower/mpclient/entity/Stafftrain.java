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

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.mpclient.common.Utils.LocalDateTimeConverter;
import net.herdao.hdp.mpclient.entity.base.BaseModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工培训
 *
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
    @ExcelIgnore
    private LocalDateTime trainingTime;

    /**
     * 培训内容
     */
    @ApiModelProperty(value="培训内容")
    private String trainingContent;

    /**
     * 所获证书
     */
    @ApiModelProperty(value="所获证书")
    private String certificate;

    /**
     * 组织者
     */
    @ApiModelProperty(value="组织者")
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
    private String creatorCode;

    /**
     * 新建时间
     */
    @ApiModelProperty(value="新建时间")
    @ExcelIgnore
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
    @ExcelIgnore
    private LocalDateTime modifiedTime;

    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    @ExcelIgnore
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
    private LocalDateTime beginTime;


    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间excel字段")
    @ExcelProperty(value = "开始时间", index = 0)
    private String beginTimeStr;


    /**
     * 结束时间
     */
    @ExcelIgnore
    @ApiModelProperty(value="结束时间")
    private LocalDateTime endTime;

    /**
     * 开始时间
     */
    @ApiModelProperty(value="结束时间excel字段")
    @ExcelProperty(value = "结束时间", index = 1)
    private String endTimeStr;

    /**
     * 培训时长
     */
    @ApiModelProperty(value="培训时长")
    @ExcelProperty(value = "培训时长", index = 3)
    private String trainPeriod;

    /**
     * 培训总学时
     */
    @ApiModelProperty(value="培训总学时")
    @ExcelProperty(value = "培训时长", index = 4)
    private String trainLearnPeriod;

    /**
     * 培训类型 内部培训；外部培训 下拉选择
     */
    @ApiModelProperty(value="培训类型 内部培训；外部培训 下拉选择")
    @ExcelProperty(value = "培训类型", index = 5)
    private String trainType;

    /**
     * 培训单位
     */
    @ApiModelProperty(value="培训单位")
    @ExcelProperty(value = "培训单位", index = 6)
    private String trainCompany;

    /**
     * 培训成绩
     */
    @ApiModelProperty(value="培训成绩")
    @ExcelProperty(value = "培训成绩", index = 7)
    private String score;

    /**
     * 证书名称
     */
    @ApiModelProperty(value="证书名称")
    @ExcelProperty(value = "证书名称", index = 8)
    private String certificateName;

    /**
     * 证书编码
     */
    @ApiModelProperty(value="证书编码")
    @ExcelProperty(value = "证书编码", index = 9)
    private String certificateCode;

    /**
     * 最近一次编辑的人的账号+工号
     */
    @ApiModelProperty(value="最近一次编辑的人的账号+工号")
    private String operator;

    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间")
    @ExcelIgnore
    private LocalDateTime operatorTime;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remarks;
}
