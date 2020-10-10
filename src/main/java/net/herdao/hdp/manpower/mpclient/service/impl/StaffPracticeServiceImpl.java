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
package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.dto.StaffPracticeDto;
import net.herdao.hdp.manpower.mpclient.entity.StaffPractice;
import net.herdao.hdp.manpower.mpclient.entity.StaffProfession;
import net.herdao.hdp.manpower.mpclient.mapper.StaffPracticeMapper;
import net.herdao.hdp.manpower.mpclient.service.StaffPracticeService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 员工实习记录
 * @author andy
 * @date 2020-10-09 17:51:16
 */
@Service
public class StaffPracticeServiceImpl extends ServiceImpl<StaffPracticeMapper, StaffPractice> implements StaffPracticeService {

    @Override
    @OperationEntity(operation = "保存或修改员工职称及职业资料", clazz = StaffPractice.class)
    public boolean saveOrUpdate(StaffPractice practice) {
        if (null != practice ){
            if (null != practice.getBeginDate() && null !=  practice.getEndDate()){
                String dayAndMonth = DateUtils.getDayAndMonth(DateUtils.formatDate(practice.getBeginDate(),"yyyy-MM-dd"), DateUtils.formatDate( practice.getEndDate(),"yyyy-MM-dd")  );
                practice.setPeriod(dayAndMonth);
            }
        }
        boolean status = super.saveOrUpdate(practice);
        return status;
    }

    @Override
    public StaffPracticeDto findStaffPractice(String staffId) {
        StaffPracticeDto result = this.baseMapper.findStaffPractice(staffId);
        return result;
    }
}
