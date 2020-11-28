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

package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.herdao.hdp.manpower.mpclient.dto.post.PostStaffCountDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostOrg;
import net.herdao.hdp.manpower.mpclient.vo.post.PostOrgListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位组织
 *
 * @author hsh
 * @date 2020-11-25 11:49:12
 */
@Mapper
public interface PostOrgMapper extends BaseMapper<PostOrg> {
    /**
     * 组织岗位列表查询
     * @param page
     * @param searchText
     * @return
     */
    Page<PostOrgListVO> findPostOrgPage(Page page, @Param("postOrgListVO") PostOrgListVO postOrgListVO, @Param("searchText") String searchText);

    /**
     * 组织岗位通过id查询
     * @param id
     * @return
     */
    PostOrgListVO findPostOrgById(Long id);

    /**
     * 查询集团下最大编码
     * @param groupId
     * @return
     */
    String getMaxCode(Long groupId);
    /**
     * 获取岗位员工数量
     */
    List<PostStaffCountDTO> getStaffCount();
}
