package com.xw.blog4u.controller;

import com.xw.blog4u.common.CommResp;
import com.xw.blog4u.common.reqs.UserInfo;
import com.xw.blog4u.common.utils.CommRespUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuwei
 * @date 2018/4/12
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public CommResp login(@RequestBody UserInfo userInfo) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
        subject.login(token);
        return CommRespUtil.responseOk(subject.getSession().getId());
    }



}
