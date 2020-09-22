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

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织报表
 *
 * @author andy
 * @date 2020-09-22 10:13:05
 */
@Data
@TableName("org_report")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "组织报表")
public class OrgMinReport extends Model<OrgMinReport> {
    private static final long serialVersionUID = 1L;
    /**
     * 组织名称
     */
    @TableId
    @ApiModelProperty(value="组织名称")
    @ExcelProperty(value = "组织名称", index = 0)
    private String orgName;

    /**
     * 组织编码
     */
    @ApiModelProperty(value="组织编码")
    @ExcelProperty(value = "组织编码", index = 1)
    private String orgCode;

    /**
     * 上级组织名称
     */
    @ApiModelProperty(value="上级组织名称")
    @ExcelProperty(value = "上级组织名称", index = 2)
    private String parentName;

    /**
     * 上级组织编码
     */
    @ApiModelProperty(value="上级组织编码")
    @ExcelProperty(value = "上级组织编码", index = 3)
    private String parentCode;

    /**
     * 组织层级
     */
    @ApiModelProperty(value="组织层级")
    @ExcelProperty(value = "组织层级", index = 4)
    private String orgLevel;

    /**
     * 组织类别
     */
    @ApiModelProperty(value="组织类别")
    @ExcelProperty(value = "组织类别", index = 5)
    private String orgType;

    /**
     * 组织描述
     */
    @ApiModelProperty(value="组织描述")
    @ExcelProperty(value = "组织描述", index = 6)
    private String orgDesc;


}
