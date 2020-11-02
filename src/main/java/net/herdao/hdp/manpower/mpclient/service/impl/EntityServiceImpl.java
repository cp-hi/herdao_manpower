package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.mpclient.mapper.EntityMapper;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.utils.StringBufferUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Autowired
    OperationLogService operationLogService;

    @Override
    public List<OperationLog> getOperationLogs(Long objId) {
        return operationLogService.findByEntity(objId, baseMapper.getEntityClass().getName());
    }

    @Override
    public IPage page(IPage page, T t) {
        return baseMapper.page(page, t);
    }

//    @Override
//    public Boolean checkDuplicateName(T t) {
//        return baseMapper.checkDuplicateName(t);
//    }

    @Override
    public String getEntityName() {
        return baseMapper.getEntityName();
    }

    @Override
    public String getCurrEntityCode() throws IllegalAccessException {
        //todo 解决逻辑删除后无法查询到的问题
        String sql = "select max(id) from " + baseMapper.getTableName();
        T t = this.getOne((Wrapper<T>) new QueryWrapper().select(baseMapper.getTableCodeField()).inSql("id", sql));
        String entityCode = "000001";
        if (null != t) {
            Field field = AnnotationUtils.getFieldByName(t, baseMapper.getEntityCodeField());
            if (null == field) return entityCode;
            field.setAccessible(true);
            Object val = field.get(t);
            if (null != val)
                entityCode = val.toString();
        }
        return entityCode;
    }

    @Override
    public String generateEntityCode() throws IllegalAccessException {
        String currEntityCode = getCurrEntityCode();
        String entityCode = String.format("%06d", Integer.valueOf(currEntityCode) + 1);
        return entityCode;
    }

    @Override
    public void setEntityCode(T t) throws IllegalAccessException {
        String entityCode = generateEntityCode();
        setEntityCode(t, entityCode);
    }

    @Override
    public void setEntityCode(T t, String entityCode) throws IllegalAccessException {
        String codeField = baseMapper.getEntityCodeField();
        Field field = AnnotationUtils.getFieldByName(t, codeField);
        if (null == field) return;
        field.setAccessible(true);
        field.set(t, entityCode);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveEntity(T t) throws IllegalAccessException {
        String operation = "";
        BaseEntity entity = (BaseEntity) t;
        SysUser sysUser = SysUserUtils.getSysUser();
        if (null == entity.getId() || 0 == entity.getId()) {
            operation = "新增";
            entity.setCreatedTime(new Date());
            entity.setCreatorName(sysUser.getUsername());
            entity.setCreatorId(Long.valueOf(sysUser.getUserId()));
            String lastEntityCode = baseMapper.getLastEntityCode(t);
            String entityCode = String.format("%06d", Integer.valueOf(lastEntityCode) + 1);
            String codeField = baseMapper.getEntityCodeField();
            Field field = AnnotationUtils.getFieldByName(t, codeField);
            field.setAccessible(true);
            field.set(t, entityCode);
        } else {
            operation = "修改";
            entity.setModifiedTime(new Date());
            entity.setModifierName(sysUser.getUsername());
            entity.setModifierId(Long.valueOf(sysUser.getUserId()));
        }

        boolean result = this.saveOrUpdate(t);
        if (result)
            addOperationLog(operation, entity.getId().toString());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delEntity(Serializable id) {
        boolean result = this.removeById(id);
        if (result)
            addOperationLog("删除", (String) id);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        if (result) {
            String operation = isStop ? "停用" : "启用";
            addOperationLog(operation, (String) id);
        }
        return result;
    }


    @Override
    public boolean getStatus(Serializable id) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("is_stop").eq("id", id);
        List<Object> objs = baseMapper.selectObjs(queryWrapper);
        if (objs.size() > 0) return (Boolean) objs.get(0);
        throw new RuntimeException("找不到此对象，或已被删除");
    }

    @Override
    public void saveVerify(T t) {
        Boolean result = baseMapper.checkDuplicateName(t);
        if (result) throw new RuntimeException("名称重复");
    }

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
        String entityName = baseMapper.getEntityName();
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

    @Override
    public Object form(Long id) {
        return baseMapper.form(id);
    }


    /**
     * 添加操作记录
     *
     * @param operation
     * @param objId
     */
    private void addOperationLog(String operation, String objId) {
        SysUser sysUser = SysUserUtils.getSysUser();
        OperationLog log = new OperationLog();
        log.setOperation(operation);
        log.setOperatorId(sysUser.getUserId());
        log.setOperator(sysUser.getUsername());
        log.setContent(operation + baseMapper.getEntityName());
        log.setEntityClass(baseMapper.getEntityClass().getName());
        log.setOperatedTime(new Date());
        log.setObjId(Long.valueOf(objId));
        operationLogService.saveOrUpdate(log);
    }
}
