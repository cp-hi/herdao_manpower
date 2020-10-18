package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.common.collect.Lists;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * 实体操作的基础Service
 *
 * @param <T>
 * @author ljan
 * @Date
 */
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
     * 逻辑删除实体并自动添加日志
     *
     * @param id
     * @return
     */
    @OperationEntity(operation = "删除",clazz = Class.class)
    default boolean delEntity(Serializable id) {
        return this.removeById(id);
    }

    /**
     * @param id
     * @return
     */
    @OperationEntity(operation = "", clazz = Class.class)
    default boolean stopEntity(Serializable id, boolean isStop) throws IllegalAccessException {
        // 停用实体
        T t = this.getById(id);
        Field stop = AnnotationUtils.getFieldByName(t, "stop");
        Field modifierId = AnnotationUtils.getFieldByName(t, "modifierId");
        Field modifierName = AnnotationUtils.getFieldByName(t, "modifierName");
        Field modifiedTime = AnnotationUtils.getFieldByName(t, "modifiedTime");
        stop.setAccessible(true);
        modifierId.setAccessible(true);
        modifierName.setAccessible(true);
        modifiedTime.setAccessible(true);

        // 获取用户并保存，在切面中实现
        SysUser user = SysUserUtils.getSysUser();
        stop.set(t, isStop);
        modifierId.set(t, user.getUserId());
        modifierName.set(t, user.getUsername());
        modifiedTime.set(t, new Date());
        return this.updateById(t);
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

    /**
     * 导入校验
     *
     * @param t
     */
    default void importVerify(Object excelObj, T t, int type) {
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
