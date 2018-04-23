//package com.xw.blog4u.config;
//
//import com.xw.blog4u.exception.ServiceException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author xuwei
// * @date 2018/4/20
// */
//@Component
//public class SessionManager {
//    @Autowired
//    private JedisPool jedisPool;
//
//    private final static String TOKEN = "Admin-Auth-Token";
//
//    /**
//     * 将登陆信息存入redis
//     *
//     * @param key 加密过后的token
//     * @param username 用户名
//     */
//    public void put(String key,String username) {
//        Jedis jedis = jedisPool.getResource();
//        jedis.setex(key, 60,username);
//    }
//
//    /**
//     * 判断是否存在
//     *
//     * @param key
//     * @return
//     */
//    public String get(String key) {
//        Jedis jedis = jedisPool.getResource();
//        if (!jedis.exists(key)) {
//            throw new ServiceException("invalid session.");
//        }
//        jedis.expire(key, 60);
//        return jedis.get(key);
//    }
//
//    /**
//     * 直接获取用户
//     * @return
//     */
//    public String get(){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Jedis jedis = jedisPool.getResource();
//        String key = request.getHeader(TOKEN);
//        if (!jedis.exists(key)) {
//            throw new ServiceException("invalid session.");
//        }
//        jedis.expire(key, 60);
//        return jedis.get(key);
//    }
//
//    /**
//     * 退出会话时删除
//     *
//     * @param key
//     */
//    public void remove(String key) {
//        Jedis jedis = jedisPool.getResource();
//        jedis.del(key);
//    }
//}
