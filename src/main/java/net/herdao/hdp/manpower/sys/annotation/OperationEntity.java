package net.herdao.hdp.manpower.sys.annotation;

import java.lang.annotation.*;

/**
 * @author ljan
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationEntity {
    String operation() default "";
    String content() default "";
    String objId() default "";
    String key() default "";
    Class clazz() ;
}
