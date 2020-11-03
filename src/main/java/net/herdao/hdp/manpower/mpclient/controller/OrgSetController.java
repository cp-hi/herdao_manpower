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
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: hdp
 *
 */

package net.herdao.hdp.manpower.mpclient.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.common.core.constant.CacheConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.service.SysSequenceService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author yangrr
 * @since 2019-03-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orgset")
@Api(value = "orgset", tags = "组织设置")
public class OrgSetController {

	private final SysDictItemService sysDictItemService;

	private final SysSequenceService sysSequenceService;

	/**
	 * 通过字典类型查找字典
	 * @param type 类型
	 * @return 同类型字典
	 */
	@ApiOperation(value = "组织设置列表", notes = "组织设置列表")
	@GetMapping("/type/{type}")
	public R getDictByType(@PathVariable String type) {

		List<SysDictItem> list = sysDictItemService.list(Wrappers.<SysDictItem>query().lambda()
				.eq(SysDictItem::getType, type)
				.orderByAsc(SysDictItem::getSort)
		);
		SysDictItem entity;
		List<String> strList = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			entity = list.get(i);
			if("0".equals(entity.getDelFlag())){
				strList.add(entity.getLabel());
			}
		}
		String value = String.join("、", strList);
		Integer dictId = null;
		if(list!=null&&list.size()!=0){
			dictId = list.get(0).getDictId();
		}

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("value", value);
		map.put("type", type);
		map.put("dictId", dictId);
		return R.ok(map);
	}

	/**
	 * 通过id查询字典项
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询字典项", notes = "通过id查询字典项")
	@GetMapping("/item/{id}")
	public R getDictItemById(@PathVariable("id") Integer id) {
		return R.ok(sysDictItemService.getById(id));
	}

	/**
	 * 新增字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@ApiOperation(value = "新增字典项", notes = "新增字典项")
	@SysLog("新增字典项")
	@PostMapping("/item")
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R save(@RequestBody SysDictItem sysDictItem) {
		long code = sysSequenceService.getNext("org_set");
		sysDictItem.setSort((int)code);
		sysDictItem.setValue(code+"");
		return R.ok(sysDictItemService.save(sysDictItem));
	}

	/**
	 * 修改字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@ApiOperation(value = "修改字典项", notes = "修改字典项")
	@SysLog("修改字典项")
	@PutMapping("/item")
	public R updateById(@RequestBody SysDictItem sysDictItem) {
		return sysDictItemService.updateDictItem(sysDictItem);
	}

	/**
	 * 通过id删除字典项
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "删除字典项", notes = "删除字典项")
	@SysLog("删除字典项")
	@DeleteMapping("/item/{id}")
	public R removeDictItemById(@PathVariable Integer id) {
		return sysDictItemService.removeDictItem(id);
	}

	@ApiOperation(value = "重排序", notes = "批量修改字典项")
	@SysLog("批量修改字典项")
	@PostMapping("/itemList")
	public R updateBatch(@RequestBody List<SysDictItem> list) {
		boolean isSuccess = false;
		if(list!=null && list.size()!=0){
			isSuccess = sysDictItemService.updateBatchById(list);
		}
		return R.ok(isSuccess);
	}

}
