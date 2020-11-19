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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织组件Vo
 *
 * @author shuling
 * @date 2020-09-24 15:01:48
 */
@Data
@ApiModel(value = "组织组件")
public class OrganizationComponentVO {

	@ApiModelProperty(value = "组织id")
	private Long id;
	
	@ApiModelProperty(value = "组织名称")
	private String orgName;

	@ApiModelProperty(value = "组织编码")
	private String orgCode;
	
	@ApiModelProperty(value = "是否叶子节点组织， 值 true 是， 值 false 否")
	private Boolean leafNode;
	
	@ApiModelProperty(value = "父组织id")
	private Long parentId;
	
	@ApiModelProperty(value = "父组织编码")
	private String parentOrgName;

	@ApiModelProperty(value = "父组织名称")
	private String parentOrgCode;
	
	@ApiModelProperty(value = "组织名称（包含父组织名称，例如：广东省/广州市/天河区）")
	private String orgFullName;
	
}
