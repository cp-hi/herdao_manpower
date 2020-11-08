package net.herdao.hdp.manpower.sys.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.var;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.sys.annotation.DtoField;
import net.herdao.hdp.manpower.sys.cache.DictCache;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName DtoConverter
 * @Description DTO VO ENTITY之间的转换类
 * ，配合@DtoField使用
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 8:31
 * @Version 1.0
 */

//@Component
public class DtoConverter {

    private static SysDictItemService sysDictItemService;

    @Autowired
    public void setSysDictItemService(SysDictItemService dictItemService) {
        DtoConverter.sysDictItemService = dictItemService;
    }

    /**
     * @param source dto类
     * @param clazz   vo 类
     * @param <T>    vo 类
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public static <T> T dto2vo(Object source, Class<? extends T> clazz)
            throws IllegalAccessException, InstantiationException {
//        Class clazz = Class.forName(clzz.getName());
        Object target = clazz.newInstance();
        BeanUtils.copyProperties(source, target);
        Field[] fields = AnnotationUtils.getAllAnnotationFields(target, DtoField.class);
        for (Field field : fields) {
            if (null == field) continue;
            field.setAccessible(true);

            //根据 DtoField中的信息获取source中对象数据
            DtoField dtoField = field.getAnnotation(DtoField.class);

            String value = "";
            if (dtoField.objField().length > 0
                    && StringUtils.isNotBlank(dtoField.objField()[0])) {
                value = getDtoFieldVal(source, dtoField);
            } else if (StringUtils.isNotBlank(dtoField.listField())) {
                value = getListFieldVal(source, dtoField);//TODO 完善此方法
            } else if (StringUtils.isNotBlank(dtoField.dictField())) {
                value = getDictFieldVal(source, dtoField);
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
//                field.setAccessible(true);
                currObj = field.get(currObj);
            }
            if (null == currObj) continue;
            Field fieldVal = AnnotationUtils.getFieldByName(currObj, fieldName);
            if (null == fieldVal) continue;
//            fieldVal.setAccessible(true);
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
//                        delField.setAccessible(true);
                        Object del = delField.get(currObj);
                        if ((Boolean) del) value = dtoField.delFix();
                    }
                }
                values.add(value);
            }
        }

        //如果有插值map则插值， 一般用于 xx于 xx创建 xx于 xx更新
        if (StringUtils.isNotBlank(dtoField.mapFix())) {
            Map infix = (Map) JSON.parse(dtoField.mapFix());
            for (Object key : infix.keySet()) {
                Integer index = Integer.valueOf(key.toString());
                if (values.size() >= index)
                    values.add(index, infix.get(key).toString());
            }
        }

        return StringUtils.join(values, dtoField.symbol());
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
     * @param source   源对象
     * @param dtoField 字典字段上的注解
     * @return
     * @throws IllegalAccessException
     */
    private static String getDictFieldVal(Object source, DtoField dtoField) throws IllegalAccessException {
        String[] dictInfo = dtoField.dictField().split("\\.");
        Field currObj = AnnotationUtils.getFieldByName(source, dictInfo[1]);
        if (null == currObj) return null;
//        currObj.setAccessible(true);
        Object val = currObj.get(source);
        return DictCache.getDictLabel(dictInfo[0], (String) val);
//        SysDictItem dictItem = DtoConverter.sysDictItemService.getOne(
//                Wrappers.<SysDictItem>query().lambda()
//                        .eq(SysDictItem::getType, dictInfo[0])
//                        .eq(SysDictItem::getValue, (String) val));
//
//        if (null != dictItem) return dictItem.getLabel();
//        return null;
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
            throws ClassNotFoundException, NoSuchFieldException,
            InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
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
}
