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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;
import net.herdao.hdp.manpower.mpclient.utils.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工异动情况
 *
 * @author lift
 * @date 2020-10-17 16:07
 */
@Data
@ApiModel(value = "员工异动情况DTO")
public class StafftransactionDTO {
	/**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 异动时间
     */
    @ApiModelProperty(value="异动时间")
    private LocalDateTime tranTime;

    /**
     * 异动类型
     */
    @ApiModelProperty(value="异动类型")
    private String tranType;

    /**
     * 异动原因
     */
    @ApiModelProperty(value="异动原因")
    private String tranReason;


    /**
     * 异动前所在组织
     */
    @ApiModelProperty(value="异动前所在组织")
    private String outUnitName;

    /**
     * 异动后所在组织
     */
    @ApiModelProperty(value="异动后所在组织")
    private String comeUnitName;

    /**
     * 操作人
     */
    @ApiModelProperty(value="操作人")
    private String modifiedName;

    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间")
    private LocalDateTime modifiedTime;

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
