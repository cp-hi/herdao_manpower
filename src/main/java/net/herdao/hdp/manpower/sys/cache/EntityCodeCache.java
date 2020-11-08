package net.herdao.hdp.manpower.sys.cache;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EntityCodeCache
 * @Description EntityCodeCache
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/11/8 9:36
 * @Version 1.0
 */
@Component
public class EntityCodeCache<T> {
    @PostConstruct
    private void init() {


    }

    List<Long> groupIdList = new ArrayList<>();
    List<Map<Class, Map<Long, Integer>>> currEntityCode = new ArrayList<>();

    public static String getEntityCode(Class clazz, Long groupId, Integer count) {
        return null;
    }
}
