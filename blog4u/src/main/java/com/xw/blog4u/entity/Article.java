package com.xw.blog4u.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author xw
 * @date 2018/4/12
 */
@Data
@Entity
public class Article {
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    private String id;
    /**
     * 标题
     */
    private String title;

    /**
     * markdown源文件
     */
    @Lob
    @Column(columnDefinition="TEXT")
    private String mdContent;
    /**
     * html源文件
     */
    @Lob
    @Column(columnDefinition="TEXT")
    private String htmlContent;
    /**
     * 概要
     */
    private String summary;
    /**
     * 类别
     */
    private String categoryId;
    /**
     * 作者
     */
    private String userId;
    /**
     * 发布时间
     */
    private Timestamp publishDate;
    /**
     * 状态 0表示草稿箱，1表示已发表，2表示已删除
     */
    private Integer state;
    private Integer pageView = 0;
    /**
     * 编辑时间
     */
    private Timestamp editTime;
    private String[] dynamicTags;
    private String nickname;
    private String cateName;

    @Transient
    private Set<Tag> tags;

    private String stateStr;
}
