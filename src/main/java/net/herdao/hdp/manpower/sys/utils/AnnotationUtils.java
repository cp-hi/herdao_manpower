package net.herdao.hdp.manpower.sys.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static List<Field> getAllFields(Class clazz, String... excludeColumn) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        List<String> exclude = Arrays.asList(excludeColumn);
        fields = fields.stream().filter(f ->
                !exclude.contains(f.getName())).collect(Collectors.toList());
        return fields;
    }


    /**
     * 获取所有字段名
     *
     * @param clazz         类
     * @param excludeColumn 要排除的字段
     * @return
     */
    public static List<String> getAllFieldNames(Class clazz, String... excludeColumn) {
        List<Field> fields = getAllFields(clazz, excludeColumn);
        List<String> exclude = Arrays.asList(excludeColumn);
        List<String> fieldNames = fields.stream().map(Field::getName).filter(
                n -> !exclude.contains(n)).collect(Collectors.toList());
        return fieldNames;
    }

    public static Field getFieldByName(Object source, String fieldName) {
        List<Field> fields = getAllFields(source.getClass());
        for (Field field : fields) {
            if (fieldName.equals(field.getName())) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }

    public static List<String> getExcelPropertyNames(Object object, String... excludeColumn) {
        List<String> propertyNames = getExcelPropertyNames(object.getClass());
        propertyNames.removeAll(Arrays.asList(excludeColumn));
        return propertyNames;
    }

    public static List<String> getExcelPropertyNames(Class clazz, String... excludeColumn) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> names = new ArrayList<>();
        for (Field field : fields) {
            ExcelProperty property = field.getAnnotation(ExcelProperty.class);
            if (null != property) names.add(property.value()[property.value().length - 1]);
        }
        names.removeAll(Arrays.asList(excludeColumn));
        return names;
    }

    /**
     * 获取当前类中包含某注解的字段,通常用于获取主键，包括父类，包括父类，包括父类
     *
     * @param object
     * @return
     * @Author ljan
     */
    public static Field getOneAnnotationFields(Object object, Class<? extends Annotation> clazz) {
        List<Field> fields = getAllFields(object.getClass());
        fields = getAllAnnotationFields(fields, clazz);
        if (null != fields && fields.size() > 0) {
            fields.get(0).setAccessible(true);
            return fields.get(0);
        }
        return null;
    }

    /**
     * 获取当前类中包含某注解的字段，包括父类，包括父类，包括父类
     *
     * @param object
     * @return
     * @Author ljan
     */
    public static List<Field> getAllAnnotationFields(Object object, Class<? extends Annotation> clazz) {
        List<Field> fields = getAllFields(object.getClass());
        List<Field> list = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(clazz);
            if (null != annotation) list.add(field);
        }
        return list;
    }
}
