package com.hedao.hdp.ehrclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hedao.hdp.ehrclient.entity.Demo;
import com.hedao.hdp.ehrclient.mapper.DemoMapper;
import com.hedao.hdp.ehrclient.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * demo 表 serviceImpl
 *
 * @author liang
 * @date 2020-08-06 15:18:40
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

}
