//package com.xw.blog4u.config;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//
//import java.time.Duration;
//
///**
// * @author xuwei
// * @date 2018/4/24
// */
//@Configuration
//@EnableCaching
//public class CacheConfig extends CachingConfigurerSupport {
//
//    /**
//     * redis缓存管理器
//     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
////        //category信息缓存配置
////        RedisCacheConfiguration categoryCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(60)).disableCachingNullValues().prefixKeysWith("category");
////        //article信息缓存配置
////        RedisCacheConfiguration articleCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(60)).disableCachingNullValues().prefixKeysWith("article");
////        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(16);
////        redisCacheConfigurationMap.put("category", categoryCacheConfiguration);
////        redisCacheConfigurationMap.put("article", articleCacheConfiguration);
//        //初始化一个RedisCacheWriter
//        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
//
//
//        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
//        //ClassLoader loader = this.getClass().getClassLoader();
//        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
//        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
//        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
//
//
//        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
//        //设置默认超过期时间是30秒
//        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
//        //初始化RedisCacheManager
//        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
//        return cacheManager;
//    }
//}
