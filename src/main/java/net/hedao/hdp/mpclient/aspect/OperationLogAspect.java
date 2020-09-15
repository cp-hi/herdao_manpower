package net.hedao.hdp.mpclient.aspect;

import lombok.AllArgsConstructor;
import net.hedao.hdp.mpclient.annotation.OperationLogAnno;
import net.hedao.hdp.mpclient.entity.OperationLog;
import net.hedao.hdp.mpclient.service.OperationLogService;
import net.herdao.hdp.admin.api.dto.UserInfo;
import net.herdao.hdp.admin.api.feign.RemoteUserService;
import net.herdao.hdp.common.core.constant.SecurityConstants;
import net.herdao.hdp.common.security.util.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("@annotation(net.hedao.hdp.mpclient.annotation.OperationLogAnno)")
    public void pointCut() {
        System.out.println("point");
    }

    @After("pointCut()")
    public void after(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        OperationLogAnno anno = method.getAnnotation(OperationLogAnno.class);
        if (null != anno) {
            UserInfo userInfo = remoteUserService.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN).getData();
            OperationLog log = new OperationLog();
            log.setOperation(anno.operation());
            log.setOperatorId(userInfo.getSysUser().getUserId());
            log.setOperator(userInfo.getSysUser().getUsername());
            log.setOperatedTime(new Date());
        }
        System.out.println("after");
    }

}