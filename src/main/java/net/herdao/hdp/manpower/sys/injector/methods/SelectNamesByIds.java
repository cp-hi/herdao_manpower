package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName SelectNamesByIds
 * @Description 根据IDS返回names, map形式
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/22 10:21
 * @Version 1.0
 */
public class SelectNamesByIds extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        //TODO 添加通过 TableField 注解获取 name字段名，目前就简单从表名拼接的方式
        List<TableFieldInfo> fieldInfos = tableInfo.getFieldList().stream().filter(f ->
                f.getEl().contains("Name")).collect(Collectors.toList());
        String name = (0 == fieldInfos.size()) ? "''" : fieldInfos.get(0).getColumn();
        String sql = "<script> SELECT %s,(case when DEL_FLAG !=1 then %s else '--' end) entityName FROM %s WHERE %s IN (%s) </script>";
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, String.format(sql,  tableInfo.getKeyProperty(),name,
                tableInfo.getTableName(), tableInfo.getKeyColumn(), SqlScriptUtils.convertForeach("#{item}", "coll", (String) null, "item", ",")), Map.class);
        return this.addSelectMappedStatementForOther(mapperClass, "selectNamesByIds", sqlSource, Map.class);
    }
}
