package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import net.herdao.hdp.manpower.sys.injector.utils.TableInfoUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import java.util.Map;

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
        String sql = "<script> SELECT %s,(case when DEL_FLAG !=1 then %s else '--' end) entityName FROM %s WHERE %s IN (%s) </script>";
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, String.format(sql, tableInfo.getKeyProperty(),
                TableInfoUtils.getNameColumn(tableInfo), tableInfo.getTableName(), tableInfo.getKeyColumn(),
                SqlScriptUtils.convertForeach("#{item}", "coll", (String) null, "item", ",")), Map.class);
        return this.addSelectMappedStatementForOther(mapperClass, "selectNamesByIds", sqlSource, Map.class);
    }
}
