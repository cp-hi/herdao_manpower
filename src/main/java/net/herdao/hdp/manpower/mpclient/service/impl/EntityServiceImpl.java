package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.mpclient.mapper.EntityMapper;
import net.herdao.hdp.manpower.mpclient.service.EntityService;

/**
 * @ClassName EntityServiceImpl
 * @Description EntityServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/28 14:50
 * @Version 1.0
 */
public class EntityServiceImpl<M extends EntityMapper<T>, T> extends ServiceImpl<M, T> implements EntityService<T> {

    public void test(){
        this.baseMapper.generateEntityCode(getTabelName(),getTableCodeField());
    }
}
