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

package net.herdao.hdp.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.mpclient.entity.Stafftrain;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 员工培训
 *
 * @author andy
 * @date 2020-09-25 09:49:45
 */
public interface StafftrainService extends IService<Stafftrain> {

    /**
     * 员工培训分页
     * @param page 分页对象
     * @param orgId
     * @param staffName
     * @param staffCode
     * @return
     */
    Page<Stafftrain> findStaffTrainPage(Page<Stafftrain> page, @Param("orgId") String orgId, @Param("staffName") String staffName, @Param("staffCode") String staffCode);

    /**
     * 员工培训新增
     * @param stafftrain
     * @return
     */
    boolean saveTrain(@RequestBody Stafftrain stafftrain);


    /**
     * 员工培训更新
     * @param stafftrain
     * @return
     */
    boolean updateTrain(@RequestBody Stafftrain stafftrain);
}
