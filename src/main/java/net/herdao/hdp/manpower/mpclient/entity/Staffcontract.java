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
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 员工合同签订
 *
 * @author liang
 * @date 2020-09-27 09:15:28
 */
@Data
@TableName("mp_staffcontract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工合同签订")
public class Staffcontract extends BaseModel<Staffcontract> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="id")
    @ExcelIgnore
    private Long id;

    /**
     * 员工合同管理OID(旧平台)
     */
    @ApiModelProperty(value="员工合同管理OID(旧平台)")
    @ExcelIgnore
    private String oid;

    /**
     * 合同开始日期
     */
    @ApiModelProperty(value="合同开始日期")
    @ExcelIgnore
    private LocalDateTime startDate;

    /**
     * 合同结束日期
     */
    @ApiModelProperty(value="合同结束日期")
    @ExcelIgnore
    private LocalDateTime endDate;

    /**
     * 劳动合同签订主体
     */
    @ExcelProperty(value = "劳动合同签订主体",index = 0)
    @ApiModelProperty(value="劳动合同签订主体")
    private String companyCode;

    /**
     * 合同编号(旧平台)
     */
    @ExcelIgnore
    @ApiModelProperty(value="合同编号(旧平台)")
    private String contractOid;

    /**
     * 合同编号
     */
    @ApiModelProperty(value="合同编号")
    @ExcelProperty(value = "合同编号")
    private Long contractId;

    /**
     * 试用期月数
     */
    @ApiModelProperty(value="试用期月数")
    @ExcelProperty(value = "试用期月数")
    private BigDecimal probationMonth;

    /**
     * 是否当前生效合同 （合同是否生效）
     */
    @ApiModelProperty(value="是否当前生效合同 （合同是否生效）")
    @ExcelProperty(value = "合同是否生效")
    private Boolean newest;

    /**
     * 合同期限类型
     */
    @ApiModelProperty(value="合同期限类型")
    @ExcelProperty(value = "合同期限类型")
    private String contractType;

    /**
     * 合同期限(月)
     */
    @ApiModelProperty(value="合同期限(月)")
    @ExcelProperty(value = "合同期限(月)")
    private String contractPeiod;

    /**
     * 违约金
     */
    @ApiModelProperty(value="违约金")
    @ExcelIgnore
    private BigDecimal liquidatedDamages;

    /**
     * 经济补偿金
     */
    @ApiModelProperty(value="经济补偿金")
    @ExcelIgnore
    private BigDecimal economicCompensation;

    /**
     * 解除合同原因
     */
    @ExcelIgnore
    @ApiModelProperty(value="解除合同原因")
    private String removeContract;
    /**
     * 解除劳动合同证明编号(旧平台)
     */
    @ApiModelProperty(value="解除劳动合同证明编号(旧平台)")
    @ExcelIgnore
    private String removeOid;
    /**
     * 员工外键(旧平台)
     */
    @ApiModelProperty(value="员工外键(旧平台)")
    @ExcelIgnore
    private String satffOid;

    /**
     * 解除劳动合同证明编号
     */
    @ApiModelProperty(value="解除劳动合同证明编号")
    @ExcelIgnore
    private Long removeId;

    /**
     * 员工外键
     */
    @ApiModelProperty(value="员工外键")
    @ExcelIgnore
    private Long satffId;

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
    @ExcelIgnore
    @ApiModelProperty(value="预留字段5")
    private String field5;

    /**
     * 是否删除
     */
    @ExcelIgnore
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;
}
