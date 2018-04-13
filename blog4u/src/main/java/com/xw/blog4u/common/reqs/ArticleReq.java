package com.xw.blog4u.common.reqs;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xuwei
 * @date 2018/4/12
 */
@Getter
@Setter
public class ArticleReq {

    private String id;
    /**
     * 标题
     */
    private String title;

    /**
     * markdown源文件
     */
    private String mdContent;
    /**
     * html源文件
     */
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
     * 状态 0表示草稿箱，1表示已发表，2表示已删除
     */
    private Integer state;

    private String[] dynamicTags;

}
