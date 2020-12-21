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

package net.herdao.hdp.manpower.mpclient.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 注册公司VO
 *
 * @author Andy
 * @date 2020-09-15 17:10:12
 */
@Data
@TableName("mp_company")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "注册公司VO")
public class CompanyVO extends BaseEntity<CompanyVO> {

    /**
     * 公司编码
     */
    @ApiModelProperty(value="公司名称")
    private String label;

    /**
     * 公司ID
     */
    @ApiModelProperty(value="公司ID")
    private String value;

}
