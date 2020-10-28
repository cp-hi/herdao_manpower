package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

/**
 * @ClassName SelectIgornDel
 * @Description SelectIgornDel
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/28 11:24
 * @Version 1.0
 */
public class SelectIgnoreLogic extends AbstractMethod {
    public SelectIgnoreLogic() {
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_BY_ID;
        SqlSource sqlSource = new RawSqlSource(this.configuration, String.format(sqlMethod.getSql(), this.sqlSelectColumns(tableInfo, false), tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty()), Object.class);
        return this.addSelectMappedStatementForTable(mapperClass, this.getMethod(sqlMethod), sqlSource, tableInfo);
    }
}
