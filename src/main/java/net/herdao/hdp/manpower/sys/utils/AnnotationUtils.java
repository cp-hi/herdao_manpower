package net.herdao.hdp.manpower.sys.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import sun.reflect.annotation.AnnotationType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AnnotationUtils
 * @Description AnnotationUtils
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/12 16:10
 * @Version 1.0
 */
public class AnnotationUtils {


    /**
     * 设置注解值
     *
     * @param annotation
     * @param key
     * @param val
     * @Author ljan
     */
    public static void setAnnotationInfo(Annotation annotation, String key, Object val) {
        InvocationHandler h;
        Field field;
        Map memberValues = null;
        try {
            if (null != annotation) {
                //获取 foo 这个代理实例所持有的 InvocationHandler
                h = Proxy.getInvocationHandler(annotation);
                // 获取 AnnotationInvocationHandler 的 memberValues 字段
                field = h.getClass().getDeclaredField("memberValues");
                // 因为这个字段事 private final 修饰，所以要打开权限
                field.setAccessible(true);
                memberValues = (Map) field.get(h);
                if (null != memberValues)
                    memberValues.put(key, val);
            }
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取一个类所有字段，包括父类
     *
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object) {
        Class clazz = object.getClass();
        return getAllFields(clazz);
    }

    public static Field[] getAllFields(Class clazz) {
//        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    public static Field getFieldByName(Object object, String fieldName) {
        Field[] fields = getAllFields(object);
        for (Field field : fields) {
            if (fieldName.equals(field.getName()))
                return field;
        }
        return null;
    }

    public static List<String> getExcelPropertyNames(Object object) {
        return getExcelPropertyNames(object.getClass());
    }

    public static List<String> getExcelPropertyNames(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> names = new ArrayList<>();
        for (Field field : fields) {
            ExcelProperty property = field.getAnnotation(ExcelProperty.class);
            names.add(property.value()[0]);
        }
        return names;
    }

    /**
     * 获取当前类中包含某注解的字段，注意，不包含父类的
     *
     * @param object
     * @return
     * @Author ljan
     */
    public static Field[] getAnnotationFields(Object object, Class<? extends Annotation> clazz) {
        Field[] fields = object.getClass().getDeclaredFields();
        return getAllAnnotationFields(fields, clazz);
    }

    /**
     * 获取当前类中包含某注解的字段,通常用于获取主键，包括父类，包括父类，包括父类
     *
     * @param object
     * @return
     * @Author ljan
     */
    public static Field getOneAnnotationFields(Object object, Class<? extends Annotation> clazz) {
        Field[] fields = getAllFields(object);
        fields = getAllAnnotationFields(fields, clazz);
        if (null != fields && fields.length > 0) return fields[0];
        return null;
    }

    /**
     * 获取当前类中包含某注解的字段，包括父类，包括父类，包括父类
     *
     * @param object
     * @return
     * @Author ljan
     */
    public static Field[] getAllAnnotationFields(Object object, Class<? extends Annotation> clazz) {
        Field[] fields = getAllFields(object);
        return getAllAnnotationFields(fields, clazz);
    }

    /**
     * 获取当前类中包含某注解的字段，包括父类，包括父类，包括父类
     *
     * @return
     * @Author ljan
     */
    public static Field[] getAllAnnotationFields(Field[] fields, Class<? extends Annotation> clazz) {
        List<Field> list = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(clazz);
            if (null != annotation) list.add(field);
        }
        Field[] array = new Field[list.size()];
        return list.toArray(array);
    }


    public static Field getFieldByAnnotationAndName(Object object, String fieldName, Class<? extends Annotation> clazz) {
        Field[] fields = getAllAnnotationFields(object, clazz);
        return getFieldByAnnotationAndName(fields, fieldName, clazz);
    }

    public static Field getFieldByAnnotationAndName(Field[] fields, String fieldName, Class<? extends Annotation> clazz) {
        Field field = null;
        for (Field f : fields) {
            if (fieldName.equals(f.getName())) {
                field = f;
                break;
            }
        }
        return field;
    }


    public static <T extends Annotation> T getAnnotationByFieldName(Object object, String fieldName, Class<? extends Annotation> clazz) {
        Field field = getFieldByAnnotationAndName(object, fieldName, clazz);
        T t = (T) field.getAnnotation(clazz);
        return t;
    }


    public static <T extends Annotation> T getAnnotationByFieldName(Field[] fields, String fieldName, Class<? extends Annotation> clazz) {
        Field field = getFieldByAnnotationAndName(fields, fieldName, clazz);
        T t = (T) field.getAnnotation(clazz);
        return t;
    }
}
