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
import net.herdao.hdp.mpclient.entity.Familystatus;
import net.herdao.hdp.mpclient.entity.Workexperience;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 员工工作经历
 *
 * @author andy
 * @date 2020-09-24 10:24:09
 */
public interface WorkexperienceService extends IService<Workexperience> {
    /**
     * 员工工作经历分页
     * @param page 分页对象
     * @param orgId
     * @return
     */
    Page<Workexperience> findStaffWorkPage(Page<Workexperience> page, @Param("orgId") String orgId, @Param("staffName") String staffName, @Param("staffCode") String staffCode);

    /**
     * 新增员工工作经历
     * @param workexperience
     * @return
     */
    boolean saveWork(@RequestBody Workexperience workexperience);


    /**
     * 更新员工工作经历
     * @param workexperience
     * @return
     */
    boolean updateWork(@RequestBody Workexperience workexperience);
}
