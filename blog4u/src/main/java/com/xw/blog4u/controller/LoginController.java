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
        //设置超时时间3分钟
        subject.getSession().setTimeout(3 * 60 * 1000);

        return CommRespUtil.responseOk(subject.getSession().getId());
    }

    @GetMapping("/logout")
    public CommResp logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return CommRespUtil.responseOk("success");
    }

}
