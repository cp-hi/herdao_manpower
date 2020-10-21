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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 员工合同签订列表list DTO
 *
 * @author liang
 * @date 2020-09-27 09:15:28
 */
@Data
@ApiModel(value = "员工合同签订列表list DTO")
public class StaffcontractDTO extends BaseModel<StaffcontractDTO> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value="员工姓名")
    @ExcelProperty(value = "员工姓名")
    private String staffName;

    /**
     * 员工工号
     */
    @ApiModelProperty(value="员工工号")
    @ExcelProperty(value = "员工工号")
    private String staffCode;


    /**
     * 劳动合同起始日期
     */
    @ApiModelProperty(value="劳动合同起始日期")
    @ExcelProperty(value = "劳动合同起始日期")
    private String startDate;

    /**
     * 劳动合同结束日期
     */
    @ApiModelProperty(value="劳动合同结束日期")
    @ExcelProperty(value = "劳动合同结束日期")
    private String endDate;

    /**
     * 合同签订主体
     */
    @ApiModelProperty(value="合同签订主体")
    @ExcelProperty(value = "合同签订主体")
    private String company;

    /**
     * 合同编号
     */
    @ApiModelProperty(value="合同编号")
    @ExcelProperty(value = "合同编号")
    private Long contractId;

    /**
     * 合同是否生效
     */
    @ApiModelProperty(value="合同是否生效")
    @ExcelProperty(value = "合同是否生效")
    private String enableStatus;

    /**
     * 合同期限类型
     */
    @ApiModelProperty(value="合同期限类型")
    @ExcelProperty(value = "合同期限类型")
    private String contractType;

    /**
     * 合同期限(月)
     */
    @ApiModelProperty(value="合同期限")
    @ExcelProperty(value = "合同期限")
    private String contractPeiod;

    /**
     * 试用期月数
     */
    @ApiModelProperty(value="试用期")
    @ExcelProperty(value = "试用期")
    private BigDecimal probationMonth;

    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间")
    @ExcelProperty(value = "操作时间")
    private String modifiedTime;

    /**
     * 操作人
     */
    @ApiModelProperty(value="操作人")
    @ExcelProperty(value = "操作人")
    private String modifierName;

    /**
     * 最近更新情况
     */
    @ApiModelProperty(value="最近更新情况")
    @ExcelProperty(value = "最近更新情况")
    private String updateDesc;

}
