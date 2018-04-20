package com.xw.blog4u.common.reqs;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xuwei
 * @date 2018/4/19
 */
@Getter
@Setter
public class MessageReq {
    private String name;
    private String email;
    private String content;
}
