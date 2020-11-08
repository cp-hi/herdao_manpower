package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;
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

    /**
     * 获取最新实体编号
     * @param t
     * @return
     */
//    String getLastEntityCode(T t);

    Integer getEntityCode(T t,Integer count);
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
     * 保存核验
     *
     * @param t
     */
    void saveVerify(T t);

    void saveVerify(T t, StringBuffer buffer);

    /**
     * 导入校验
     *
     * @param t
     */
    void importVerify(T t, Object excelObj, int type);

    /**
     * 新增校验
     *
     * @param t
     * @param excelObj
     */
    default void addEntity(T t, Object excelObj) {
    }

    /**
     * 编辑核验
     *
     * @param t
     * @param excelObj
     */
    default void updateEntity(T t, Object excelObj) {
    }


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

    Function<T, String> getNameMapper();

    Function<T, Long> getGroupIdMapper();
}
