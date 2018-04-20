package com.xw.blog4u.common.annotations;

import com.xw.blog4u.common.enums.RoleEnum;

import java.lang.annotation.*;

/**
 * @author xuwei
 * @date 2018/4/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    RoleEnum permission();
}
