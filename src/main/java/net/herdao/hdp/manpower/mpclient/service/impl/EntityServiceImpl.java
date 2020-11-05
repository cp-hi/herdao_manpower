package net.herdao.hdp.manpower.mpclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.mpclient.mapper.EntityMapper;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public IPage getOperationLogs(IPage page, Long objId) {
        QueryWrapper<OperationLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ENTITY_CLASS", baseMapper.getEntityClass().getName());
        queryWrapper.eq("OBJ_ID", objId).orderBy(true, false, "operated_time");
        return operationLogService.page(page, queryWrapper);
    }

    /**
     * 添加操作记录
     *
     * @param operation
     * @param objId
     */
    private void addOperationLog(String operation, Long objId) {
        SysUser sysUser = SysUserUtils.getSysUser();
        OperationLog log = new OperationLog();
        log.setOperation(operation);
        log.setOperatorId(sysUser.getUserId());
        log.setOperator(sysUser.getUsername());
        log.setContent(operation + getEntityName());
        log.setEntityClass(baseMapper.getEntityClass().getName());
        log.setOperatedTime(new Date());
        log.setObjId(objId);
        operationLogService.saveOrUpdate(log);
    }


    @Override
    public Class<T> getEntityClass() {
        return baseMapper.getEntityClass();
    }

    @Override
    public IPage page(IPage page, T t) {
        return baseMapper.page(page, t);
    }

    @Override
    public Object form(Long id) {
        return baseMapper.form(id);
    }

    @Override
    public String getEntityName() {
        return baseMapper.getEntityName();
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
            if (!NumberUtils.isNumber(lastEntityCode)) lastEntityCode = "000000";
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
            addOperationLog(operation, entity.getId());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delEntity(Serializable id) {
        boolean result = this.removeById(id);
        if (result)
            addOperationLog("删除", (Long) id);
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
            addOperationLog(operation, (Long) id);
        }
        return result;
    }

    @SneakyThrows
    @Override
    public boolean getStatus(Serializable id) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("is_stop").eq("id", id);
        List<Object> objs = baseMapper.selectObjs(queryWrapper);
        if (objs.size() > 0) return (Boolean) objs.get(0);
        throw new  Exception("找不到此对象，或已被删除");
    }

    @Override
    public void saveVerify(T t) {
        StringBuffer buffer = new StringBuffer();
        this.saveVerify(t, buffer);
        this.handleErrMsg(buffer);
    }

    @Override
    public void saveVerify(T t, StringBuffer buffer) {
        Boolean result = baseMapper.checkDuplicateName(t);
        if (result) buffer.append("；该集团下已经有相同名称的" + getEntityName());
    }

    /**
     * 处理异常信息
     * @param buffer
     */
    @SneakyThrows
    protected void handleErrMsg(StringBuffer buffer) {
        String errMsg = buffer.toString();
        if (null != errMsg && errMsg.startsWith("；")) {
            errMsg = errMsg.replaceFirst("；", "");
            throw new Exception(errMsg);
        }
    }

    @SneakyThrows
    @Override
    public void importVerify(T t, Object excelObj, int type) {
        boolean add = (0 == type);
        StringBuffer buffer = new StringBuffer();
        try {
            if (add) addEntity(t, excelObj);
            else updateEntity(t, excelObj);
        } catch (Exception ex) {
            buffer.append(ex.getMessage());
        }
        //这个验证要放 最后，因为前面要给ID赋值
        this.saveVerify(t, buffer);
        if (StringUtils.isNotBlank(buffer))
            throw new Exception(buffer.toString());
    }

    @Override
    public T chkEntityExists(String name, Long groupId, boolean need, StringBuffer buffer) {
        T t = baseMapper.getEntityByName(name, groupId);
        String errMsg = "";
        if (!need && null != t)  //不需要它但它不为空
            //添加分号，因为批量导入需要所有错误信息
            errMsg = "；该集团下已存在此" + getEntityName() + "：" + name;
        if (need && null == t)  //需要它但它为空
            errMsg = "；该集团下不存在此" + getEntityName() + "：" + name;
        buffer.append(errMsg);
        return t;
    }

    @SneakyThrows
    @Override
    public T chkEntityExists(String name, Long groupId, boolean need) {
        StringBuffer buffer = new StringBuffer();
        T t = this.chkEntityExists(name, groupId, need, buffer);
        if (StringUtils.isNotBlank(buffer.toString()))
            throw new Exception(buffer.toString());
        return t;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveList(List<T> dataList, Integer batchCount) {
        //TODO 验证批次内是否有重名，以及不同集团
        if (0 >= batchCount) batchCount = 50;
        List<List<T>> batch = Lists.partition(dataList, batchCount);
        for (List<T> tmp : batch) this.saveOrUpdateBatch(tmp);
        dataList.clear();
    }
}
