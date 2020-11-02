package net.herdao.hdp.manpower.mpclient.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import net.herdao.hdp.manpower.mpclient.utils.StringBufferUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.ParameterizedType;

/**
 * @ClassName EntityMapper
 * @Description EntityMapper
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/29 21:32
 * @Version 1.0
 */
public interface EntityMapper<T> extends BaseMapper<T> {

    //region table & entity info

    /**
     * 获取实体类名
     *
     * @return
     */
    default Class<T> getEntityClass() {
        Class<T> clazz = null;
        try {
            Class cls = Class.forName(getClass().getGenericInterfaces()[0].getTypeName());
            clazz = (Class<T>) ((ParameterizedType) cls.getGenericInterfaces()[0]).getActualTypeArguments()[0];
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("EntityMapper----------找不到相应的实体类型");
        }
        return clazz;
    }


    /**
     * 获取数据表名
     *
     * @return
     */
    default String getTableName() {
        Class<T> clazz = getEntityClass();
        TableName table = clazz.getAnnotation(TableName.class);
        return table.value();
    }


    default String getEntityName() {
        Class<T> clazz = getEntityClass();
        ApiModel apiModel = clazz.getAnnotation(ApiModel.class);
        return apiModel.value();
    }

    /**
     * 获取编码在表中的字段名
     *
     * @return
     */
    default String getTableCodeField() {
        String codeField = getTableName().toLowerCase().replaceFirst("mp_", "")
                .replaceFirst("sys_", "") + "_code";
        return codeField;
    }

    /**
     * 获取编码在实体中的字段名
     *
     * @return
     */
    default String getEntityCodeField() {
        Class<T> clazz = getEntityClass();
        String entityName = StringBufferUtils.toLowerCaseFirstOne(clazz.getSimpleName());
        return entityName + "Code";
    }

    /**
     * 获取Name在表中的字段名
     *
     * @return
     */
    default String getTableNameField() {
        String codeField = getTableName().toLowerCase().replaceFirst("mp_", "")
                .replaceFirst("sys_", "") + "_name";
        return codeField;
    }

    /**
     * 获取Name在实体中的字段名
     *
     * @return
     */
    default String getEntityNameField() {
        Class<T> clazz = getEntityClass();
        String entityName = StringBufferUtils.toLowerCaseFirstOne(clazz.getSimpleName());
        return entityName + "Name";
    }

    //endregion

    /**
     * 分页，默认用自身实体可传所有参数，
     * 如有其他参数需自己另写方法实现
     *
     * @param page
     * @param t
     * @return
     */
    IPage page(IPage page, @Param("t") T t);

    /**
     * 获取表单对象
     * @param id
     * @return
     */
    Object form(Long id);

    /**
     * 校验重复名称
     * @param t
     * @return
     */
    Boolean checkDuplicateName(T t);

    /**
     * 获取最新的实体编码
     * @param t
     * @return
     */
    String getLastEntityCode(T t);
}
