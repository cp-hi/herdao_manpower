package net.herdao.hdp.manpower.sys.utils;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ClassName DtoConverter
 * @Description DtoConverter
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 8:31
 * @Version 1.0
 */
public class DtoConverter {

    public static <T> T convert(Object source, Class clzz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class clazz = Class.forName(clzz.getName());
        Object t = clazz.newInstance();
        BeanUtils.copyProperties(source, t);
        return (T) t;
    }
}
