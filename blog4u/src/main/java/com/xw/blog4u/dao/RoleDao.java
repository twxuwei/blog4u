package com.xw.blog4u.dao;

import com.xw.blog4u.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xuwei
 * @date 2018/4/12
 */
public interface RoleDao extends JpaRepository<Role,String>{
    /**
     * 根据name查询
     * @param name
     * @return
     */
    Role findByName(String name);
}
