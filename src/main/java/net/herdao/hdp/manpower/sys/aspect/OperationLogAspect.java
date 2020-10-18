package net.herdao.hdp.manpower.sys.aspect;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import net.herdao.hdp.manpower.sys.utils.SysUserUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.lang.reflect.Field;


/**
 * @ClassName OperateAspect
 * @Description OperateAspect
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 16:11
 * @Version 1.0
 */
//TODO 记得添加删除和停用的日志记录
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private OperationLogService operationLogService;


    /**
     * 记录操作的切入点
     */
    @Pointcut("@annotation(net.herdao.hdp.manpower.sys.annotation.OperationEntity)")
    public void pointCutOperate() {
        System.out.println("pointCutOperate");
    }

    /**
     * 保存时设置操作人信息的切入点
     */
    @Pointcut("execution(public * com.baomidou.mybatisplus.extension.service..*.saveOrUpdate(..))" +
            "||execution(public * net.herdao.hdp.manpower.mpclient.service..*.saveOrUpdate(..))" +
            "||execution(public * net.herdao.hdp.manpower.mpclient.service..*.saveEntity(..))")
    public void pointCutSave() {
        System.out.println("pointCutSave");
    }

    @Pointcut("execution(public * net.herdao.hdp.manpower.mpclient.service..*.stopEntity(..))")
    public void pointCutStop() {
        System.out.println("pointCutStop");
    }

    @Pointcut("execution(public * net.herdao.hdp.manpower.mpclient.service..*.delEntity(..))")
    public void pointDelete() {
        System.out.println("pointDelete");
    }

    //region 保存实体 设置操作人信息以及实体主键

    @Before("pointCutSave()")
    public void beforeSave(JoinPoint point) {
        Method method = getJoinPointMethod(point);
        OperationEntity operation = getOperationEntity(method);
        if (0 == point.getArgs().length || null == operation)
            return;

        //保存前先验证数据
        Object target = point.getTarget();
        if (target instanceof EntityService)
            ((EntityService) target).saveVerify(point.getArgs()[0]);

        Object[] args = point.getArgs();
        SysUser sysUser = SysUserUtils.getSysUser();
        for (Object arg : args) {

            if (arg != null && arg instanceof BaseEntity) {
                Class clazz = arg.getClass();
                BaseEntity entity = (BaseEntity) arg;
                AnnotationUtils.setAnnotationInfo(operation, "clazz", clazz);
                ApiModel model = (ApiModel) clazz.getAnnotation(ApiModel.class);
                if (null == entity.getId() || 0 == entity.getId()) {
                    entity.setCreatedTime(new Date());
                    entity.setCreatorName(sysUser.getUsername());
                    entity.setCreatorId(Long.valueOf(sysUser.getUserId()));
                    AnnotationUtils.setAnnotationInfo(operation, "operation", "新增" + model.value());
                    if (StringUtils.isBlank(operation.content()))
                        AnnotationUtils.setAnnotationInfo(operation, "content", "新增" + model.value());
                } else {
                    entity.setModifiedTime(new Date());
                    entity.setModifierName(sysUser.getUsername());
                    entity.setModifierId(Long.valueOf(sysUser.getUserId()));
                    AnnotationUtils.setAnnotationInfo(operation, "operation", "修改" + model.value());
                    if (StringUtils.isBlank(operation.content()))
                        AnnotationUtils.setAnnotationInfo(operation, "content", "修改" + model.value());
                }
            }
        }
    }

    @After("pointCutSave()")
    public void afterSave(JoinPoint point) {
        OperationEntity operation = getOperationEntity(point);
        if (null == operation || 0 == point.getArgs().length)
            return;
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                Object objId = null;
                Object extraKey = null;
                Object module = null;
                if (arg instanceof BaseEntity) {
                    BaseEntity entity = (BaseEntity) arg;
                    if (null != entity.getId() && 0 != entity.getId()) {
                        objId = entity.getId();
                    }
                    if (null != entity.getExtraKey()) {
                        extraKey = entity.getExtraKey();
                    }
                    if (null != entity.getExtraKey()) {
                        module = entity.getModule();
                    }
                } else {
                    objId = getTableIdValue(arg);
                }
                if (null != objId) {
                    AnnotationUtils.setAnnotationInfo(operation, "objId", objId.toString());
                }
                if (null != extraKey) {
                    AnnotationUtils.setAnnotationInfo(operation, "extraKey", extraKey.toString());
                }
                if (null != module) {
                    AnnotationUtils.setAnnotationInfo(operation, "module", module.toString());
                }

            }
        }
    }


    @Before("pointDelete()")
    public void beforeDelete(JoinPoint point) {
        Method method = getJoinPointMethod(point);
        OperationEntity operation = getOperationEntity(method);
        if (0 == point.getArgs().length || null == operation) {
            return;
        }

        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg != null && arg instanceof BaseEntity) {
                Class clazz = arg.getClass();
                AnnotationUtils.setAnnotationInfo(operation, "clazz", clazz);
                ApiModel model = (ApiModel) clazz.getAnnotation(ApiModel.class);
//                AnnotationUtils.setAnnotationInfo(operation, "operation", "删除" + model.value());
                if (StringUtils.isBlank(operation.content())) {
                    AnnotationUtils.setAnnotationInfo(operation, "content", "删除" + model.value());
                }
            }
        }
    }


    @After("pointDelete()")
    public void afterDelete(JoinPoint point) {
        OperationEntity operation = getOperationEntity(point);
        if (null == operation || 0 == point.getArgs().length)
            return;
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                Object objId = null;
                Object extraKey = null;
                Object module = null;
                if (arg instanceof BaseEntity) {
                    BaseEntity entity = (BaseEntity) arg;
                    if (null != entity.getId() && 0 != entity.getId()) {
                        objId = entity.getId();
                    }
                    if (null != entity.getExtraKey()) {
                        extraKey = entity.getExtraKey();
                    }
                    if (null != entity.getExtraKey()) {
                        module = entity.getModule();
                    }
                } else {
                    objId = getTableIdValue(arg);
                }
                if (null != objId) {
                    AnnotationUtils.setAnnotationInfo(operation, "objId", objId.toString());
                }
                if (null != extraKey) {
                    AnnotationUtils.setAnnotationInfo(operation, "extraKey", extraKey.toString());
                }
                if (null != module) {
                    AnnotationUtils.setAnnotationInfo(operation, "module", module.toString());
                }

            }
        }
    }

    //endregion

    //region 操作记录

    @After("pointCutOperate()")
    public void afterOperate(JoinPoint point) {
        //TODO 解决前面验证报错了，还会往下执行这个日志保存
        OperationEntity operation = getOperationEntity(point);
        if (StringUtils.isNotBlank(operation.exception()))
            return;

        OperationLog log = new OperationLog();


        if (StringUtils.isNotBlank(operation.extraKey())) {
            log.setExtraKey(operation.extraKey());
        }

        if (StringUtils.isNotBlank(operation.module())) {
            log.setModule(operation.module());
        }

        Class entityClass = null;
        Object target = point.getTarget();
        if (target instanceof EntityService)
            entityClass = (Class) ((ParameterizedType) target.getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[1];

        if (null != log.getObjId() && null !=entityClass) {
            SysUser sysUser = SysUserUtils.getSysUser();
            log.setObjId(Long.valueOf((String) point.getArgs()[0]));
            log.setOperatedTime(new Date());
            log.setContent(operation.content());
            log.setOperator(sysUser.getUsername());
            log.setOperation(operation.operation());
            log.setEntityClass(entityClass.getName());
            log.setOperatorId(sysUser.getUserId().longValue());
            operationLogService.save(log);
        }
    }


    @After("pointCutStop()")
    public void afterStop(JoinPoint point) {
        Object target = point.getTarget();
        Class entityClass = null;
        if (target instanceof EntityService)
            entityClass = (Class) ((ParameterizedType) target.getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[1];

        if (null == entityClass) return;

        Object id = point.getArgs()[0];
        Boolean stop = (Boolean) point.getArgs()[1];
        String operation = stop ? "停用" : "启用";
        ApiModel model = (ApiModel) entityClass.getAnnotation(ApiModel.class);

        OperationLog log = new OperationLog();
        SysUser sysUser = SysUserUtils.getSysUser();
        log.setObjId(Long.valueOf(id.toString()));
        log.setOperation(operation);
        log.setOperatedTime(new Date());
        log.setContent(operation + " " + model.value());
        log.setOperator(sysUser.getUsername());
        log.setEntityClass(entityClass.getName());
        log.setOperatorId(sysUser.getUserId().longValue());
        operationLogService.save(log);
    }

    //endregion

    //region 公共方法

    private Method getJoinPointMethod(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        return method;
    }

    private OperationEntity getOperationEntity(Method method) {
        OperationEntity operation = method.getAnnotation(OperationEntity.class);
        return operation;
    }

    private OperationEntity getOperationEntity(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        OperationEntity operation = method.getAnnotation(OperationEntity.class);
        return operation;
    }

    /**
     * 通过注解获取对象主键值
     *
     * @param arg
     * @return
     */
    private Object getTableIdValue(Object arg) {
        Field objId = AnnotationUtils.getOneAnnotationFields(arg, TableId.class);
        if (null == objId) return null;
        objId.setAccessible(true);
        Object val = null;
        try {
            val = objId.get(arg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return val;
    }

    //endregion
}
