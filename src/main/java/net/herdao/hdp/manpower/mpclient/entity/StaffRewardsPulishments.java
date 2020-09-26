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
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
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
 * 员工奖惩
 *
 * @author andy
 * @date 2020-09-25 16:26:20
 */
@Data
@TableName("mp_staff_rewards_pulishments")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工奖惩")
@ContentRowHeight(30)
@ColumnWidth(20)
public class StaffRewardsPulishments extends BaseModel<StaffRewardsPulishments> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    @ExcelIgnore
    @ExcelProperty("ID")
    private Long id;

    /**
     * 奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择
     */
    @ExcelProperty(value = "奖惩类别", index = 0)
    @ApiModelProperty(value="奖惩类别 通报表扬；年度优秀员工奖；特殊贡献奖。下拉框选择")
    private String type;

    /**
     * 奖惩内容
     */
    @ExcelProperty(value = "奖惩内容", index = 1)
    @ApiModelProperty(value="奖惩内容")
    private String content;

    /**
     * 奖惩金额
     */
    @ExcelProperty(value = "奖惩内容", index = 2)
    @ApiModelProperty(value="奖惩内容")
    private String amount;

    /**
     * 奖惩原因
     */
    @ExcelProperty(value = "奖惩原因", index = 3)
    @ApiModelProperty(value="奖惩原因")
    private String reason;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 4)
    @ApiModelProperty(value="备注")
    private String remarks;

    /**
     * 新建用户
     */
    @ExcelProperty(value = "新建用户", index = 5)
    @ApiModelProperty(value="新建用户")
    private String creatorCode;

    /**
     * 新建时间
     */
    @ExcelProperty(value = "新建时间", index = 6)
    @ApiModelProperty(value="新建时间")
    @ExcelIgnore
    private LocalDateTime createdTime;

    /**
     * 最后修改人
     */
    @ExcelProperty(value = "最后修改人", index = 7)
    @ApiModelProperty(value="最后修改人")
    private String modifierCode;

    /**
     * 最后修改时间
     */
    @ExcelProperty(value = "最后修改时间", index = 8)
    @ApiModelProperty(value="最后修改时间")
    @ExcelIgnore
    private LocalDateTime modifiedTime;

    /**
     * 租户ID
     */
    @ExcelIgnore
    @ApiModelProperty(value="租户ID")
    @ExcelProperty("tenantId")
    private Long tenantId;

    /**
     * 预留字段1
     */
    @ExcelIgnore
    @ApiModelProperty(value="预留字段1")
    @ExcelProperty("field1")
    private String field1;

    /**
     * 预留字段2
     */
    @ApiModelProperty(value="预留字段2")
    @ExcelIgnore
    @ExcelProperty("field2")
    private String field2;

    /**
     * 预留字段3
     */
    @ExcelIgnore
    @ApiModelProperty(value="预留字段3")
    @ExcelProperty("field3")
    private String field3;

    /**
     * 预留字段4
     */
    @ExcelIgnore
    @ExcelProperty("field4")
    @ApiModelProperty(value="预留字段4")
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
    @ExcelProperty("delFlag")
    @ApiModelProperty(value="是否删除")
    private Boolean delFlag;
}
