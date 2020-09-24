package net.herdao.hdp.sys.aspect;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.sys.annotation.ExcludeField;
import net.herdao.hdp.sys.annotation.OperationEntity;
import net.herdao.hdp.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.sys.entity.OperationLog;
import net.herdao.hdp.sys.service.OperationLogService;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Date;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


/**
 * @ClassName OperateAspect
 * @Description OperateAspect
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/14 16:11
 * @Version 1.0
 */
@Aspect
@Component
@AllArgsConstructor
public class OperationLogAspect {

    private final OperationLogService operationLogService;
    private final RemoteUserService remoteUserService;

    private SysUser getSysUser() {
        UserInfo userInfo = remoteUserService.info(
                SecurityUtils.getUser().getUsername(),
                SecurityConstants.FROM_IN).getData();
        return userInfo.getSysUser();
    }

    /**
     * 记录操作的切入点
     */
    @Pointcut("@annotation(net.herdao.hdp.sys.annotation.OperationEntity)")
    public void pointCutOperate() {
        System.out.println("point1");
    }

    /**
     * 保存时设置操作人信息的切入点
     */
    @Pointcut("execution(public * net.herdao.hdp.mpclient.service..*.saveOrUpdate(..))")
    public void pointCutSave() {
        System.out.println("point2");
    }

    //region 保存实体 设置操作人信息以及实体主键

    @Before("pointCutSave()")
    public void beforeSave(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        OperationEntity operation = method.getAnnotation(OperationEntity.class);
        Object[] args = point.getArgs();
        if (null == args || 0 == args.length) return;
        SysUser sysUser = getSysUser();
        for (Object arg : args) {
            if (arg != null && arg instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) arg;
                ApiModel model = (ApiModel) operation.clazz().getAnnotation(ApiModel.class);
                //TODO 通过注解方式获取主键
                if (null == entity.getId() || 0 == entity.getId()) {
                    entity.setCreatedTime(new Date());
                    entity.setCreatorName(sysUser.getUsername());
                    entity.setCreatorId(Long.valueOf(sysUser.getUserId()));
                    setAnnotationInfo(operation, "operation", "新增" + model.value());
                } else {
                    entity.setModifiedTime(new Date());
                    entity.setModifierName(sysUser.getUsername());
                    entity.setModifierId(Long.valueOf(sysUser.getUserId()));
                    setAnnotationInfo(operation, "operation", "修改" + model.value());
                }

            }
        }
    }

    @After("pointCutSave()")
    public void afterSave(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();
        if (null == args || 0 == args.length) return;
        OperationEntity operation = method.getAnnotation(OperationEntity.class);
        if (null == operation) return;
        for (Object arg : args) {
            if (arg != null && arg instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) arg;
                //TODO 通过注解方式获取主键
                if (null != entity.getId() && 0 != entity.getId())
                    setAnnotationInfo(operation, "objId", entity.getId());
            }
        }
    }

    //endregion

    //region 操作记录

    @After("pointCutOperate()")
    public void afterOperate(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        OperationEntity operation = method.getAnnotation(OperationEntity.class);
        if (null != operation) {
            SysUser sysUser = getSysUser();
            OperationLog log = new OperationLog();
            log.setOperation(operation.operation());
            log.setOperator(sysUser.getUsername());
            log.setOperatorId(sysUser.getUserId().longValue());
            if (null != operation.clazz())
                log.setEntityClass(operation.clazz().getName());

            if (StringUtils.isNotBlank(operation.objId())) {
                log.setObjId(Long.valueOf(operation.objId()));
            } else if (StringUtils.isNotBlank(operation.key())
                    && StringUtils.isBlank(operation.objId())) {
                int index = Arrays.binarySearch(signature.getParameterNames(), operation.key());
                Object objId = point.getArgs()[index];
                log.setObjId((Long) objId);
            }
            log.setOperatedTime(new Date());
            operationLogService.save(log);
        }
    }

    //endregion


    private void setAnnotationInfo(Annotation annotation, String key, Object val) {
        InvocationHandler h;
        Field field;
        Map memberValues = null;
        try {
            if (null != annotation) {
                //获取 foo 这个代理实例所持有的 InvocationHandler
                h = Proxy.getInvocationHandler(annotation);
                // 获取 AnnotationInvocationHandler 的 memberValues 字段
                field = h.getClass().getDeclaredField("memberValues");
                // 因为这个字段事 private final 修饰，所以要打开权限
                field.setAccessible(true);
                memberValues = (Map) field.get(h);
                if (null != memberValues)
                    memberValues.put(key, val.toString());
            }
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

}
