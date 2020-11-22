package net.herdao.hdp.manpower.sys.annotation;

import net.herdao.hdp.manpower.mpclient.service.EntityService;

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

    String[] objField() default "";

    String listField() default "";

    String dictField() default "";

    String separator() default " ";

    String mapFix() default "";//example  "{1:\"于\",3:\"创建\"}"    "{1:\"于\",3:\"更新\"}"

    String converter() default "{true:\"已停用\",false:\"已启用\"}";

    String pattern() default "yyyy-MM-dd HH:mm";

    String delFix() default "";

    Class entityService() default Class.class;

    String pkField() default "";
}
