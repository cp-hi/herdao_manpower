package net.herdao.hdp.manpower.sys.annotation;

import java.lang.annotation.*;

/**
 * @ClassName ImportDescription
 * @Description ImportDescription
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/29 10:56
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImportDescription {
    String value() default "";
}
