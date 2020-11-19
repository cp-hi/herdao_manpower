/*
 *
 *      Copyright (c) 2018-2025, hdp All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the herdao.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: hdp
 *
 */

package net.herdao.hdp.manpower.mpclient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hdp
 * @date 2017年11月9日23:33:45
 */
@Data
@ApiModel(value = "树形节点")
public class TreeNode {

	@ApiModelProperty(value = "当前节点id")
	protected Long id;

	@ApiModelProperty(value = "父节点id")
	protected Long parentId;

	@ApiModelProperty(value = "子节点列表")
	protected List<TreeNode> children = new ArrayList<>();

	public void add(TreeNode node) {
		children.add(node);
	}

}
