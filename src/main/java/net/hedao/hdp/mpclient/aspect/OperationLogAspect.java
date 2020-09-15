package net.hedao.hdp.mpclient.aspect;

import lombok.AllArgsConstructor;
import net.hedao.hdp.mpclient.annotation.OperationEntity;
import net.hedao.hdp.mpclient.entity.BaseEntity;
import net.hedao.hdp.mpclient.entity.OperationLog;
import net.hedao.hdp.mpclient.service.OperationLogService;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Date;

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

    @Pointcut("@annotation(net.hedao.hdp.mpclient.annotation.OperationEntity)")
    public void pointCut1() {
        System.out.println("point1");
    }

    @Pointcut("execution(public * net.hedao.hdp.mpclient.service..*.addOrUpdate(..))")
    public void pointCut2() {
        System.out.println("point2");
    }

//    @Around("pointCut2()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        Object result = point.proceed();
//        return result;
//    }

    @Before("pointCut2()")//保存时设置操作人信息
    public void before(JoinPoint point) {
        Object[] args = point.getArgs();
        if (null == args || 0 == args.length) return;
        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();

        for (Object arg : args) {
            if (arg != null && arg instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) arg;

                if (null == entity.getId() || 0 == entity.getId()) {
                    entity.setCreatedTime(new Date());
                    entity.setCreatorCode(userInfo.getSysUser().getUsername());
                    entity.setCreatorId(Long.valueOf(userInfo.getSysUser().getUserId()));
                } else {
                    entity.setModifierTime(new Date());
                    entity.setModifierCode(userInfo.getSysUser().getUsername());
                    entity.setModifierId(Long.valueOf(userInfo.getSysUser().getUserId()));
                }

            }
        }
//        if(null == entity) return;
//        UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
//
//        if(null == entity.getId() || 0 == entity.getId()){
//            entity.setCreatedTime(new Date());
//            entity.setCreatorCode(userInfo.getSysUser().getUsername());
//            entity.setCreatorId(Long.valueOf( userInfo.getSysUser().getUserId()));
//        }else {
//            entity.setModifierTime(new Date());
//            entity.setModifierCode(userInfo.getSysUser().getUsername());
//            entity.setModifierId(Long.valueOf( userInfo.getSysUser().getUserId()));
//        }

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
            log.setOperatorId(userInfo.getSysUser().getUserId());
            log.setOperator(userInfo.getSysUser().getUsername());
            if (null != entity.clazz())
                log.setEntityClass(entity.clazz().getName());
            log.setOperatedTime(new Date());
            operationLogService.save(log);
        }
        System.out.println("after");
    }

}
