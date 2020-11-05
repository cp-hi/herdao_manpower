package net.herdao.hdp.manpower.sys.annotation;

import java.lang.annotation.*;

/**
 * @ClassName EntityName
 * @Description EntityName
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/5 8:54
 * @Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityName {
    String value() default "";
}
