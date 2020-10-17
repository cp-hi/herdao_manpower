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

package net.herdao.hdp.manpower.mpclient.dto.staff;

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
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
@Data
@ApiModel(value = "员工培训DTO")
public class StafftrainDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 新平台新增
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名")
    private String staffName;

    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号")
    private String staffCode;

    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间")
    @ExcelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value="结束时间")
    @ExcelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    /**
     * 培训时长
     */
    @ApiModelProperty(value="培训时长")
    @ExcelProperty(value = "培训时长")
    private String trainPeriod;

    /**
     * 培训总学时
     */
    @ApiModelProperty(value="培训总学时")
    @ExcelProperty(value = "培训总学时")
    private String trainLearnPeriod;

    /**
     * 培训总学分
     */
    @ApiModelProperty(value="培训总学分")
    @ExcelProperty(value = "培训总学分")
    private String trainLearnScore;

    /**
     * 培训类型 内部培训；外部培训 下拉选择
     */
    @ApiModelProperty(value="培训类型")
    @ExcelProperty(value = "培训类型")
    private String trainType;

    /**
     * 培训单位
     */
    @ApiModelProperty(value="培训单位")
    @ExcelProperty(value = "培训单位")
    private String trainCompany;

    /**
     * 培训成绩
     */
    @ApiModelProperty(value="培训成绩")
    @ExcelProperty(value = "培训成绩")
    private String score;


    /**
     * 证书名称
     */
    @ApiModelProperty(value="证书名称")
    @ExcelProperty(value = "证书名称")
    private String certificateName;

    /**
     * 证书编号
     */
    @ApiModelProperty(value="证书编号")
    @ExcelProperty(value = "证书编号")
    private String certificateCode;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    @ExcelProperty(value = "备注")
    private String remarks;


    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="操作人")
    @ExcelProperty(value = "操作人")
    private Date modifierName;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="操作时间")
    @ExcelProperty(value = "操作时间")
    private Date modifiedTime;



}
