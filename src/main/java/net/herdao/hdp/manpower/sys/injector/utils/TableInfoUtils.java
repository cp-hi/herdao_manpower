package net.herdao.hdp.manpower.sys.injector.utils;

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName TableInfoUtils
 * @Description TableInfoUtils
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/22 10:55
 * @Version 1.0
 */
public class TableInfoUtils {

    public static String getNameProperty(TableInfo tableInfo) {
        TableFieldInfo fieldInfo = getNameFieldInfo(tableInfo);
        return null == fieldInfo ? "''" : fieldInfo.getProperty();
    }

    public static String getCodeProperty(TableInfo tableInfo) {
        TableFieldInfo fieldInfo = getCodeFieldInfo(tableInfo);
        return null == fieldInfo ? "''" : fieldInfo.getProperty();
    }

    public static String getNameColumn(TableInfo tableInfo) {
        TableFieldInfo fieldInfo = getNameFieldInfo(tableInfo);
        return null == fieldInfo ? "''" : fieldInfo.getColumn();
    }

    public static String getCodeColumn(TableInfo tableInfo) {
        TableFieldInfo fieldInfo = getCodeFieldInfo(tableInfo);
        return null == fieldInfo ? "''" : fieldInfo.getColumn();
    }

    public static TableFieldInfo getCodeFieldInfo(TableInfo tableInfo) {
        return getTableFieldInfo(tableInfo, "Code");
    }

    public static TableFieldInfo getNameFieldInfo(TableInfo tableInfo) {
        //TODO 添加通过 TableField 注解获取 name字段名，目前就简单从表名拼接的方式
        return getTableFieldInfo(tableInfo, "Name");
    }

    public static TableFieldInfo getTableFieldInfo(TableInfo tableInfo, final String contains) {
        List<TableFieldInfo> fieldInfos = tableInfo.getFieldList().stream().filter(f ->
                f.getEl().contains(contains)).collect(Collectors.toList());
        if (fieldInfos.size() > 0) return fieldInfos.get(0);
        return null;
    }

}
