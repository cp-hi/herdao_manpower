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
import net.herdao.hdp.manpower.mpclient.dto.UserpostNow;
import net.herdao.hdp.manpower.mpclient.entity.Familystatus;
import net.herdao.hdp.manpower.mpclient.entity.Organization;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author andy
 * @date 2020-09-15 08:57:53
 */
public interface UserpostService extends IService<Userpost> {

    /**
     * 查询用户岗位
     * @param condition
     * @return
     */
    List<Userpost> findUserPost(Userpost condition);



      /**
     * 现任职情况分页
     * @param page 分页对象
     * @param orgId
     * @param staffName
     * @param staffCode
     * @return
     */
    Page<UserpostNow> findUserPostNowPage(Page<UserpostNow> page, @Param("orgId") String orgId, @Param("staffName") String staffName, @Param("staffCode") String staffCode);

}
