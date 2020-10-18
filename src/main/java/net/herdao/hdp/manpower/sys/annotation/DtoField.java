package net.herdao.hdp.manpower.sys.annotation;

import java.lang.annotation.*;

/**
 * @ClassName DtoField
 * @Description 读取Dto Field的数据
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 8:31
 * @Version 1.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DtoField {

    String[] joinFields() default "";

    String  boolField() default "";

    String objField() default "";

    String listField() default "";

    String dictField() default "";

    String symbol() default "-";

    String suffix() default "";
}