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
import net.herdao.hdp.manpower.mpclient.dto.UserpostDTO;
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
    Page<UserpostDTO> findUserPostNowPage(Page<UserpostDTO> page,String orgId, String staffName, String staffCode,String staffId);

    /**
     * 现任职情况
     * @param orgId
     * @param staffName
     * @param staffCode
     * @return
     */
    List<UserpostDTO> findUserPostNow( String orgId,  String staffName,  String staffCode,String staffId);

    /**
     * 新增现任职情况
     * @param entity
     * @return
     */
    Boolean saveUserPostNow(Userpost entity);


    /**
     * 更新现任职情况
     * @param entity
     * @return
     */
    Boolean updateUserPostNow(Userpost entity);

    /**
     * 员工详情-工作情况-目前任职
     * @param orgId
     * @param staffName
     * @param staffCode
     * @param staffId
     * @return
     */
    UserpostDTO findCurrentJob( String orgId, String staffName,String staffCode, String staffId);

}
