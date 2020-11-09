package net.herdao.hdp.manpower.sys.annotation;

import java.lang.annotation.*;

/**
 * @ClassName FieldValid
 * @Description FieldValid
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/9 11:43
 * @Version 1.0
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldValid {
    String[] exclude() default {"errMsg"};
}
