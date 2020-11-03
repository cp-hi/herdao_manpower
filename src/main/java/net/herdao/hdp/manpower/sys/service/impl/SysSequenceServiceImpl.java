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
package net.herdao.hdp.manpower.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.sys.entity.SysSequence;
import net.herdao.hdp.manpower.sys.mapper.SysSequenceMapper;
import net.herdao.hdp.manpower.sys.service.SysSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 序列表
 *
 * @author yangrr
 * @date 2020-10-20 11:37:09
 */
@Service
public class SysSequenceServiceImpl extends ServiceImpl<SysSequenceMapper, SysSequence> implements SysSequenceService {

    @Override
    public Long getNext(String seqCode){
        List<SysSequence> list = this.list(new QueryWrapper<SysSequence>()
                .eq("seq_code", seqCode)
        );
        if(list!=null&&list.size()!=0){
            SysSequence entity = list.get(0);
            long current = entity.getCurrentVal();
            long add = entity.getIncrementVal();
            long next = current + add;
            entity.setCurrentVal(next);
            this.updateById(entity);
            return next;
        }else{
            return null;
        }
    }

    @Override
    public void updateSeq(String seqCode, Long current){
        List<SysSequence> list = this.list(new QueryWrapper<SysSequence>()
                .eq("seq_code", seqCode)
        );
        if(list!=null&&list.size()!=0){
            SysSequence entity = list.get(0);
            entity.setCurrentVal(current);
            this.updateById(entity);
        }
    }

}
