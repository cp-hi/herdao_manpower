package net.hedao.hdp.mpclient.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogAnnotation {
    String operation() default "";
    String entityClazz()default "";
}
