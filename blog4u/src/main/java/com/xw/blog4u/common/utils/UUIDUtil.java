package com.xw.blog4u.common.utils;

import java.util.UUID;

/**
 * @author xw
 * @date 2018/4/2
 */
public class UUIDUtil {

    /**
     * 小写的32位uuid
     * @return
     */
    public static String getLowerCase(){
        return UUID.randomUUID().toString().replaceAll("-","").toLowerCase();
    }

    /**
     * 大写的32位uuid
     * @return
     */
    public static String getUpperCase(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }
}
