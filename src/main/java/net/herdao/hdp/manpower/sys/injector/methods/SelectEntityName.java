package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import net.herdao.hdp.manpower.mpclient.mapper.EntityMapper;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SelectEntityName
 * @Description 查询实体名称
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/1 21:58
 * @Version 1.0
 */
public class SelectEntityName extends AbstractMethod {
    public SelectEntityName() {
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        //TODO 添加通过 TableField 注解获取 name字段名，目前就简单从表名拼接的方式
        List<TableFieldInfo> fieldInfos = tableInfo.getFieldList().stream().filter(f -> {
            return f.getEl().contains("Name");
        }).collect(Collectors.toList());
        String name = (0 == fieldInfos.size()) ? "''" : fieldInfos.get(0).getColumn();
        SqlSource sqlSource = new RawSqlSource(this.configuration, String.format("SELECT %s FROM %s WHERE %s=#{%s} ",
                name, tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty()), Object.class);
        return this.addSelectMappedStatementForOther(mapperClass, "selectEntityName", sqlSource, String.class);
    }
}
