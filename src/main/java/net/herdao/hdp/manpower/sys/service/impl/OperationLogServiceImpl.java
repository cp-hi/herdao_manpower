package net.herdao.hdp.manpower.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OperationLogServiceImpl
 * @Description OperationLogServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 16:28
 * @Version 1.0
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<BaseMapper<OperationLog>, OperationLog> implements OperationLogService {
    @Override
    public List<OperationLog> findByEntity(Long objId, String entityClass) {
        return this.baseMapper.selectList(new QueryWrapper<OperationLog>()
                .eq("obj_Id", objId).eq("ENTITY_CLASS", entityClass));
    }
}
