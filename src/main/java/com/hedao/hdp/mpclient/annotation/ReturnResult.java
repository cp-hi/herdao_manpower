package com.hedao.hdp.mpclient.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReturnResult {
    String msg() default "";
}
