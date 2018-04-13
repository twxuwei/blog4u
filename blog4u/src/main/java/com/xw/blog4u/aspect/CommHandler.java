package com.xw.blog4u.aspect;

import com.xw.blog4u.common.CommResp;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xw
 * @date 2018/4/2
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class CommHandler {

    @Pointcut("execution(public * com.xw.blog4u.controller.*.*(..))")
    public void controller() {

    }

    @Around("controller()")
    public CommResp doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取Http请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取请求方式
        String method = request.getMethod();
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        long startTime = System.currentTimeMillis();

        log.info("request method: " + method + "  request ip: " + ip + "  request url: " + url + "  request start");
        CommResp commResp = (CommResp) joinPoint.proceed();
        log.info("request method: " + method + "  request ip: " + ip + "  request url: " + url + "  time consuming: " + (startTime - System.currentTimeMillis()) + "ms");

        return commResp;
    }
}
