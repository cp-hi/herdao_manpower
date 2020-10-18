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
import net.herdao.hdp.manpower.mpclient.dto.StaffFileDTO;
import net.herdao.hdp.manpower.mpclient.entity.StaffFile;
import net.herdao.hdp.manpower.mpclient.entity.StaffSecondFileType;
import org.apache.ibatis.annotations.Param;

/**
 * 员工附件表
 *
 * @author liang
 * @date 2020-09-30 10:39:45
 */
public interface StaffFileService extends IService<StaffFile> {
    /**
     * 员工附件分页
     * @param page
     * @param entity
     * @return
     */
    Page<StaffFileDTO> findStaffFilePage(Page<StaffFileDTO> page, @Param("entity") StaffFileDTO entity);

    /**
     * 新增或修改员工附件分类
     * @param entity
     * @return
     */
     @Override
     boolean saveOrUpdate(StaffFile entity);

    /**
     * 删除员工附件
     * @param entity
     * @return
     */
    boolean delEntity(StaffFile entity);
}
