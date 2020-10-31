package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.mapper.EntityMapper;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.utils.StringBufferUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * @ClassName EntityServiceImpl
 * @Description EntityServiceImpl
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/29 21:40
 * @Version 1.0
 */
public class EntityServiceImpl<M extends EntityMapper<T>, T> extends ServiceImpl<M, T> implements EntityService<T> {

    @Override
    public IPage page(IPage page, T t) {
        return baseMapper.page(page, t);
    }

    @Override
    public T selectIgnoreDel(Long id) {
        return baseMapper.selectIgnoreDel(id);
    }

    @Override
    public Class<T> getEntityClass() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }


    @Override
    public String getTabelName() {
        Class<T> clazz = getEntityClass();
        TableName table = clazz.getAnnotation(TableName.class);
        return table.value();
    }


    @Override
    public String getEntityName() {
        Class<T> clazz = getEntityClass();
        ApiModel apiModel = clazz.getAnnotation(ApiModel.class);
        return apiModel.value();
    }


    @Override
    public String getTableCodeField() {
        String codeField = getTabelName().toLowerCase().replaceFirst("mp_", "")
                .replaceFirst("sys_", "") + "_code";
        return codeField;
    }


    @Override
    public String getEntityCodeField() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        String entityName = StringBufferUtils.toLowerCaseFirstOne(clazz.getSimpleName());
        return entityName + "Code";
    }

    @Override
    public String generateEntityCode() throws IllegalAccessException {
        String currEntityCode = getCurrEntityCode();
        String entityCode = String.format("%06d", Integer.valueOf(currEntityCode) + 1);
        return entityCode;
    }

    @Override
    public String getCurrEntityCode() throws IllegalAccessException {
        //todo 解决逻辑删除后无法查询到的问题
        String sql = "select max(id) from " + getTabelName();
        T t = this.getOne((Wrapper<T>) new QueryWrapper().select(getTableCodeField()).inSql("id", sql));
        String entityCode = "000001";
        if (null != t) {
            Field field = AnnotationUtils.getFieldByName(t, getEntityCodeField());
            if (null == field) return entityCode;
            field.setAccessible(true);
            Object val = field.get(t);
            if (null != val)
                entityCode = val.toString();
        }
        return entityCode;
    }


    @Override
    public void setEntityCode(T t) throws IllegalAccessException {
        String entityCode = generateEntityCode();
        setEntityCode(t, entityCode);
    }

    @Override
    public void setEntityCode(T t, String entityCode) throws IllegalAccessException {
        String codeField = getEntityCodeField();
        Field field = AnnotationUtils.getFieldByName(t, codeField);
        if (null == field) return;
        field.setAccessible(true);
        field.set(t, entityCode);
    }


    @Override
    @OperationEntity(clazz = Class.class)
    public boolean saveEntity(T t) {
        return this.saveOrUpdate(t);
    }

    @OperationEntity(operation = "删除", clazz = Class.class)
    @Override
    public boolean delEntity(Serializable id) {
        return this.removeById(id);
    }


    @OperationEntity(clazz = Class.class)
    @Override
    public boolean stopEntity(Serializable id, boolean isStop) {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("is_stop", isStop);
        if (isStop) {
            updateWrapper.set("stop_date", new Date());
            updateWrapper.set("start_date", null);
        } else {
            updateWrapper.set("stop_date", null);
            updateWrapper.set("start_date", new Date());
        }
        boolean result = this.update(updateWrapper);
        return result;
    }

    @Override
    public boolean getStatus(Serializable id) throws IllegalAccessException {
        T t = this.getById(id);
        Field stop = AnnotationUtils.getFieldByName(t, "stop");
        stop.setAccessible(true);
        return (Boolean) stop.get(t);
    }

    @Override
    public void saveVerify(T t) {
    }

//    @Override
//    public void importVerify(T t, int type) {
//
//    }


    @Override
    public void importVerify(T t, Object excelObj, int type) {
        boolean add = (0 == type);
        if (add) addEntity(t, excelObj);
        else updateEntity(t, excelObj);
        //这个验证要放 最后，因为前面要给ID赋值
        this.saveVerify(t);
    }


    @Override
    public void addEntity(T t, Object excelObj) {
    }


    @Override
    public void updateEntity(T t, Object excelObj) {
    }


    @Override
    public T chkEntityExists(String field, String value, boolean need, StringBuffer buffer) {
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

    @Override
    public T chkEntityExists(String field, String value, boolean need) {
        StringBuffer buffer = new StringBuffer();
        T t = this.chkEntityExists(field, value, need, buffer);
        if (StringUtils.isNotBlank(buffer.toString()))
            throw new RuntimeException(buffer.toString());
        return t;
    }


    @Override
    public T getEntityByField(String field, String name) {
        T t = getOne(new QueryWrapper<T>().eq(field, name));
        return t;
    }

    @Override
    public void saveList(List<T> dataList, Integer batchCount) {
        if (0 >= batchCount) batchCount = 50;
        List<List<T>> batch = Lists.partition(dataList, batchCount);
        for (List<T> tmp : batch) this.saveOrUpdateBatch(tmp);
        dataList.clear();
    }
}
