package net.herdao.hdp.manpower.sys.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import net.herdao.hdp.manpower.sys.injector.methods.MyDefinedMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MyDefinedSqlInjector
 * @Description MyDefinedSqlInjector
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/29 8:40
 * @Version 1.0
 */
//@Component
public class MyDefinedSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new MyDefinedMethod());
        return methodList;
    }
}
