package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName GetEntityByName
 * @Description 通过名称获取实体
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/3 9:16
 * @Version 1.0
 */
public class GetEntityByName extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        List<TableFieldInfo> fieldInfos = tableInfo.getFieldList().stream().filter(f -> {
            return f.getEl().contains("Name");
        }).collect(Collectors.toList());

        if (0 == fieldInfos.size()) return null;

        SqlSource sqlSource = new RawSqlSource(this.configuration, String.format("SELECT %s FROM %s WHERE %s=#{arg0} and group_id=#{arg1} limit 1",
                this.sqlSelectColumns(tableInfo, false), tableInfo.getTableName(), fieldInfos.get(0).getColumn()), null);
        return this.addSelectMappedStatementForTable(mapperClass, "getEntityByName", sqlSource, tableInfo);
    }
}
