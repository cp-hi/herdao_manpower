package net.herdao.hdp.manpower.sys.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.collect.BiMap;
import net.herdao.hdp.manpower.mpclient.entity.Group;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CaffeineConfig
 * @Description CaffeineConfig
 * @Author ljan
 * @mail 122092@gdpr.com
 * @Date 2020/12/3 18:40
 * @Version 1.0
 */
@Configuration
public class CaffeineConfig {

    @Bean
    public Cache<String, Map<Long, String>> entityNamesCache() {
        return Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(30, TimeUnit.MINUTES)
                // 初始的缓存空间大小
                .initialCapacity(10000)
                // 缓存的最大条数
                .maximumSize(100000)
                .build();
    }

    @Bean
    public Cache<String, BiMap<String, String>> dictCache() {
        return Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(60, TimeUnit.MINUTES)
                // 初始的缓存空间大小
                .initialCapacity(10000)
                // 缓存的最大条数
                .maximumSize(100000)
                .build();
    }

    @Bean
    public Cache<String, Group> groupCache() {
        return Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(60, TimeUnit.MINUTES)
                // 初始的缓存空间大小
                .initialCapacity(1000)
                // 缓存的最大条数
                .maximumSize(100000)
                .build();
    }


}
