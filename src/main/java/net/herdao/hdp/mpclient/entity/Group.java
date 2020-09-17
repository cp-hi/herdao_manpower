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

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 集团表
 *
 * @author yangrr
 * @date 2020-09-11 11:57:16
 */
@Data
@TableName("mp_group")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "集团表")
public class Group extends Model<Group> {
private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    @ApiModelProperty(value="主键ID")
    private Long id;
    /**
     * 集团编码
     */
    @ApiModelProperty(value="集团编码")
    private String groupCode;
    /**
     * 集团名称
     */
    @ApiModelProperty(value="集团名称")
    private String groupName;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Integer sortNo;
    /**
     * 对应组织主键
     */
    @ApiModelProperty(value="对应组织主键")
    private Long orgId;
    /**
     * 对应组织
     */
    @ApiModelProperty(value="对应组织")
    private String orgCode;
    /**
     * 集团全称(表头名称)
     */
    @ApiModelProperty(value="集团全称(表头名称)")
    private String groupFullname;
    /**
     * 租户ID
     */
    @ApiModelProperty(value="租户ID")
    private Long tenantId;
    }
