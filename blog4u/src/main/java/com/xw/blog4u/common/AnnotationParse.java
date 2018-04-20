package com.xw.blog4u.common;

import com.xw.blog4u.common.annotations.Permission;
import com.xw.blog4u.common.enums.RoleEnum;

import java.lang.reflect.Method;

/** 解析权限注解
 * @author xuwei
 * @date 2018/4/20
 */
public class AnnotationParse {

    /**
     * 通过反射解析自定义的权限注解
     * @param method 被解析的方法
     * @return 被注解方法中permission的值
     */
    public static RoleEnum privilegeParse(Method method){
        if(method.isAnnotationPresent(Permission.class)){
            Permission annotation = method.getAnnotation(Permission.class);
            return annotation.permission();
        }
        return null;
    }
}
