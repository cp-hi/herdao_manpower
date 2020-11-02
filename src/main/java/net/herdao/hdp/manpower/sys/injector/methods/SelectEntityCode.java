package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SelectEntityCode
 * @Description 查询实体编码
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/1 21:59
 * @Version 1.0
 */
public class SelectEntityCode extends AbstractMethod {
    public SelectEntityCode() {
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        List<TableFieldInfo> fieldInfos = tableInfo.getFieldList().stream().filter(f -> {
            return f.getEl().contains("Code");
        }).collect(Collectors.toList());
        String code = (0 == fieldInfos.size()) ? "''" : fieldInfos.get(0).getColumn();
        SqlSource sqlSource = new RawSqlSource(this.configuration, String.format("SELECT %s FROM %s WHERE %s=#{%s} ",
                code, tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty()), Object.class);
        return this.addSelectMappedStatementForOther(mapperClass, "selectEntityCode", sqlSource, String.class);
    }
}
