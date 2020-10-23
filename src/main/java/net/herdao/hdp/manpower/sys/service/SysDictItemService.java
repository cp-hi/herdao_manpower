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
package net.herdao.hdp.manpower.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.common.core.util.R;
import org.apache.commons.lang3.StringUtils;

/**
 * 字典项
 *
 * @author hdp
 * @date 2019/03/19
 */
public interface SysDictItemService extends IService<SysDictItem> {

    /**
     * 删除字典项
     *
     * @param id 字典项ID
     * @return
     */
    R removeDictItem(Integer id);

    /**
     * 更新字典项
     *
     * @param item 字典项
     * @return
     */
    R updateDictItem(SysDictItem item);

    /**
     * @param type
     * @param label
     * @param buffer
     * @return
     */
    default String getDictItemValue(String type, String label, StringBuffer buffer) {
        SysDictItem dictItem = this.getOne(new QueryWrapper<SysDictItem>()
                .eq("type", type).eq("label", label));
        String errMsg = "";
        if (null == dictItem) {
			errMsg = "；不存在此字典值：" + label;
			buffer.append(errMsg.replaceFirst("：", ""));
			return null;
		}
        return dictItem.getValue();
    }

    /**
     * @param type
     * @param label
     * @return
     */
    default String getDictItemValue(String type, String label) {
        StringBuffer buffer = new StringBuffer();
        String value = this.getDictItemValue(type, label, buffer);
        if (StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());
        return value;
    }


}
