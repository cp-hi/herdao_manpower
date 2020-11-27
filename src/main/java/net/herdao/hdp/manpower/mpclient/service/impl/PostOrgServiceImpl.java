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

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.herdao.hdp.manpower.mpclient.entity.PostOrg;
import net.herdao.hdp.manpower.mpclient.mapper.PostOrgMapper;
import net.herdao.hdp.manpower.mpclient.service.PostOrgService;
import net.herdao.hdp.manpower.mpclient.vo.post.PostOrgListVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return postOrgMapper.findPostOrgPage(page,postOrgListVO,searchText);
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
        int retryCount=10;
        if(ObjectUtil.isNull(postOrg.getId())){
            this.save(postOrg);
            do{
                postOrg.setPostCode(this.getCode(postOrg.getGroupId()));
                List<PostOrg> list = this.list(Wrappers.<PostOrg>lambdaUpdate()
                        .eq(PostOrg::getId, postOrg.getId())
                        .eq(PostOrg::getGroupId, postOrg.getGroupId())
                        .ne(PostOrg::getPostCode, postOrg.getPostCode()));
                System.out.println(this.update(postOrg, Wrappers.<PostOrg>lambdaUpdate()
                        .eq(PostOrg::getId,postOrg.getId())
                        .eq(PostOrg::getGroupId,postOrg.getGroupId())
                        .ne(PostOrg::getPostCode,postOrg.getPostCode())));
            }while (!this.update(postOrg, Wrappers.<PostOrg>lambdaUpdate()
                    .eq(PostOrg::getId,postOrg.getId())
                    .eq(PostOrg::getGroupId,postOrg.getGroupId())
                    .ne(PostOrg::getPostCode,postOrg.getPostCode()))&&retryCount-->0);
            if(retryCount<1){
                throw new RuntimeException("失败");
            }
        }else{
            this.updateById(postOrg);
        }
        return postOrg;
    }
    private String getCode(Long groupId){
        String maxCode = postOrgMapper.getMaxCode(groupId);
        String code = StringUtils.isEmpty(maxCode)?"000000":maxCode;
        return String.format("%06d",Integer.parseInt(code)+1);
    }

}
