package net.herdao.hdp.manpower.sys.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName ApplicationContextBeanUtils
 * @Description ApplicationContextBeanUtils
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/20 13:05
 * @Version 1.0
 */
public class ApplicationContextBeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextBeanUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String className) throws BeansException,IllegalArgumentException {
        if(className==null || className.length()<=0) {
            throw new IllegalArgumentException("className为空");
        }

        String beanName = null;
        if(className.length() > 1) {
            beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
        } else {
            beanName = className.toLowerCase();
        }
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }
}
