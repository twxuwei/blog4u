package com.xw.blog4u.exception;

import com.xw.blog4u.common.CommResp;
import com.xw.blog4u.common.enums.StatusEnum;
import com.xw.blog4u.common.utils.CommRespUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义异常处理类
 * @author xw
 * @date 2018/4/3
 */
@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    private CommResp handle(Exception exception) {
        if(exception instanceof ServiceException || exception instanceof ShiroException){
            CommResp result = new CommResp();
            result.setStatus(StatusEnum.FAILED);
            result.setError(exception.getMessage());
            log.error(exception.getMessage());
            return result;
        }else {
            log.error(exception.getMessage());
            return CommRespUtil.responseFailed("unknown error.");
        }
    }

}
