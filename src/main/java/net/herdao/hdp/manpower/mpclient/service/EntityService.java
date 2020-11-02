package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 实体操作的基础Service
 *
 * @param <T>
 * @author ljan
 * @Date
 */
public interface EntityService<T> extends IService<T> {

    IPage page(IPage page, T t);

    List<OperationLog> getOperationLogs(Long objId);

    /**
     * 获取实体名
     *
     * @return
     */
    String getEntityName();

    /**
     * 生成编码
     *
     * @return
     */
    String generateEntityCode() throws IllegalAccessException;

    String getCurrEntityCode() throws IllegalAccessException;

    /**
     * 自动设置编码
     *
     * @param t
     * @throws IllegalAccessException
     */
    void setEntityCode(T t) throws IllegalAccessException;

    void setEntityCode(T t, String code) throws IllegalAccessException;


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
     * @param id
     * @return
     */
    boolean stopEntity(Serializable id, boolean isStop);

    boolean getStatus(Serializable id);

    /**
     * 保存核验
     *
     * @param t
     */
    void saveVerify(T t);

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
    void addEntity(T t, Object excelObj);

    /**
     * 编辑核验
     *
     * @param t
     * @param excelObj
     */
    void updateEntity(T t, Object excelObj);


    /**
     * 根据字段名和字段值获取字段
     * 并根据条件拼接错误信息
     *
     * @param field  字段名
     * @param value  字段值
     * @param need   是否需要它存在
     * @param buffer 拼接信息的StringBuffer，
     *               这样可以实体多数据返回，
     *               使用StringBuffer主要是为了线程安全
     * @return
     */
    T chkEntityExists(String field, String value, boolean need, StringBuffer buffer);

    /**
     * 根据字段名和字段值获取字段
     * 并根据条件抛异常
     *
     * @param field 字段名
     * @param value 字段值
     * @param need  是否需要它存在
     * @return
     * @Author ljan
     */
    T chkEntityExists(String field, String value, boolean need);


    /**
     * 根据字段名获取单个实体
     *
     * @param field
     * @param name
     * @return
     */
    T getEntityByField(String field, String name);

    /**
     * 批量保存 可以新增/修改
     *
     * @param dataList
     * @param batchCount
     */
    @Transactional(rollbackFor = Exception.class)
    void saveList(List<T> dataList, Integer batchCount);

    Object form(Long id);

}
