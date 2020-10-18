package net.herdao.hdp.manpower.sys.utils;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.mpclient.utils.DateUtils;
import net.herdao.hdp.manpower.sys.annotation.DtoField;
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

/**
 * @ClassName DtoConverter
 * @Description DTO VO ENTITY之间的转换类
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/16 8:31
 * @Version 1.0
 */

@Component
public class DtoConverter {

    private static SysDictItemService sysDictItemService;

    @Autowired
    public void setSysDictItemService(SysDictItemService dictItemService) {
        DtoConverter.sysDictItemService = dictItemService;
    }

    /**
     * @param source dto类
     * @param clzz   vo 类
     * @param <T>    vo 类
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public static <T> T dto2vo(Object source, Class clzz)
            throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, NoSuchFieldException {

        Class clazz = Class.forName(clzz.getName());
        Object target = clazz.newInstance();
        BeanUtils.copyProperties(source, target);
        Field[] fields = AnnotationUtils.getAllAnnotationFields(target, DtoField.class);
        for (Field field : fields) {
            if (null == field) continue;

            field.setAccessible(true);
            //根据 DtoField中的信息获取source中对象数据
            DtoField dtoField = field.getAnnotation(DtoField.class);

//            int total = 0;
//            if (dtoField.joinFields().length > 0) total++;
//            if (StringUtils.isNotBlank(dtoField.objField())) total++;
//            if (StringUtils.isNotBlank(dtoField.listField())) total++;
//            if (StringUtils.isNotBlank(dtoField.dictField())) total++;
//            if (1 != total) throw new RuntimeException("不能设置对象字段同时设置列表字段");

            if (dtoField.joinFields().length > 0 &&
                    StringUtils.isNotBlank(dtoField.joinFields()[0])) {
                String value = "";
                for (String fieldName : dtoField.joinFields()) {
                    Field currObj = AnnotationUtils.getFieldByName(source, fieldName);
                    if (null == currObj) continue;
                    currObj.setAccessible(true);
                    Object val = currObj.get(source);
                    if (null == val) continue;
                    if (java.util.Date.class == val.getClass()) {
                        String date = DateUtils.formatDate((Date) val, "yyyy-MM-dd hh:mm:ss");
                        value += dtoField.symbol() + date;
                    } else {
                        value += dtoField.symbol() + val;
                    }
                }
                value = value.replaceFirst(dtoField.symbol(), "");
                if (StringUtils.isNotBlank(value) &&
                        StringUtils.isNotBlank(dtoField.suffix()))
                    value += dtoField.symbol() + dtoField.suffix();
                field.set(target, value);
            } else if (StringUtils.isNotBlank(dtoField.objField())) {
                String[] path = dtoField.objField().split("\\.");
                Field currObj = source.getClass().getDeclaredField(path[0]);
                if (null == currObj) continue;
                currObj.setAccessible(true);
                Object seq = currObj.get(source);
                if (null != seq) {
                    Field val = AnnotationUtils.getFieldByName(seq, path[1]);
//                    Field val = seq.getClass().getDeclaredField(path[1]);
                    val.setAccessible(true);
                    field.set(target, val.get(seq));
                }
            } else if (StringUtils.isNotBlank(dtoField.dictField())) {
                String[] dictInfo = dtoField.dictField().split("\\.");
                Field currObj = source.getClass().getSuperclass().getDeclaredField(dictInfo[1]);
                if (null == currObj) continue;
                currObj.setAccessible(true);
                Object val = currObj.get(source);
                SysDictItem dictItem = DtoConverter.sysDictItemService.getOne(
                        Wrappers.<SysDictItem>query().lambda()
                                .eq(SysDictItem::getType, dictInfo[0])
                                .eq(SysDictItem::getValue, (String) val));

                if (null != dictItem) field.set(target, dictItem.getLabel());

            } else if (StringUtils.isNotBlank(dtoField.listField())) {

            }
        }
        return (T) target;
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
    public static <T> List<T> dto2vo(List source, Class  clzz)
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
