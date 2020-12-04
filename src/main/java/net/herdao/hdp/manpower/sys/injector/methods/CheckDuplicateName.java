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
        String sql = "select case when  exists ( SELECT * FROM %s WHERE del_flag != 1 and %s=#{%s} " +
                " and group_id = #{groupId} and (%s != #{%s} or #{%s} is null) ) then 1 else 0 end  as duplicateName ";
        SqlSource sqlSource = new RawSqlSource(this.configuration, String.format(sql, tableInfo.getTableName(),
                TableInfoUtils.getNameColumn(tableInfo), TableInfoUtils.getNameProperty(tableInfo),
                tableInfo.getKeyColumn(), tableInfo.getKeyProperty(), tableInfo.getKeyProperty()), modelClass);
        return this.addMappedStatement(mapperClass, "checkDuplicateName", sqlSource, SqlCommandType.SELECT,
                modelClass, null, Boolean.class, new NoKeyGenerator(), null, null);
    }
}
