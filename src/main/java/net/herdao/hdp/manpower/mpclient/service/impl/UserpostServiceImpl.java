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
package net.herdao.hdp.manpower.mpclient.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.entity.Userpost;
import net.herdao.hdp.manpower.mpclient.mapper.UserpostMapper;
import net.herdao.hdp.manpower.mpclient.service.UserpostService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author andy
 * @date 2020-09-15 08:57:53
 */
@Service
public class UserpostServiceImpl extends ServiceImpl<UserpostMapper, Userpost> implements UserpostService {

    @Override
    public List<Userpost> findUserPost(Userpost condition) {
        List<Userpost> list = this.baseMapper.findUserPost(condition);
        return list;
    }
}
