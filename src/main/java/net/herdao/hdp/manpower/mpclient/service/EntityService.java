package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 实体操作的基础Service
 *
 * @param <T>
 * @author ljan
 * @Date
 */
public interface EntityService<T> extends IService<T> {
    /**
     * 获取实体类型
     *
     * @return
     */
    Class<T> getEntityClass();

    /**
     * 获取实体名
     *
     * @return
     */
    String getEntityName();

    IPage page(IPage page, T t);

    Object form(Long id);

    IPage getOperationLogs(IPage page, Long objId);

    Integer getEntityCode(T t, Integer count);

    /**
     * 保存实体并自动添加日志
     *
     * @param t
     * @return
     */
    boolean saveEntity(T t) throws IllegalAccessException;

    /**
     * 逻辑删除实体并自动添加日志
     *
     * @param id
     * @return
     */
    boolean delEntity(Serializable id);

    /**
     * 逻辑删除前校验
     *
     * @param id
     * @return
     */
    default void delVerify(Serializable id) {
    }

    /**
     * 停用/启用
     *
     * @param id
     * @return
     */
    boolean stopEntity(Serializable id, boolean isStop);

    /**
     * 获取状态
     *
     * @param id
     * @return
     */
    boolean getStatus(Serializable id);

    /**
     * 保存核验，有错误则抛异常
     *
     * @param t
     */
    void saveVerify(T t);

    /**
     * 保存核验，有错误则加进buffer
     *
     * @param t
     */
    void saveVerify(T t, StringBuffer buffer);
    /**
     * 是否同步平台数据开关
     *
     */
    default Boolean isSync(){return Boolean.FALSE;}

    /**
     * 保存同步平台数据
     *
     * @param t
     */
    default void saveOrUpdateSync(T t){}

    /**
     * 删除同步平台数据
     *
     */
    default Boolean deleteSync(Serializable id){return null;}

    /**
     * 批量保存或更新
     *
     */
    default void saveOrUpdateBatchSync(List<T> collection){}

    /**
     * 删除同步平台数据
     *
     */
    default Boolean stop(Serializable id,Boolean stop){return null;}

    /**
     * 导入校验
     *
     * @param t
     */
    void importVerify(T t, Object excelObj, int type);

    /**
     * 根据字段名和字段值获取字段
     * 并根据条件拼接错误信息
     *
     * @param name   实体名称
     * @param need   是否需要它存在
     * @param buffer 拼接信息的StringBuffer，
     *               这样可以实体多数据返回，
     *               使用StringBuffer主要是为了线程安全
     * @return
     */
    T chkEntityExists(String name, Long groupId, boolean need, StringBuffer buffer);

    /**
     * 根据字段名和字段值获取字段
     * 并根据条件抛异常
     *
     * @param name 实体名称
     * @param need 是否需要它存在
     * @return
     * @Author ljan
     */
    T chkEntityExists(String name, Long groupId, boolean need);


    /**
     * 批量保存 可以新增/修改
     *
     * @param dataList
     * @param importType
     */
    void saveList(List<T> dataList, Integer importType);

    String selectEntityName(Serializable id);

    List<Map<String, Object>> selectNamesByIds(List<? extends Serializable> ids);

    //region 设置实体属性

    Function<T, String> getNameMapper();

    Function<T, Long> getGroupIdMapper();

    //endregion
}
