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

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.Staffcontract;
import net.herdao.hdp.manpower.mpclient.entity.Staffeducation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 员工合同签订
 *
 * @author liang
 * @date 2020-09-27 09:15:28
 */
public interface StaffcontractService extends IService<Staffcontract> {
    /**
     * 员工合同签订分页
     * @param page 分页对象
     * @param orgId
     * @param staffName
     * @param staffCode
     * @return
     */
    Page<Staffcontract> findStaffContractPage(Page<Staffcontract> page, @Param("orgId") String orgId, @Param("staffName") String staffName, @Param("staffCode") String staffCode);

    /**
     * 员工合同签订
     * @param orgId
     * @param staffName
     * @param staffCode
     * @return
     */
    List<Staffcontract> findStaffContract(@Param("orgId") String orgId, @Param("staffName") String staffName, @Param("staffCode") String staffCode);

    /**
     * 新增员工合同签订
     * @param staffcontract
     * @return
     */
    Boolean saveContract(@RequestBody Staffcontract staffcontract);

    /**
     * 更新员工合同签订
     * @param staffcontract
     * @return
     */
    boolean UpateContract(@RequestBody Staffcontract staffcontract);
}
