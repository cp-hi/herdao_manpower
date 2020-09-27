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

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门组件Vo
 *
 * @author shuling
 * @date 2020-09-24 15:01:48
 */
@Data
@ApiModel(value = "部门组件")
public class OrganizationComponentVo {

	@ApiModelProperty(value = "部门/组织id")
	private Long id;
	
	@ApiModelProperty(value = "部门/组织名称")
	private String orgName;

	@ApiModelProperty(value = "部门/组织编码")
	private String orgCode;
	
	@ApiModelProperty(value = "下级部门/组织")
	private List<OrganizationComponentVo> organizationComponents;
	
}
