package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.common.collect.Lists;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EntityService<T> extends IService<T> {

    /**
     * 保存实体并自动添加日志
     *
     * @param t
     * @return
     */
    @OperationEntity(clazz = Class.class)
    default boolean saveEntity(T t) {
        return this.saveOrUpdate(t);
    }

    /**
     * 保存核验
     *
     * @param t
     */
    default void saveVerify(T t) {
    }

    /**
     * 导入校验
     *
     * @param t
     */
    default void importVerify(T t, int type) {
    }


    //TODO 添加操作日志

    /**
     * 批量保存 可以新增/修改
     *
     * @param dataList
     * @param batchCount
     */
    @Transactional(rollbackFor = Exception.class)
    default void saveList(List<T> dataList, Integer batchCount) {
        if (0 >= batchCount) batchCount = 50;
        List<List<T>> batch = Lists.partition(dataList, batchCount);
        for (List<T> tmp : batch) this.saveOrUpdateBatch(tmp);
        dataList.clear();
    }
}
