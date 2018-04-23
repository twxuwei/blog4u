//package com.xw.blog4u.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * @author xuwei
// * @date 2018/4/20
// */
//@Configuration
//public class SessionConfig {
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//
//    @Value("${spring.redis.pool.max-idle}")
//    private int maxIdle;
//
//    @Value("${spring.redis.pool.max-wait}")
//    private long maxWaitMillis;
//
//    @Value("${spring.redis.pool.max-active}")
//    private int maxActive;
//
//    @Bean
//    public JedisPool jedisPool() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxActive(maxActive);
//        config.setMaxWait(maxWaitMillis);
//        config.setMaxIdle(maxIdle);
//
//        JedisPool jedisPool = new JedisPool(config, host, port, timeout);
//
//        return jedisPool;
//    }
//}
