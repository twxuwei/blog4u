package com.xw.blog4u.dao;

import com.xw.blog4u.entity.Role;
import com.xw.blog4u.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author xuwei
 * @date 2018/4/12
 */
public interface UserDao extends JpaRepository<User,String>{
    /**
     * 登录验证spring security)
     * @param username 用户名
     * @return
     */
    User findByUsername(String username);

    List<User> findByNickname(String nickname);
}
