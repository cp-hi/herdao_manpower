/*
 *    Copyright (c) 2018-2025, herdao All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the herdao.net developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liang
 */

package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.mpclient.entity.PostOrg;
import net.herdao.hdp.manpower.mpclient.vo.post.PostOrgListVO;

/**
 * 岗位组织
 *
 * @author hsh
 * @date 2020-11-25 11:49:12
 */
public interface PostOrgService extends IService<PostOrg> {

    /**
     * 组织岗位列表查询
     * @param page
     * @param searchText
     * @return
     */
    Page<PostOrgListVO> findPostOrgPage(Page page, PostOrgListVO postOrgListVO,String searchText);

    /**
     * 组织岗位通过id查询
     * @param id
     * @return
     */
    PostOrgListVO findPostOrgById(Long id);
    /**
     * 组织岗位通过id查询
     * @param postOrg
     * @return
     */
    PostOrg saveOrUpdatePostOrg(PostOrg postOrg);

    public String getPostOrgName(Long id);

    void validityCheck(Long postOrgId, String msg) throws Exception;
}
