package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
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

    Object form(Long id);

    IPage getOperationLogs(IPage page,Long objId);

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

    void saveVerify(T t,StringBuffer buffer);

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
     * @param batchCount
     */
    void saveList(List<T> dataList, Integer batchCount);

}
