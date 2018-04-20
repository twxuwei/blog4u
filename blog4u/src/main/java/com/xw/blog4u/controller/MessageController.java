package com.xw.blog4u.controller;

import com.xw.blog4u.common.CommResp;
import com.xw.blog4u.common.reqs.MessageReq;
import com.xw.blog4u.common.utils.CommRespUtil;
import com.xw.blog4u.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuwei
 * @date 2018/4/19
 */
@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("")
    public CommResp getAllMessages(){
        return CommRespUtil.responseOk(messageService.getAllMessages());
    }

    @GetMapping("/{size}")
    public CommResp getLimitMessages(@PathVariable int size){
        return CommRespUtil.responseOk(messageService.getAllMessages());
    }

    @PostMapping("")
    public CommResp addMessage(@RequestBody MessageReq messageReq){
        return CommRespUtil.responseOk(messageService.addMessage(messageReq));
    }
}
