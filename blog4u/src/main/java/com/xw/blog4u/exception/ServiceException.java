package com.xw.blog4u.exception;

import com.xw.blog4u.common.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xw
 * @date 2018/4/3
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {
    private StatusEnum status = StatusEnum.FAILED;
    private String message;

    public ServiceException(String message){
        this.message = message;
    }
}
