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

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysDict;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.common.core.constant.CacheConstants;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.common.log.annotation.SysLog;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.service.SysDictService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author hdp
 * @since 2019-03-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orgset")
@Api(value = "orgset", tags = "组织设置")
public class OrgSetController {

	private final SysDictService sysDictService;

	private final SysDictItemService sysDictItemService;

	/**
	 * 通过字典类型查找字典
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/type/{type}")
	public R getDictByType(@PathVariable String type) {

		List<SysDictItem> list = sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getType, type));
		SysDictItem entity;
		List<String> strList = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			entity = list.get(i);
			if("0".equals(entity.getDelFlag())){
				strList.add(entity.getLabel());
			}
		}
		String value = String.join("、", strList);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("value", value);
		return R.ok(map);
	}

	/**
	 * 删除字典，并且清除字典缓存
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除字典")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dict_del')")
	public R removeById(@PathVariable Integer id) {
		return sysDictService.removeDict(id);
	}

	/**
	 * 修改字典
	 * @param sysDict 字典信息
	 * @return success/false
	 */
	@PutMapping
	@SysLog("修改字典")
	@PreAuthorize("@pms.hasPermission('sys_dict_edit')")
	public R updateById(@Valid @RequestBody SysDict sysDict) {
		return sysDictService.updateDict(sysDict);
	}

	/**
	 * 通过id查询字典项
	 * @param id id
	 * @return R
	 */
	@GetMapping("/item/{id}")
	public R getDictItemById(@PathVariable("id") Integer id) {
		return R.ok(sysDictItemService.getById(id));
	}

	/**
	 * 新增字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("新增字典项")
	@PostMapping("/item")
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R save(@RequestBody SysDictItem sysDictItem) {
		return R.ok(sysDictItemService.save(sysDictItem));
	}

	/**
	 * 修改字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
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
	@SysLog("删除字典项")
	@DeleteMapping("/item/{id}")
	public R removeDictItemById(@PathVariable Integer id) {
		return sysDictItemService.removeDictItem(id);
	}

	@SysLog("批量修改字典项")
	@PostMapping("/itemList")
	public R updateBatch(String jsonList) {
		List<SysDictItem> list= JSONObject.parseArray(jsonList,SysDictItem.class);
		boolean isSuccess = false;
		if(list!=null && list.size()!=0){
			isSuccess = sysDictItemService.updateBatchById(list);
		}
		return R.ok(isSuccess);
	}

}
