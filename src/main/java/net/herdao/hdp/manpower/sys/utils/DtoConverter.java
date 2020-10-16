package net.herdao.hdp.manpower.sys.utils;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.Setter;
import net.herdao.hdp.admin.api.entity.SysDictItem;
import net.herdao.hdp.manpower.sys.annotation.DtoField;
import net.herdao.hdp.manpower.sys.service.SysDictItemService;
import net.herdao.hdp.manpower.sys.service.SysDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
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

    public static SysDictItemService sysDictItemService;

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
            //根据 DtoField中的信息获取source中对象数据
            DtoField dtoField = field.getAnnotation(DtoField.class);
            int total = 0;
            if (StringUtils.isNotBlank(dtoField.objField())) total++;
            if (StringUtils.isNotBlank(dtoField.listField())) total++;
            if (StringUtils.isNotBlank(dtoField.dictField())) total++;
            if (1 != total) throw new RuntimeException("不能设置对象字段同时设置列表字段");

            if (StringUtils.isNotBlank(dtoField.objField())) {
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
            } else if (StringUtils.isNotBlank(dtoField.dictField())) {
                String[] dictInfo = dtoField.dictField().split("\\.");
                Field currObj = source.getClass().getSuperclass().getDeclaredField(dictInfo[1]);
                if (null == currObj) continue;
                currObj.setAccessible(true);
                Object val = currObj.get(source);
                SysDictItem dictItem = sysDictItemService.getOne(Wrappers.<SysDictItem>query().lambda()
                        .eq(SysDictItem::getType, dictInfo[0])
                        .eq(SysDictItem::getValue, (String) val));

                if (null != dictItem) field.set(t, dictItem.getLabel());

            } else if (StringUtils.isNotBlank(dtoField.listField())) {

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
