package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import net.herdao.hdp.manpower.sys.injector.utils.TableInfoUtils;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

/**
 * @ClassName GetLastEntity
 * @Description 获取最新记录，用于生成编码
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/2 14:39
 * @Version 1.0
 */
public class GetLastEntityCode extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "SELECT IFNULL( %s,'000000') FROM %s WHERE %s=(select max(%s) from %s where group_id=#{groupId}) ";
        SqlSource sqlSource = new RawSqlSource(this.configuration, String.format(sql, TableInfoUtils.getCodeColumn(tableInfo),
                tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyColumn(),
                tableInfo.getTableName()), Object.class);
        return this.addMappedStatement(mapperClass, "getLastEntityCode", sqlSource, SqlCommandType.SELECT,
                modelClass, null, String.class, new NoKeyGenerator(), null, null);
    }
}
