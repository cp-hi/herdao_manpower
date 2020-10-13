package net.herdao.hdp.manpower.sys.aspect;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import net.herdao.hdp.admin.api.entity.SysUser;
import net.herdao.hdp.manpower.mpclient.entity.base.BaseEntity;
import net.herdao.hdp.manpower.sys.annotation.OperationEntity;
import net.herdao.hdp.manpower.sys.entity.OperationLog;
import net.herdao.hdp.manpower.sys.service.OperationLogService;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import net.herdao.hdp.manpower.sys.utils.AnnotationUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
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
    @Pointcut("@annotation(net.herdao.hdp.manpower.sys.annotation.OperationEntity)")
    public void pointCutOperate() {
        System.out.println("pointCutOperate");
    }

//    @Pointcut("@annotation(net.herdao.hdp.manpower.sys.annotation.ImportField)")
//    public void pointCutImport() {
//        System.out.println("pointCutImport");
//    }

    /**
     * 保存时设置操作人信息的切入点
     */
    @Pointcut("execution(public * net.herdao.hdp.manpower.mpclient.service..*.saveOrUpdate(..))")
    public void pointCutSave() {
        System.out.println("point2");
    }

    //region 保存实体 设置操作人信息以及实体主键

    @Before("pointCutSave()")
    public void beforeSave(JoinPoint point) {
        Method method = getJoinPointMethod(point);
        OperationEntity operation = getOperationEntity(method);
        if (0 == point.getArgs().length || null == operation)
            return;
        Class<?> service = method.getDeclaringClass();
        //TODO 完善保存前验证 EntityService
//        if(service instanceof EntityService){
//
//        }
        Object[] args = point.getArgs();
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

                Field[] fields = AnnotationUtils.getAllAnnotationFields(arg, TableId.class);

                if (arg instanceof BaseEntity) {
                    BaseEntity entity = (BaseEntity) arg;
                    //TODO 通过注解方式获取主键
                    if (null != entity.getId() && 0 != entity.getId())
                        AnnotationUtils.setAnnotationInfo(operation, "objId", entity.getId());
                } else {

//                    fields[0].get()
                }
            }
        }
    }

    //endregion

    //region 操作记录

    @After("pointCutOperate()")
    public void afterOperate(JoinPoint point) {

        MethodSignature signature = (MethodSignature) point.getSignature();
        OperationEntity operation = getOperationEntity(point);
        SysUser sysUser = getSysUser();
        OperationLog log = new OperationLog();
        log.setOperation(operation.operation());
        log.setContent(operation.content());
        log.setOperator(sysUser.getUsername());
        log.setOperatorId(sysUser.getUserId().longValue());
        if (null != operation.clazz())
            log.setEntityClass(operation.clazz().getName());

        if (StringUtils.isNotBlank(operation.objId())) {
            log.setObjId(Long.valueOf(operation.objId()));
        } else if (StringUtils.isNotBlank(operation.key())
                && StringUtils.isBlank(operation.objId())) {
            int index = Arrays.asList(signature.getParameterNames()).indexOf(operation.key());
            Object objId = point.getArgs()[index];
            log.setObjId(Long.valueOf(objId.toString()));
        }
        log.setOperatedTime(new Date());
        operationLogService.save(log);
    }

    //endregion


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

}
