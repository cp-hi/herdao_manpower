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

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 注册公司
 *
 * @author liang
 * @date 2020-09-15 17:10:12
 */
@Data
@TableName("mp_company")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "注册公司")
public class Company extends BaseEntity<Company> {

    /**
     * 公司编码
     */
    @ApiModelProperty(value="公司编码")
    private String companyCode;
    /**
     * 公司名称
     */
    @ApiModelProperty(value="公司名称")
    private String companyName;
    /**
     * 注册号
     */
    @ApiModelProperty(value="注册号")
    private String registrationNo;
    /**
     * 公司法人
     */
    @ApiModelProperty(value="公司法人")
    private String legal;
    /**
     * 注册资本
     */
    @ApiModelProperty(value="注册资本")
    private String registeredCapital;
    /**
     * 公司地址
     */
    @ApiModelProperty(value="公司地址")
    private String address;
    /**
     * 对应组织主键
     */
    @ApiModelProperty(value="对应组织主键")
    private Long orgId;
    /**
     * 所在城市ID
     */
    @ApiModelProperty(value="所在城市ID")
    private Long cityId;
    /**
     * 公积金标准ID
     */
    @ApiModelProperty(value="公积金标准ID")
    private Long cityFundId;
    /**
     * 目标系统ID
     */
    @ApiModelProperty(value="目标系统ID")
    private Long targetSysId;
    /**
     * 支付公司账号
     */
    @ApiModelProperty(value="支付公司账号")
    private String payAcctNo;
    /**
     * 纳税人识别码
     */
    @ApiModelProperty(value="纳税人识别码")
    private String taxerNo;
    /**
     * 工会费比例
     */
    @ApiModelProperty(value="工会费比例")
    private BigDecimal lohnkostenRate;

}
