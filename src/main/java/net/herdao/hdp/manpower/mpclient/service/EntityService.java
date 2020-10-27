package net.herdao.hdp.manpower.mpclient.service;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.utils.StringBufferUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
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

    default IPage page(Page page, T t) {
        return page;
    }

    default Class<T> getEntityClass() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }

    /**
     * 获取表名
     *
     * @return
     */
    default String getTabelName() {
        Class<T> clazz = getEntityClass();
        TableName table = clazz.getAnnotation(TableName.class);
        return table.value();
    }

    /**
     * 获取实体名
     *
     * @return
     */
    default String getEntityName() {
        Class<T> clazz = getEntityClass();
        ApiModel apiModel = clazz.getAnnotation(ApiModel.class);
        return apiModel.value();
    }

    /**
     * 获取编码在表中的字段名
     *
     * @return
     */
    default String getTableCodeField() {
        String codeField = getTabelName().toLowerCase().replaceFirst("mp_", "")
                .replaceFirst("sys_", "") + "_code";
        return codeField;
    }

    /**
     * 获取编码在实体中的字段名
     *
     * @return
     */
    default String getEntityCodeField() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        String entityName = StringBufferUtils.toLowerCaseFirstOne(clazz.getSimpleName());
        return entityName + "Code";
    }

    /**
     * 生成编码
     *
     * @return
     */
    default String generateEntityCode() throws IllegalAccessException {
        //todo 解决逻辑删除后无法查询到的问题
        String sql = "select max(id) from " + getTabelName();
        T t = getOne(new QueryWrapper<T>().inSql("id", sql));
        String entityCode = "000001";
        if (null != t) {
            Field field = AnnotationUtils.getFieldByName(t, getEntityCodeField());
            field.setAccessible(true);
            Object val = field.get(t);
            if (null != val)
                entityCode = String.format("%06d", Integer.valueOf(val.toString()) + 1);
        }
        return entityCode;
    }

    /**
     * 自动设置编码
     *
     * @param t
     * @throws IllegalAccessException
     */
    default void setEntityCode(T t) throws IllegalAccessException {
        Field field = AnnotationUtils.getFieldByName(t, getEntityCodeField());
        if (null == field) return;
        String entityCode = generateEntityCode();
        field.setAccessible(true);
        field.set(t, entityCode);
    }


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
    @OperationEntity(operation = "删除", clazz = Class.class)
    default boolean delEntity(Serializable id) {
        return this.removeById(id);
    }

    /**
     * @param id
     * @return
     */
    @OperationEntity(clazz = Class.class)
    default boolean stopEntity(Serializable id, boolean isStop) throws IllegalAccessException {
        // 停用实体
        T t = this.getById(id);
        Field stop = AnnotationUtils.getFieldByName(t, "stop");
        Field stopDate = AnnotationUtils.getFieldByName(t, "stopDate");
        Field startDate = AnnotationUtils.getFieldByName(t, "startDate");
        Field modifierId = AnnotationUtils.getFieldByName(t, "modifierId");
        Field modifierName = AnnotationUtils.getFieldByName(t, "modifierName");
        Field modifiedTime = AnnotationUtils.getFieldByName(t, "modifiedTime");
        stop.setAccessible(true);
        stopDate.setAccessible(true);
        startDate.setAccessible(true);
        modifierId.setAccessible(true);
        modifierName.setAccessible(true);
        modifiedTime.setAccessible(true);

        // 获取用户并保存，在切面中实现
        SysUser user = SysUserUtils.getSysUser();
        stop.set(t, isStop);
        modifierId.set(t, user.getUserId());
        modifierName.set(t, user.getUsername());
        modifiedTime.set(t, new Date());

        if (isStop) {
            stopDate.set(t, new Date());
//            startDate.set(t, null);
        } else {
//            stopDate.set(t, null);
            startDate.set(t, new Date());
        }
        return this.updateById(t);
    }

    default boolean getStatus(Serializable id) throws IllegalAccessException {
        T t = this.getById(id);
        Field stop = AnnotationUtils.getFieldByName(t, "stop");
        stop.setAccessible(true);
        return (Boolean) stop.get(t);
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
    default void importVerify(T t, Object excelObj, int type) {
        boolean add = (0 == type);
        if (add) addEntity(t, excelObj);
        else updateEntity(t, excelObj);
        //这个验证要放 最后，因为前面要给ID赋值
        this.saveVerify(t);
    }

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
     * @param field  字段名
     * @param value  字段值
     * @param need   是否需要它存在
     * @param buffer 拼接信息的StringBuffer，
     *               这样可以实体多数据返回，
     *               使用StringBuffer主要是为了线程安全
     * @return
     */
    default T chkEntityExists(String field, String value, boolean need, StringBuffer buffer) {
        T t = this.getEntityByField(field, value);
        String entityName = getEntityName();
        String errMsg = "";
        if (!need && null != t)  //不需要它但它不为空
            //添加分号，因为批量导入需要所有错误信息
            errMsg = "；已存在此" + entityName + "：" + value;
        if (need && null == t)  //需要它但它为空
            errMsg = "；不存在此" + entityName + "：" + value;

        buffer.append(errMsg);
        return t;
    }

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
    default T chkEntityExists(String field, String value, boolean need) {
        StringBuffer buffer = new StringBuffer();
        T t = this.chkEntityExists(field, value, need, buffer);
        if (StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());
        return t;
    }


    /**
     * 根据字段名获取单个实体
     *
     * @param field
     * @param name
     * @return
     */
    default T getEntityByField(String field, String name) {
        T t = getOne(new QueryWrapper<T>().eq(field, name));
        return t;
    }

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
