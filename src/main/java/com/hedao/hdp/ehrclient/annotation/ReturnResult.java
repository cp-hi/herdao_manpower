package com.hedao.hdp.ehrclient.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReturnResult {
    String msg() default "";
}
