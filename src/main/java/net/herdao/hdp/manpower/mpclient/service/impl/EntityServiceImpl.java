package net.herdao.hdp.manpower.mpclient.service.impl;

import com.alibaba.excel.annotation.ExcelProperty;
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
import net.herdao.hdp.manpower.sys.annotation.FieldValid;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public String getEntityName() {
        return baseMapper.getEntityName();
    }

    @Override
    public IPage page(IPage page, T t) {
        return baseMapper.page(page, t);
    }

    @Override
    public Object form(Long id) {
        return baseMapper.form(id);
    }

    /**
     * 保存不同集团ID下实体数据最新的编号
     */
    Map<Long, Integer> currEntityCode = new ConcurrentHashMap<>();

    @SneakyThrows
    @Override
    public Integer getEntityCode(T t, Integer count) {
        Long groupId = getGroupIdMapper().apply(t);
        Integer entityCode = null;
        //TODO 线程安全，能否锁住单个groupId下的value
        if (!currEntityCode.containsKey(groupId)) {
            Boolean groupEnable = baseMapper.checkGroupStatus(t);
            if (!groupEnable) throw new Exception("找不到该集团，或已被删除或停用");
            String lastEntityCode = baseMapper.getLastEntityCode(t);
            if (!NumberUtils.isNumber(lastEntityCode)) lastEntityCode = "000000";
            entityCode = Integer.valueOf(lastEntityCode) + count;
            currEntityCode.put(groupId, entityCode);
        } else {
            entityCode = currEntityCode.get(groupId) + count;
            currEntityCode.put(groupId, entityCode);
        }
        return entityCode;
    }

    /**
     * 生成编码
     * 这里传入种子，子类可根据需求改变长度，
     * 这里默认6位数字，不足补0
     *
     * @param seed 种子数字，用于填充0
     * @return
     */
    protected String generateEntityCode(Integer seed) {
        return String.format("%06d", seed);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveEntity(T t) throws IllegalAccessException {
        String operation = "";
        boolean result = false;
        BaseEntity entity = (BaseEntity) t;
        SysUser sysUser = SysUserUtils.getSysUser();
        if (null == entity.getId() || 0 == entity.getId()) {
            operation = "新增";
            entity.setCreatedTime(new Date());
            entity.setCreatorName(sysUser.getUsername());
            entity.setCreatorId(Long.valueOf(sysUser.getUserId()));
            Field field = AnnotationUtils.getFieldByName(t, baseMapper.getEntityCodeField());
            String entityCode = this.generateEntityCode(getEntityCode(t, 1));
            field.set(t, entityCode);
            result = this.saveOrUpdate(t);
        } else {
            operation = "修改";
            entity.setModifiedTime(new Date());
            entity.setModifierName(sysUser.getUsername());
            entity.setModifierId(Long.valueOf(sysUser.getUserId()));
            result = this.saveOrUpdate(t);
        }
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
        throw new Exception("找不到此对象，或已被删除");
    }

    @Override
    public void saveVerify(T t) {
        StringBuffer buffer = new StringBuffer();
        this.saveVerify(t, buffer);
        throwErrMsg(buffer);
    }

    @Override
    public void saveVerify(T t, StringBuffer buffer) {
        Long groupId = this.getGroupIdMapper().apply(t);
        if (null == groupId) buffer.append("；集团ID为空不能保存");
        Boolean result = baseMapper.checkDuplicateName(t);
        if (result) buffer.append("；该集团下已经有相同名称的" + getEntityName());
    }

    /**
     * 处理异常信息
     *
     * @param errBuffer 异常信息
     */
    @SneakyThrows
    protected final void throwErrMsg(StringBuffer errBuffer) {
        String errMsg = errBuffer.toString();
        if (StringUtils.isNotBlank(errMsg)
                && errMsg.startsWith("；")) {
            errMsg = errMsg.replaceFirst("；", "");
            throw new Exception(errMsg);
        }
    }

    @SneakyThrows
    protected void validExcelObj(Object excelObj) {
        Class clazz = excelObj.getClass();
        FieldValid fieldValid = (FieldValid) clazz.getAnnotation(FieldValid.class);
        if (null == fieldValid) return;
        List<String> exclude = Arrays.asList(fieldValid.exclude());
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer buffer = new StringBuffer();
        for (Field field : fields) {
            if (exclude.contains(field.getName())) continue;

            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            field.setAccessible(true);
            Object val = field.get(excelObj);
            if (null == val || StringUtils.isEmpty(val.toString()))
                buffer.append(String.format("；%s不能为空", excelProperty.value()[1]));
        }
        throwErrMsg(buffer);
    }

    @SneakyThrows
    @Override
    public void importVerify(T t, Object excelObj, int type) {
        boolean add = (0 == type);
        //this.validExcelObj(excelObj);
        StringBuffer buffer = new StringBuffer();
        if (add) addEntity(t, excelObj, buffer);
        else updateEntity(t, excelObj, buffer);
        //这个验证要放 最后，因为前面要给ID赋值
        this.saveVerify(t, buffer);
        throwErrMsg(buffer);
    }

    protected void addEntity(T t, Object excelObj, StringBuffer buffer) {
    }

    protected void updateEntity(T t, Object excelObj, StringBuffer buffer) {
    }

    @Override
    public T chkEntityExists(String name, Long groupId, boolean need, StringBuffer buffer) {
        if (StringUtils.isBlank(name)) {
            if (need) buffer.append("；" + getEntityName() + "名称不能为空");
            return null;
        }
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
        throwErrMsg(buffer);
        return t;
    }

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveList(List<T> dataList, Integer importType) {
        // 验证批次内是否有重名，以及不同集团
        // getNameMapper()作用是选名称加入集合
        List<String> names = dataList.stream().map(getNameMapper()).collect(
                //根据重复数据统计数量
                Collectors.groupingBy(Function.identity(), Collectors.counting()))
                //把数量大于1（重复）的加进来
                .entrySet().stream().filter(c -> c.getValue() > 1).collect(Collectors.toList())
                //获取map的key（名称）字段
                .stream().map(Map.Entry::getKey).collect(Collectors.toList());

        if (names.size() > 0)
            throw new Exception("此批次数据中出现重复的名称:" + StringUtils.join(names));

        SysUser sysUser = SysUserUtils.getSysUser();
        //新增时先设置编号
        if (0 == importType) {
            //根据不同集团分类
            Map<Long, List<T>> subData = dataList.stream().collect(
                    Collectors.groupingBy(getGroupIdMapper()));
            for (List<T> entities : subData.values()) {
                Integer largestEntity = getEntityCode(entities.get(0), entities.size());
                for (int i = entities.size() - 1; i >= 0; i--) {
                    T t = entities.get((entities.size() - 1) - i);
                    BaseEntity entity = (BaseEntity) t;
                    entity.setCreatedTime(new Date());
                    entity.setCreatorName(sysUser.getUsername());
                    entity.setCreatorId(Long.valueOf(sysUser.getUserId()));
                    Field field = AnnotationUtils.getFieldByName(t, baseMapper.getEntityCodeField());
                    String entityCode = this.generateEntityCode(largestEntity - i);
                    field.set(t, entityCode);
                }
            }
        } else {
            dataList.forEach(t -> {
                BaseEntity entity = (BaseEntity) t;
                entity.setModifiedTime(new Date());
                entity.setModifierName(sysUser.getUsername());
                entity.setModifierId(Long.valueOf(sysUser.getUserId()));
            });
        }
        List<List<T>> batch = Lists.partition(dataList, 100);
        for (List<T> tmp : batch) this.saveOrUpdateBatch(tmp);
        dataList.clear();
    }

    //region 批量导入时分类用 参照PostServiceImpl
    @Override
    public Function<T, String> getNameMapper() {
        throw new NotImplementedException("要使用批量保存方法请在各自的ServiceImpl类中重写此函数");
    }

    @Override
    public Function<T, Long> getGroupIdMapper() {
        throw new NotImplementedException("要使用批量保存方法请在各自的ServiceImpl类中重写此函数");
    }
    //endregion

}
