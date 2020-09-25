package net.herdao.hdp.manpower.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.manpower.mpclient.mapper.OperationLogMapper;
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
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
//    @Override
//    public List<OperationLog> findByEntity(Long objId, String clazz) {
//        return baseMapper.findByEntity(objId,clazz);
//    }
}
