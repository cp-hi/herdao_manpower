package net.hedao.hdp.mpclient.modules.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.hedao.hdp.mpclient.modules.demo.entity.Demo;
import net.hedao.hdp.mpclient.modules.demo.mapper.DemoMapper;
import net.hedao.hdp.mpclient.modules.demo.service.DemoService;
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
