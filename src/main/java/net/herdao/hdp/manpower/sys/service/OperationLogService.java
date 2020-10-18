package net.herdao.hdp.manpower.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.sys.entity.OperationLog;

import java.util.List;

public interface OperationLogService extends IService<OperationLog> {
    /**
     * 根据实体信息查询
     * @param objId
     * @param entityClass
     * @return
     */
    List<OperationLog> findByEntity(Long objId,String entityClass);

    /**
     * 根据实体信息查询分页
     * @param page
     * @param log
     * @param searchText
     * @return
     */
    Page<OperationLog> findOperationLog(Page page,OperationLog log,String searchText);

    /**
     * 根据实体信息查询
     * @param log
     * @param searchText
     * @return
     */
    List<OperationLog> findOperationLog(OperationLog log,String searchText);
}
