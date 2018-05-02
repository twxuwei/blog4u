package com.xw.blog4u.entity;

import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @author xuwei
 * @date 2018/4/17
 */
@Data
@Document(collection = "visitor")
public class Visitor {
    @Id
    private String id;

    private String host;

    private String username;

    private String date;
}
