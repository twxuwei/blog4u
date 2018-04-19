package com.xw.blog4u.dao;

import com.xw.blog4u.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xuwei
 * @date 2018/4/12
 */
public interface ArticleDao extends JpaRepository<Article,String> {
    /**
     * 分页查询
     * @param pageable 分页信息
     * @param state 状态
     * @return
     */
    Page<Article> findByState(Pageable pageable,int state);

    /**
     * 根据栏目名称查找
     * @param pageable
     * @param state
     * @param cateName
     * @return
     */
    Page<Article> findByStateAndCateName(Pageable pageable,int state,String cateName);
    /**
     * 查询所有文章
     * @param state 状态
     * @return
     */
    List<Article> findAllByState(int state);
}
