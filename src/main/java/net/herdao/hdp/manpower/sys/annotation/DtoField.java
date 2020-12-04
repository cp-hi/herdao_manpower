package net.herdao.hdp.manpower.sys.annotation;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import net.herdao.hdp.manpower.mpclient.service.impl.EntityServiceImpl;

import java.lang.annotation.*;

/**
 * @ClassName DtoField
 * @Description 读取Dto Field的数据，
 * 此注解有2种用法：
 * 1）简单获取关联实体名称，以及字典名称，
 *      这种情况下建议把注解打在Entity 字段上，参照Post.java
 * 2）以一定规则拼接的组合字段，由于规则的多样性
 *      这种情况建议把注解打在VO类字段上 ，参照 PostListVO.java
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 8:31
 * @Version 1.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@TableField(updateStrategy= FieldStrategy.IGNORED)
public @interface DtoField {

    String[] objField() default "";

    String listField() default "";

    String dictField() default "";

    /**
     * 分割符
     * @return
     */
    String separator() default " ";

    /**
     * 插值字典
     * @return
     */
    String interpolation() default "";//example  "{1:\"于\",3:\"创建\"}"    "{1:\"于\",3:\"更新\"}"

    /**
     * bool转换器
     * @return
     */
    String converter() default "{true:\"已停用\",false:\"已启用\"}";

    /**
     * 日期格式
     * @return
     */
    String pattern() default "yyyy-MM-dd HH:mm";

    String delFix() default "";

    /**
     * 实体服务类
     * @return
     */
    Class<? extends EntityServiceImpl> entityService() default EntityServiceImpl.class;

    /**
     * 目标字段
     * @return
     */
    String targetField() default "";
}
