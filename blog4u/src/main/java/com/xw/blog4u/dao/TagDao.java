package com.xw.blog4u.dao;

import com.xw.blog4u.entity.Tag;
import com.xw.blog4u.exception.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author xuwei
 * @date 2018/4/13
 */
public interface TagDao extends JpaRepository<Tag,String>{
    /**
     * 根据文章id查找
     * @param articleId
     * @return
     */
    Set<Tag> findByArticleId(String articleId);

    /**
     * 根据文章id删除
     * @param articleId
     */
    @Transactional(rollbackFor = ServiceException.class)
    void deleteAllByArticleId(String articleId);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    Tag findByTagName(String name);
}
