package net.herdao.hdp.manpower.sys.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import net.herdao.hdp.manpower.sys.injector.methods.SelectIgnoreLogic;

import java.util.List;

/**
 * @ClassName HerdaoSqlInjector
 * @Description HerdaoSqlInjector
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/10/28 11:21
 * @Version 1.0
 */

public class HerdaoSqlInjector extends DefaultSqlInjector {
    public HerdaoSqlInjector() {
    }

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> list = super.getMethodList(mapperClass);
        list.add(new SelectIgnoreLogic());
        return list;
    }
}
