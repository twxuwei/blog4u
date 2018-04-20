package com.xw.blog4u.common.utils;


import com.xw.blog4u.common.CommResp;
import com.xw.blog4u.common.enums.StatusEnum;

/**
 * @author xw
 * @date 2018/3/30
 */
public class CommRespUtil {

    /**
     * 请求成功
     * @param data
     * @return
     */
    public static CommResp responseOk(Object data){
        CommResp result = new CommResp();
        result.setStatus(StatusEnum.OK);
        result.setResult(data);
        return result;
    }

    /**
     * 请求失败
     * @param message
     * @return
     */
    public static CommResp responseFailed(String message){
        CommResp result = new CommResp();
        result.setStatus(StatusEnum.FAILED);
        result.setError(message);
        return result;
    }
}
