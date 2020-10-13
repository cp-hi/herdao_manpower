package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.common.collect.Lists;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EntityService<T> extends IService<T> {

    /**
     * 保存实体并记录日志
     *
     * @param t
     * @return
     * @Author ljan
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

    @Transactional(rollbackFor = Exception.class)
    default void saveList(List<T> dataList, Integer batchCount) {
        if (0 >= batchCount) batchCount = 50;
        List<List<T>> batch = Lists.partition(dataList, batchCount);
        for (List<T> tmp : batch) this.saveBatch(tmp);
        dataList.clear();
    }
}
