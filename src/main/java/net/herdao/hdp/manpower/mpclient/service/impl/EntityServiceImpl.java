package net.herdao.hdp.manpower.mpclient.service.impl;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dadiyang.equator.Equator;
import com.github.dadiyang.equator.FieldInfo;
import com.github.dadiyang.equator.GetterBaseEquator;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.SneakyThrows;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.common.core.util.R;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.mpclient.mapper.EntityMapper;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.sys.annotation.DtoField;
import net.herdao.hdp.manpower.sys.annotation.FieldValid;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.CacheService;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import net.herdao.hdp.manpower.sys.utils.ApplicationContextBeanUtils;
import net.herdao.hdp.manpower.sys.utils.DtoConverter;
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

    @Autowired
    CacheService cacheService;

    @Override
    public IPage getOperationLogs(IPage page, Long objId) {
        return operationLogService.page(page, Wrappers.<OperationLog>query().lambda()
                .eq(OperationLog::getEntityClass, getEntityClass().getName())
                .eq(OperationLog::getObjId, objId).orderBy(true, false, OperationLog::getOperatedTime));
    }

    /**
     * 添加操作记录
     *
     * @param operation
     * @param objId
     */
    private void addOperationLog(String operation, Long objId, String objName) {
        SysUser sysUser = SysUserUtils.getSysUser();
        OperationLog log = new OperationLog();
        log.setOperation(operation);
        log.setOperatorId(sysUser.getUserId());
        log.setOperator(sysUser.getUsername());
        log.setContent(operation + "了" + getEntityName() + ": " + objName);
        log.setEntityClass(getEntityClass().getName());
        log.setOperatedTime(new Date());
        log.setObjId(objId);
        operationLogService.saveOrUpdate(log);
    }

    /**
     * 检验差异字段时要排除的字段
     * 如需加减，可以在子类中重写
     *
     * @return
     */
    protected List<String> getExcludeFieldName() {
        List<String> excludeField = AnnotationUtils.getAllFieldNames(BaseEntity.class);
        excludeField.add(baseMapper.getEntityCodeField());
        excludeField.add("isStop");
        excludeField.add("stopDate");
        excludeField.add("startDate");
        return excludeField;
    }

    /**
     * 获取变更字段内容
     * 特殊的可以留在子类中实现
     *
     * @param origin
     * @return
     */
    @SneakyThrows
    protected List<String> getDiffEntityContent(T origin, T current) {
        Equator equator = new GetterBaseEquator();
        //TODO 比较字段变化及排除不需要比较字段
        List<String> excludeField = getExcludeFieldName();
        List<FieldInfo> diff = equator.getDiffFields(origin, current).stream().filter(
                f -> !excludeField.contains(f.getFieldName())).collect(Collectors.toList());

        List<String> modifyField = new ArrayList<>();

        for (FieldInfo f : diff) {
            Field field = origin.getClass().getDeclaredField(f.getFieldName());
            ApiModelProperty property = field.getAnnotation(ApiModelProperty.class);
            DtoField dtoField = field.getAnnotation(DtoField.class);
            String tmp = "%s从“%s”调整为“%s”";
            if (null == dtoField) {
                tmp = String.format(tmp, property.value(), f.getFirstVal(), f.getSecondVal());
            } else {
                String firstVal = null, secondVal = null;
                if (EntityServiceImpl.class != dtoField.entityService()) {
                    EntityService service = ApplicationContextBeanUtils.getBean(dtoField.entityService());
                    firstVal = service.selectEntityName((Serializable) f.getFirstVal());
                    secondVal = service.selectEntityName((Serializable) f.getSecondVal());
                } else if (StringUtils.isNotBlank(dtoField.dictField())) {
                    firstVal = cacheService.getDictLabel(dtoField.dictField(), (String) f.getFirstVal());
                    secondVal = cacheService.getDictLabel(dtoField.dictField(), (String) f.getSecondVal());
                } else if (Boolean.class == field.getType()) {
                    firstVal = DtoConverter.bool2string((Boolean) f.getFirstVal(), dtoField);
                    secondVal = DtoConverter.bool2string((Boolean) f.getSecondVal(), dtoField);
                }
                tmp = String.format(tmp, property.value(), firstVal, secondVal);
            }
            modifyField.add(tmp);
        }
        return modifyField;
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
            if (!groupEnable) throw new Exception("找不到该集团，可能已经被删除或停用");
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
     * 这里默认6位数字，不足补0，可以在子类中重写
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
        boolean result = false;
        BaseEntity entity = (BaseEntity) t;
        SysUser sysUser = SysUserUtils.getSysUser();
        if (null == entity.getId() || 0 == entity.getId()) {
            entity.setCreatedTime(new Date());
            entity.setCreatorName(sysUser.getUsername());
            entity.setCreatorId(Long.valueOf(sysUser.getUserId()));
            Field field = AnnotationUtils.getFieldByName(t, baseMapper.getEntityCodeField());
            String entityCode = this.generateEntityCode(getEntityCode(t, 1));
            field.set(t, entityCode);
            result = this.saveOrUpdate(t);
            if (isSync()) {
                saveOrUpdateSync(t);
            }
            if (result)
                addOperationLog("新增", entity.getId(), getNameMapper().apply(t));
        } else {
            T origin = baseMapper.selectIgnoreDel(entity.getId());
            entity.setModifiedTime(new Date());
            entity.setModifierName(sysUser.getUsername());
            entity.setModifierId(Long.valueOf(sysUser.getUserId()));
            result = this.saveOrUpdate(t);
            if (result) {
                List<String> content = getDiffEntityContent(origin, t);
                if (content.size() > 0)
                    addOperationLog("编辑", entity.getId(), StringUtils.join(content, "；"));
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delEntity(Serializable id) {
        delVerify(id);
        boolean result = this.removeById(id);
        if (result) {
            T t = baseMapper.selectIgnoreDel((Long) id);
            String objName = getNameMapper().apply(t);
            addOperationLog("删除", (Long) id, objName);
        }
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
            T t = baseMapper.selectIgnoreDel((Long) id);
            String objName = getNameMapper().apply(t);
            addOperationLog(operation, (Long) id, objName);
        }
        return result;
    }

    @SneakyThrows
    @Override
    public boolean getStatus(Serializable id) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("is_stop").eq("id", id);
        List<Object> objs = baseMapper.selectObjs(queryWrapper);
        if (objs.size() > 0) return Integer.valueOf(1).equals(objs.get(0));
        throw new Exception("找不到此对象，或已被删除");
    }

    public boolean isDelete(Serializable id) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("del_flag").eq("id", id);
        List<Object> objs = baseMapper.selectObjs(queryWrapper);
        if (objs.size() > 0) return Integer.valueOf(1).equals(objs.get(0));
        return false;
    }

    @Override
    public void saveVerify(T t) {
        StringBuffer buffer = new StringBuffer();
        Boolean isDel = isDelete(((BaseEntity) t).getId());
        if (isDel) buffer.append("；该" + getEntityName() + "已经被删除");
        this.saveVerify(t, buffer);
        throwErrMsg(buffer);
    }

    @Override
    public void saveVerify(T t, StringBuffer buffer) {
        Long groupId = this.getGroupIdMapper().apply(t);
        if (null == groupId) buffer.append("；集团ID为空不能保存");
        Boolean result = baseMapper.checkDuplicateName(t);
        if (result)
            buffer.append("；该集团下已经有相同名称的" + getEntityName() + "：" + getNameMapper().apply(t));
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
        if (add) batchAddVerify(t, excelObj, buffer);
        else batchUpdateVerify(t, excelObj, buffer);
        //这个验证要放 最后，因为前面要给ID赋值
        this.saveVerify(t, buffer);
        throwErrMsg(buffer);
    }

    /**
     * 批量新增校验
     *
     * @param t
     * @param excelObj
     * @param buffer
     */
    protected void batchAddVerify(T t, Object excelObj, StringBuffer buffer) {
    }

    /**
     * 批量编辑校验
     *
     * @param t
     * @param excelObj
     * @param buffer
     */
    protected void batchUpdateVerify(T t, Object excelObj, StringBuffer buffer) {
    }

    //region 检查实体是否存在，并根据需要决定是马上抛异常还是用buffer接住异常信息

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

    /**
     * 根据字段名和字段值获取字段
     * 并根据条件抛异常
     *
     * @param name 实体名称
     * @param need 是否需要它存在
     * @return
     * @Author ljan
     */
    @SneakyThrows
    @Override
    public T chkEntityExists(String name, Long groupId, boolean need) {
        StringBuffer buffer = new StringBuffer();
        T t = this.chkEntityExists(name, groupId, need, buffer);
        throwErrMsg(buffer);
        return t;
    }

    /**
     * 根据字段上的注解自动识别是否必填字段
     * 此方法目的是避免造成必填字段要分开维护
     *
     * @param excelObj
     * @param groupId
     * @param excelField
     * @param buffer
     * @return
     */
    @SneakyThrows
    protected T chkEntityExists(Object excelObj, Long groupId, String excelField, StringBuffer buffer) {
        Field nameField = AnnotationUtils.getFieldByName(excelObj, excelField);
        HeadFontStyle fontStyle = nameField.getAnnotation(HeadFontStyle.class);
        boolean need = (null != fontStyle && (short) 10 == fontStyle.color());
        String nameVal = (String) nameField.get(excelObj);
        return chkEntityExists(nameVal, groupId, need, buffer);
    }

    /**
     * 根据字段上的注解自动识别是否必填字段
     * 此方法目的是避免造成必填字段要分开维护
     *
     * @param excelObj
     * @param groupId
     * @param excelField
     * @return
     */
    @SneakyThrows
    protected T chkEntityExists(Object excelObj, Long groupId, String excelField) {
        StringBuffer buffer = new StringBuffer();
        T t = chkEntityExists(excelObj, groupId, excelField, buffer);
        throwErrMsg(buffer);
        return t;
    }
    //endregion

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
            for (T t : dataList) {
                BaseEntity entity = (BaseEntity) t;
                entity.setModifiedTime(new Date());
                entity.setModifierName(sysUser.getUsername());
                entity.setModifierId(Long.valueOf(sysUser.getUserId()));
            }
        }
        List<List<T>> batch = Lists.partition(dataList, 100);
        for (List<T> tmp : batch) this.saveOrUpdateBatch(tmp);
        dataList.clear();
    }

    @Override
    public String selectEntityName(Serializable id) {
        String entityName = baseMapper.selectEntityName(id);
        return entityName;
    }

    @Override
    public List<Map<String, Object>> selectNamesByIds(List<? extends Serializable> ids) {
        return baseMapper.selectNamesByIds(ids);
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
