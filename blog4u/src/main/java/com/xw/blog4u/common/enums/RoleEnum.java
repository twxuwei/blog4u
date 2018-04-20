package com.xw.blog4u.common.enums;

import lombok.Getter;

/**
 * @author xuwei
 * @date 2018/4/20
 */
@Getter
public enum RoleEnum {
    ADMIN(1,"admin");

    private int code;
    private String name;

    RoleEnum(int code,String name){
        this.code = code;
        this.name = name;
    }
}
