package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CheckDuplicateName
 * @Description 校验重复名字
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/2 8:41
 * @Version 1.0
 */
public class CheckDuplicateName extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        List<TableFieldInfo> fieldInfos = tableInfo.getFieldList().stream().filter(f -> {
            return f.getEl().contains("Name");
        }).collect(Collectors.toList());
        if (0 == fieldInfos.size()) return null;
        TableFieldInfo fieldInfo = fieldInfos.get(0);
        String sql = "select case when  exists ( SELECT * FROM %s WHERE %s=#{%s} and group_id = #{groupId} and (%s != #{%s} or #{%s} is null) ) then 1 else 0 end  as duplicateName ";
        SqlSource sqlSource = new RawSqlSource(this.configuration, String.format(sql, tableInfo.getTableName(), fieldInfo.getColumn(),
                fieldInfo.getEl(),  tableInfo.getKeyColumn(), tableInfo.getKeyProperty(), tableInfo.getKeyProperty()), modelClass);
        return this.addMappedStatement(mapperClass, "checkDuplicateName", sqlSource, SqlCommandType.SELECT,
                modelClass, (String) null, Boolean.class, new NoKeyGenerator(), (String) null, (String) null);
    }
}
