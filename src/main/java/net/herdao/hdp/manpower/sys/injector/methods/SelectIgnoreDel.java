package net.herdao.hdp.manpower.sys.injector.methods;


import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

/**
 * @ClassName SelectIgnoreDel
 * @Description 通过ID查询，忽略逻辑删除
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/29 20:56
 * @Version 1.0
 */
public class SelectIgnoreDel extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlSource sqlSource = new RawSqlSource(this.configuration, String.format("SELECT %s FROM %s WHERE %s=#{%s} ",
                this.sqlSelectColumns(tableInfo, false), tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty() ), Object.class);
        return this.addSelectMappedStatementForTable(mapperClass, "selectIgnoreDel", sqlSource, tableInfo);
    }
}
