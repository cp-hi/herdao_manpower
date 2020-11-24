package net.herdao.hdp.manpower.sys.annotation;

import net.herdao.hdp.manpower.mpclient.service.EntityService;
import net.herdao.hdp.manpower.mpclient.service.impl.EntityServiceImpl;

import java.lang.annotation.*;

/**
 * @ClassName DtoField
 * @Description 读取Dto Field的数据，
 * 此注解有2种用法：
 * 1）简单获取关联实体名称，以及字典名称，
 *      这种情况下建议把注解打在Entity 字段上
 * 2）以一定规则拼接的组合字段，由于规则的多样性
 *      这种情况建议把注解打在VO类字段上
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

    Class<? extends EntityServiceImpl> entityService() default EntityServiceImpl.class;

    String pkField() default "";

    String targetField() default "";
}
