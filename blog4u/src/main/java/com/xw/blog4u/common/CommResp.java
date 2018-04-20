package com.xw.blog4u.common;

import com.xw.blog4u.common.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xw
 * @date 2018/3/30
 */
@Getter
@Setter
public class CommResp {
    private StatusEnum status;
    private String error;
    private Object result;
}
