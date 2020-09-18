package net.herdao.hdp.sys.annotation;

import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerifyData {
    enum VerifyType{
        NONE,
        NOTNULL,
        INTEGER,
        NUMBER,
        TELEPHONE,
        MOBILE,
        PASSPORT,
        REGULAR,
    };
    VerifyType type() default VerifyType.NONE;
    String regular() default "";
}
