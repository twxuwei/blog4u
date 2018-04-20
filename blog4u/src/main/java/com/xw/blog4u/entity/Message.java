package com.xw.blog4u.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xuwei
 * @date 2018/4/19
 */
@Entity
@Data
@Table(name = "message")
public class Message {
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    private String id;

    private String content;

    private String createDate;

    private String email;

    private String name;
}
