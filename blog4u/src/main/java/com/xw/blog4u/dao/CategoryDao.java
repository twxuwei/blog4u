package com.xw.blog4u.dao;

import com.xw.blog4u.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xuwei
 * @date 2018/4/12
 */
public interface CategoryDao extends JpaRepository<Category,String> {

    /**
     * 根据名字查找栏目
     * @param name 名字
     * @return
     */
    Category findByCateName(String name);
}
