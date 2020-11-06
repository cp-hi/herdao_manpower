package net.herdao.hdp.manpower.sys.annotation;

import java.lang.annotation.*;

/**
 * @ClassName EntityCode
 * @Description EntityCode
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/5 8:53
 * @Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityCode {
    String value() default "";
}
