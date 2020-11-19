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

package net.herdao.hdp.manpower.mpclient.utils;

import lombok.experimental.UtilityClass;
import net.herdao.hdp.manpower.mpclient.dto.TreeNode;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hdp
 * @date 2020-02-09
 */
@UtilityClass
public class TreeUtil {

	/**
	 * 两层循环实现建树
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public <T extends TreeNode> List<T> build(List<T> treeNodes, Object root) {

		List<T> trees = new ArrayList<>();
		MultiValueMap<Long,T> map=new LinkedMultiValueMap();
		treeNodes.forEach(e->{
			map.add(e.getParentId(),e);
		});
		for (T treeNode : treeNodes) {

			if (root.equals(treeNode.getParentId())) {
				trees.add(treeNode);
			}
			treeNode.setChildren((List<TreeNode>) map.get(treeNode.getId()));
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<T>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getParentId())) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (treeNode.getId() == it.getParentId()) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<>());
				}
				treeNode.add(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}


}
