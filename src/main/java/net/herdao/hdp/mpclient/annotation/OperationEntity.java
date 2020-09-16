package net.herdao.hdp.mpclient.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationEntity {
    String operation() default "";
    String key() default "";
    Class clazz() ;
}
