package com.hedao.hdp.ehrclient.aspect;

import com.hedao.hdp.ehrclient.annotation.ReturnResult;
import net.herdao.hdp.common.core.util.R;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @ClassName ReturnAspect
 * @Description ReturnAspect
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/9/11 9:48
 * @Version 1.0
 */

public class ReturnAspect {

    @Pointcut("@annotation(com.hedao.hdp.ehrclient.annotation.ReturnResult)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public R around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object result = point.proceed();
        return R.ok(result);
    }

    // 所有方法的执行作为切入点
    // 声明ex时指定的类型会限制目标方法必须抛出指定类型的异常
    // 此处将ex的类型声明为Throwable，意味着对目标方法抛出的异常不加限制
    @AfterThrowing(throwing = "ex", pointcut = "pointCut()")
    public R throwing(JoinPoint point, Throwable ex) {
        String msg = ex.getMessage();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        ReturnResult anno = method.getAnnotation(ReturnResult.class);
        if (null != anno && StringUtils.isNotBlank(anno.msg()))
            msg = anno.msg();
        return R.failed(msg);
    }
}
