package com.xw.blog4u.dao;

import com.xw.blog4u.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xuwei
 * @date 2018/4/19
 */
public interface MessageDao extends JpaRepository<Message, String> {
}
