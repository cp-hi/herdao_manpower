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
package net.herdao.hdp.manpower.mpclient.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.dto.post.PostStaffCountDTO;
import net.herdao.hdp.manpower.mpclient.entity.PostOrg;
import net.herdao.hdp.manpower.mpclient.mapper.PostOrgMapper;
import net.herdao.hdp.manpower.mpclient.service.PostOrgService;
import net.herdao.hdp.manpower.mpclient.vo.post.PostOrgListVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 岗位组织
 *
 * @author hsh
 * @date 2020-11-25 11:49:12
 */
@Service
@AllArgsConstructor
public class PostOrgServiceImpl extends ServiceImpl<PostOrgMapper, PostOrg> implements PostOrgService {

        private final PostOrgMapper postOrgMapper;

    @Override
    public Page<PostOrgListVO> findPostOrgPage(Page page, PostOrgListVO postOrgListVO, String searchText) {
        Page<PostOrgListVO> postOrgPage = postOrgMapper.findPostOrgPage(page, postOrgListVO, searchText);
        Map<Long,Integer> staffCountDTOMap = new HashMap<>();
        List<PostStaffCountDTO> staffCount = postOrgMapper.getStaffCount();
        if(CollectionUtil.isNotEmpty(staffCount)){
            staffCount.forEach(e->staffCountDTOMap.put(e.getPostOrgId(),e.getStaffCount()));
        }
        postOrgPage.getRecords().forEach(e->{
            if(ObjectUtil.isNotNull(staffCountDTOMap.get(e.getId()))){
                e.setStaffCount(staffCountDTOMap.get(e.getId()));
            }else {
                e.setStaffCount(0);
            }
        });
        return postOrgPage;
    }

    /**
     * 组织岗位通过id查询
     * @param id
     * @return
     */
    @Override
    public PostOrgListVO findPostOrgById(Long id) {
        return postOrgMapper.findPostOrgById(id);
    }

    @Override
    @Transactional
    public PostOrg saveOrUpdatePostOrg(PostOrg postOrg) {
        postOrg.setPostCode(getCode(postOrg.getGroupId()));
        this.saveOrUpdate(postOrg);
        return postOrg;
    }
    private String getCode(Long groupId){
        String maxCode = postOrgMapper.getMaxCode(groupId);
        String code = StringUtils.isEmpty(maxCode)?"000000":maxCode;
        return String.format("%06d",Integer.parseInt(code)+1);
    }

}
