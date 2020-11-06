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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseModel;

/**
 * 福利类型
 *
 * @author shuling
 * @date 2020-11-06 11:27:28
 */
@Data
@TableName("mp_welfare_standards")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "福利类型")
public class WelfareStandards extends BaseModel<WelfareStandards> {
private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value="id")
    private Long id;
    /**
     * 旧人力福利类型表id
     */
    @ApiModelProperty(value="旧人力福利类型表id")
    private String welfarestandardsOid;
    /**
     * 版本
     */
    @ApiModelProperty(value="版本")
    private String version;
    /**
     * 是否启用
     */
    @ApiModelProperty(value="是否启用")
    private Boolean enabled;
    /**
     * 组织表id
     */
    @ApiModelProperty(value="组织表id")
    private Long organizationId;
    /**
     * 旧人力组织表id
     */
    @ApiModelProperty(value="旧人力组织表id")
    private String organizationOid;
    
    }
