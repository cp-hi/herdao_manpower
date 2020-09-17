package net.herdao.hdp.mpclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.herdao.hdp.sys.entity.OperationLog;
import net.herdao.hdp.mpclient.mapper.OperationLogMapper;
import net.herdao.hdp.mpclient.service.OperationLogService;
import org.springframework.stereotype.Service;

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
}
