package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.mpclient.entity.Demo;
import net.herdao.hdp.mpclient.mapper.DemoMapper;
import net.herdao.hdp.mpclient.service.DemoService;
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