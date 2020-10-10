package net.herdao.hdp.manpower.sys.annotation;

import java.lang.annotation.*;

/**
 * @ClassName ImportField
 * @Description ImportField
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/9 14:57
 * @Version 1.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImportField {
    Class clazz();
}
