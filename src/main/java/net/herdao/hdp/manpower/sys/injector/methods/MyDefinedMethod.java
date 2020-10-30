package net.herdao.hdp.manpower.sys.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @ClassName MyDefinedMethod
 * @Description MyDefinedMethod
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/29 8:41
 * @Version 1.0
 */
@Deprecated
public class MyDefinedMethod extends AbstractMethod {
    //    @Override
//    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
//        return null;
//    }
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String myDefineSql = "update " + tableInfo.getTableName() +" set del_flag = 1";//构造一条delete from 表，待注入的sql预编译语句
        String methodName = "deleteAll";//方法名 -->这个就是在UserMapper中需要添加的方法名
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, myDefineSql, modelClass);// 进行预编译得到sqlSource对象
        //添加到delete操作的Statement中也就是相当于将预编译sql和其它的delete相关的编译后的sql存在一起
        return addDeleteMappedStatement(mapperClass, methodName, sqlSource);
    }
}
