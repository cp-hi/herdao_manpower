package net.herdao.hdp.sys.aspect;

import lombok.AllArgsConstructor;
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
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.lang.reflect.Field;
import java.util.List;


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

    /**
     * 记录操作的切入点
     */
    @Pointcut("@annotation(net.herdao.hdp.sys.annotation.OperationEntity)")
    public void pointCut1() {
        System.out.println("point1");
    }

    /**
     * 保存时设置操作人信息的切入点
     */
    @Pointcut("execution(public * net.herdao.hdp.mpclient.service..*.saveOrUpdate(..))")
    public void pointCut2() {
        System.out.println("point2");
    }

    /**
     * 排除字段
     */
    @Pointcut("@annotation(net.herdao.hdp.sys.annotation.ExcludeField)")
    public void pointCut3() {
        System.out.println("point3");
    }

    @Before("pointCut3()")
    public void excludeField(JoinPoint point) {
        Class cls = point.getSignature().getDeclaringType();
        if (null == cls) return;
        ExcludeField excludeField = (ExcludeField) cls.getAnnotation(ExcludeField.class);
        if (null == excludeField || excludeField.value().length == 0) return;
        List<String> excludeFields = Arrays.asList(excludeField.value());
        for (Field field : cls.getFields()) {
            if (excludeFields.contains(field.getName())) {
                //TODO 为字段加上排队注解   @TableField(exist = false)
                System.out.println("字段名是-------------" + field.getName());
            }
        }
    }

    @Before("pointCut2()")
    public void before(JoinPoint point) {
        Object[] args = point.getArgs();
        if (null == args || 0 == args.length) return;
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();

        for (Object arg : args) {
            if (arg != null && arg instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) arg;
                Field[] fields = entity.getClass().getDeclaredFields();
                for (Field f : fields) {
                    Annotation[] annos = f.getAnnotations();
                    System.out.println(annos);
                }

                if (null == entity.getId() || 0 == entity.getId()) {
                    entity.setCreatedTime(new Date());
                    entity.setCreatorName(userInfo.getSysUser().getUsername());
                    entity.setCreatorId(Long.valueOf(userInfo.getSysUser().getUserId()));
                } else {
                    entity.setModifiedTime(new Date());
                    entity.setModifierName(userInfo.getSysUser().getUsername());
                    entity.setModifierId(Long.valueOf(userInfo.getSysUser().getUserId()));
                }
            }
        }
    }

    @After("pointCut1()")
    public void after(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        OperationEntity entity = method.getAnnotation(OperationEntity.class);
        if (null != entity) {
            UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
            OperationLog log = new OperationLog();
            log.setOperation(entity.operation());
            log.setOperatorId(userInfo.getSysUser().getUserId().intValue());
            log.setOperator(userInfo.getSysUser().getUsername());
            if (null != entity.clazz())
                log.setEntityClass(entity.clazz().getName());
            if (StringUtils.isNotBlank(entity.key())) {
                //TODO 完善获取主键的方法
                Object[] args = point.getArgs();
                for (Object arg : args) {

                }
                log.setObjId(entity.key());
            }
            log.setOperatedTime(new Date());
            operationLogService.save(log);
        }
    }


}
