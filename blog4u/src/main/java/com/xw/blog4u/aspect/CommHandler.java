package com.xw.blog4u.aspect;

import com.xw.blog4u.common.AnnotationParse;
import com.xw.blog4u.common.CommResp;
import com.xw.blog4u.common.enums.RoleEnum;
import com.xw.blog4u.dao.VisitorDao;
import com.xw.blog4u.entity.Visitor;
import com.xw.blog4u.exception.ServiceException;
import com.xw.blog4u.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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

    @Autowired
    private UserService userService;

    @Pointcut("execution(public * com.xw.blog4u.controller.*.*(..))")
    public void controller() {

    }

    @Around("controller()")
    public CommResp doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取Http请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //权限校验
//        verify(joinPoint, request);

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
        if (null != SecurityUtils.getSubject().getPrincipal()) {
            visitor.setUsername(SecurityUtils.getSubject().getPrincipal().toString());
        } else {
            visitor.setUsername("visitor");
        }

        visitorDao.save(visitor);
        return commResp;
    }

    /**
     * 权限校验
     *
     * @param joinPoint 当前切点信息
     * @param request   请求信息
     */
//    private void verify(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
//        //获取访问目标方法
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method targetMethod = methodSignature.getMethod();
//
//        //获得被注解方法的权限
//        RoleEnum role = AnnotationParse.privilegeParse(targetMethod);
//        if (role == null) {
//            //不需要权限校验
//            return;
//        }
//        //获取权限校验token
//        String token = request.getHeader("Admin-Auth-Token");
//        if ("".equals(token) || null == token) {
//            throw new ServiceException("Permission denied.");
//        }
//        //1.从redis中获取该token是否有效 reids.getString()
//        //2.token有效 解析token 获得当前用户
//        //3.获取当前用户权限 并与要求的权限比较
//        String username = sessionManager.get(token);
//        String currentRole = userService.findByUsername(username).getRoles().get(0).getName().toLowerCase();
//        if(! role.getName().equals(currentRole)){
//            throw new ServiceException("Permission denied.");
//        }
//    }
}
