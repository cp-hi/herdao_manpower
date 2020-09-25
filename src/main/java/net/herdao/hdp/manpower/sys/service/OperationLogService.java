package net.herdao.hdp.manpower.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.sys.entity.OperationLog;

import java.util.List;

public interface OperationLogService extends IService<OperationLog> {
    /**
     * 根据实体信息查询
     * @param objId
     * @param clazz
     * @return
     */
    List<OperationLog> findByEntity(Long objId,String clazz);
}