package net.herdao.hdp.manpower.sys.utils;

import net.herdao.hdp.manpower.sys.annotation.DtoField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DtoConverter
 * @Description DtoConverter
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 8:31
 * @Version 1.0
 */
public class DtoConverter {

    public static <T> T convert(Object source, Class clzz)
            throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, NoSuchFieldException {

        Class clazz = Class.forName(clzz.getName());
        Object t = clazz.newInstance();
        BeanUtils.copyProperties(source, t);
        Field[] fields = AnnotationUtils.getAllAnnotationFields(t, DtoField.class);
        for (Field field : fields) {
            if (null == field) continue;

            field.setAccessible(true);
            //TODO 根据 DtoField中的信息获取source中对象数据
            DtoField dtoField = field.getAnnotation(DtoField.class);
            if (StringUtils.isNotBlank(dtoField.objField())
                    && StringUtils.isNotBlank(dtoField.listField()))
                throw new RuntimeException("不能设置对象字段同时设置列表字段");
            String[] path = dtoField.objField().split("\\.");
            Field currObj = source.getClass().getDeclaredField(path[0]);
            if (null == currObj) continue;
            currObj.setAccessible(true);
            Object seq = currObj.get(source);
            if (null != seq) {
                Field val = seq.getClass().getDeclaredField(path[1]);
                val.setAccessible(true);
                field.set(t, val.get(seq));
            }
        }
        return (T) t;
    }

    public static <T> List<T> convert(List source, Class clzz) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        for (Object o : source) {
            T t = convert(o, clzz);
            list.add(t);
        }
        return list;
    }

}
