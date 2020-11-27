package net.herdao.hdp.manpower.sys.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.SneakyThrows;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.sys.annotation.DtoField;
import net.herdao.hdp.manpower.sys.cache.DictCache;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName DtoConverter
 * @Description DTO VO ENTITY之间的转换类
 * ，配合@DtoField使用
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 8:31
 * @Version 1.0
 */

public class DtoConverter {

    /**
     * @param source      dto类
     * @param targetClazz vo 类
     * @param <T>         vo 类
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public static <T> T dto2vo(Object source, Class<? extends T> targetClazz)
            throws IllegalAccessException, InstantiationException {
        Object target = targetClazz.newInstance();
        BeanUtils.copyProperties(source, target);
        List<Field> fields = AnnotationUtils.getAllAnnotationFields(target, DtoField.class);
        for (Field field : fields) {
            //根据 DtoField中的信息获取source中对象数据
            DtoField dtoField = field.getAnnotation(DtoField.class);

            String value = "";
            if (dtoField.objField().length > 0
                    && StringUtils.isNotBlank(dtoField.objField()[0])) {
                value = getDtoFieldVal(source, dtoField);
            } else if (StringUtils.isNotBlank(dtoField.listField())) {
                value = getListFieldVal(source, dtoField);//TODO 完善此方法
            } else if (StringUtils.isNotBlank(dtoField.dictField())) {
                value = getDictFieldVal(source, field);
            }
            field.set(target, value);
        }
        return (T) target;
    }

    /**
     * 转换字段属性
     *
     * @param source   源对象
     * @param dtoField 字段注解
     * @return
     * @throws IllegalAccessException
     */
    private static String getDtoFieldVal(Object source, DtoField dtoField) throws IllegalAccessException {

        List<String> values = new ArrayList<>();
        for (String fieldStr : dtoField.objField()) {
            Object currObj = source;
            String[] path = fieldStr.split("\\.");
            String fieldName = path[path.length - 1];
            for (int i = 0; i < path.length - 1; i++) {
                String currObjName = path[i];
                if (null == currObj) continue;
                Field field = AnnotationUtils.getFieldByName(currObj, currObjName);
                if (null == field) continue;
                currObj = field.get(currObj);
            }
            if (null == currObj) continue;
            Field fieldVal = AnnotationUtils.getFieldByName(currObj, fieldName);
            if (null == fieldVal) continue;
            Object objVal = fieldVal.get(currObj);
            String value = "";
            if (Date.class == fieldVal.getType()) {
                //如果是日期则转格式
                value = DateUtils.formatDate((Date) objVal, dtoField.pattern());
            } else if (Boolean.class == fieldVal.getType()) {
                //如果设置了布尔器则转文字
                if (StringUtils.isNotBlank(dtoField.converter())) {
                    Map map = (Map) JSON.parse(dtoField.converter());
                    value = (String) map.get(String.valueOf(objVal));
                } else {
                    value = String.valueOf(objVal);
                }
            } else {
                value = String.valueOf(objVal);
            }
            if (StringUtils.isNotBlank(value) && !"null".equals(value)) {
                if (StringUtils.isNotBlank(dtoField.delFix())) {
                    Field delField = AnnotationUtils.getFieldByName(currObj, "delFlag");
                    if (null != delField) {
                        //被删除了则用设置的规则替换
                        Object del = delField.get(currObj);
                        if ((Boolean) del) value = dtoField.delFix();
                    }
                }
                values.add(value);
            }
        }

        //如果有插值map则插值， 一般用于 xx于 xx创建 xx于 xx更新
        if (StringUtils.isNotBlank(dtoField.interpolation())) {
            Map infix = (Map) JSON.parse(dtoField.interpolation());
            for (Object key : infix.keySet()) {
                Integer index = Integer.valueOf(key.toString());
                if (values.size() >= index)
                    values.add(index, infix.get(key).toString());
            }
        }

        return StringUtils.join(values, dtoField.separator());
    }

    /**
     * 转换列表属性
     *
     * @param source
     * @param dtoField
     * @return
     */
    private static String getListFieldVal(Object source, DtoField dtoField) {
        for (String fieldStr : dtoField.objField()) {
            String[] path = fieldStr.split("\\.");
            String fieldName = path[path.length - 1];
        }
        return null;
    }

    /**
     * 转换字典对象
     *
     * @param source 源对象
     * @param field  字典字段
     * @return
     * @throws IllegalAccessException
     */
    private static String getDictFieldVal(Object source, Field field) throws IllegalAccessException {
        DtoField dtoField = field.getAnnotation(DtoField.class);
        String[] dictInfo = dtoField.dictField().split("\\.");
        String fieldName = dictInfo.length == 2 ? dictInfo[1] : field.getName();
        Field currObj = AnnotationUtils.getFieldByName(source, fieldName);
        if (null == currObj) return null;
        Object val = currObj.get(source);
        return DictCache.getDictLabel(dictInfo[0], (String) val);
    }

    /**
     * @param source dto list
     * @param clzz   vo 类
     * @param <T>    vo 类
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> dto2vo(List source, Class<? extends T> clzz)
            throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        if (0 == source.size()) return list;

        //目标类型中所有字段及字段名称
        List<Field> targetFields = AnnotationUtils.getAllFields(clzz);
        List<String> targetFieldNames = targetFields.stream().map(Field::getName).collect(Collectors.toList());

        //源对象中所有包含DtoField中的字段
        List<Field> sourceFields = AnnotationUtils.getAllAnnotationFields(source.get(0), DtoField.class);
        sourceFields = sourceFields.stream().filter(sf -> targetFieldNames.contains(sf.getName())).collect(Collectors.toList());
        //TODO 先获取所有标注DtoField字段的数据存入List
        // 再把这些名称信息全部获取并存进缓存，设置有效期
        // 首先得设计缓存框架，包括Guava和Redis等等，缓存设计是重中之重！！！
        for (Object o : source) {
            T t = dto2vo(o, clzz);
            list.add(t);
        }
        return list;
    }

    public static <T> T vo2dto(Object source, Class clzz) {
        throw new NotImplementedException("未实现此方法");
    }

    public static <T> List<T> vo2dto(List source, Class clzz) {
        throw new NotImplementedException("未实现此方法");
    }


    @SneakyThrows
    public static Boolean string2bool(Object source, String fieldName) {
        Field field = AnnotationUtils.getFieldByName(source, fieldName);
        DtoField dtoField = field.getAnnotation(DtoField.class);
        BiMap<String, String> converter = HashBiMap.create((Map) JSON.parse(dtoField.converter()));
        return (Boolean.parseBoolean(converter.inverse().get(field.get(source))));
    }

    @SneakyThrows
    public static String bool2string(Boolean val, DtoField dtoField) {
        BiMap<String, String> converter = HashBiMap.create((Map) JSON.parse(dtoField.converter()));
        if (null == val) return null;
        return converter.get(val.toString());
    }
}
