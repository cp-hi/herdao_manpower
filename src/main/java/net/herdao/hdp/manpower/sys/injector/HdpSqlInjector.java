package net.herdao.hdp.manpower.sys.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import net.herdao.hdp.manpower.sys.injector.methods.SelectEntityCode;
import net.herdao.hdp.manpower.sys.injector.methods.SelectEntityName;
import net.herdao.hdp.manpower.sys.injector.methods.SelectIgnoreDel;
import org.springframework.context.annotation.Primary;
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
@Primary
@Component
public class HdpSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new SelectIgnoreDel());
        methodList.add(new SelectEntityCode());
        methodList.add(new SelectEntityName());
        return methodList;
    }
}
