package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.common.collect.Lists;
import net.herdao.hdp.manpower.mpclient.dto.JobLevelDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EntityService<T> extends IService<T> {

    /**
     * 批量导入一次导入条数
     */
    Integer BATCH_COUNT = 50;

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

    /**
     * 批量保存列表
     *
     * @param dataList
     */
    default void saveList(List<T> dataList) {
        this.saveList(dataList, BATCH_COUNT);
    }

    @Transactional(rollbackFor = Exception.class)
    default void saveList(List<T> dataList, Integer batchCount) {
        if (0 >= batchCount) batchCount = BATCH_COUNT;
        List<List<T>> batch = Lists.partition(dataList, batchCount);
        for (List<T> tmp : batch) this.saveBatch(tmp);
        dataList.clear();
    }
}
