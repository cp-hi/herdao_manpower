package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import net.herdao.hdp.manpower.sys.injector.utils.TableInfoUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
/**
 * @ClassName SelectEntityName
 * @Description 查询实体名称
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/1 21:58
 * @Version 1.0
 */
public class SelectEntityName extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "with T as (SELECT  %s  FROM  %s WHERE %s=#{%s} AND del_flag!=1) " +
                " select case  when exists (select * from T ) then (select * from T) else '--' end as entity_name ";
        SqlSource sqlSource = new RawSqlSource(this.configuration, String.format(sql, TableInfoUtils.getNameColumn(tableInfo),
                tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty()), Object.class);
        return this.addSelectMappedStatementForOther(mapperClass, "selectEntityName", sqlSource, String.class);
    }
}
