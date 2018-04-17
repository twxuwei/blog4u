package com.xw.blog4u.aspect;

import com.xw.blog4u.common.CommResp;
import com.xw.blog4u.dao.VisitorDao;
import com.xw.blog4u.entity.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xw
 * @date 2018/4/2
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class CommHandler {

    @Autowired
    private VisitorDao visitorDao;

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
        long timeCost = System.currentTimeMillis() - startTime;
        log.info("request method: " + method + "  request ip: " + ip + "  request url: " + url + "  time consuming: " + timeCost + "ms");

        //访问统计
        Visitor visitor = new Visitor();
        visitor.setHost(ip);
        visitor.setMethod(method);
        visitor.setUri(url);
        visitor.setRequestTime(new SimpleDateFormat("yyyy MM:dd HH:mm:ss").format(new Date(startTime)));
        visitor.setTimeCost(timeCost);

//        visitorDao.save(visitor);
        return commResp;
    }
}
