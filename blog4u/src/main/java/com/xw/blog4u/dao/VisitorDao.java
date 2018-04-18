package com.xw.blog4u.dao;

import com.xw.blog4u.entity.Visitor;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author xuwei
 * @date 2018/4/17
 */
public interface VisitorDao extends MongoRepository<Visitor,String>{
}
