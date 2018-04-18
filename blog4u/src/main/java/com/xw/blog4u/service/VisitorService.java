package com.xw.blog4u.service;

import com.xw.blog4u.dao.VisitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuwei
 * @date 2018/4/17
 */
@Service
public class VisitorService {
    @Autowired
    private VisitorDao visitorDao;
}
