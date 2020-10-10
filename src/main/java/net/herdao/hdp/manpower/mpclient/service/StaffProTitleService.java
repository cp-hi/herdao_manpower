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
import net.herdao.hdp.manpower.mpclient.entity.Post;
import net.herdao.hdp.manpower.mpclient.entity.StaffProTitle;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 员工职称
 *
 * @author andy
 * @date 2020-10-10 16:37:15
 */
public interface StaffProTitleService extends IService<StaffProTitle> {
    /**
     * 员工职称分页
     * @param page 分页对象
     * @param staffId 员工ID
     * @return
     */
    Page<StaffProTitle> findStaffProTitlePage(Page<StaffProTitle> page,String staffId);


    /**
     * 新增或修改员工职称
     * @param entity
     * @return
     */
    @Override
    boolean saveOrUpdate(StaffProTitle entity);

}
