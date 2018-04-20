package com.xw.blog4u.service;

import com.xw.blog4u.common.reqs.MessageReq;
import com.xw.blog4u.dao.MessageDao;
import com.xw.blog4u.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author xuwei
 * @date 2018/4/19
 */
@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    /**
     * 获取所有留言
     * @return
     */
    public List<Message> getAllMessages(){
        return messageDao.findAll();
    }

    /**
     * 截取一定的留言
     * @param size
     * @return
     */
    public List<Message> getLimitMessages(int size){
        List<Message> result = getAllMessages();
        if(size > result.size()){
            return getAllMessages();
        }
        return getAllMessages().subList(0,size);
    }

    /**
     * 添加留言
     * @param messageReq
     * @return
     */
    public String addMessage(MessageReq messageReq){
        Message message = new Message();
        message.setContent(messageReq.getContent().replaceAll("爸爸","儿子").replaceAll("dad","son"));
        message.setEmail(messageReq.getEmail());
        message.setName(messageReq.getName());
        message.setCreateDate(new SimpleDateFormat().format(new Date()));
        messageDao.save(message);
        return "success";
    }
}
