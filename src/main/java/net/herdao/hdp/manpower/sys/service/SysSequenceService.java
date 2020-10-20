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

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.sys.entity.SysSequence;

/**
 * 序列表
 *
 * @author yangrr
 * @date 2020-10-20 11:37:09
 */
public interface SysSequenceService extends IService<SysSequence> {

    Long getNext(String seqCode);

}
