package com.xw.blog4u.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xw
 * @date 2018/4/12
 */
@Data
@Entity
public class Tag implements Serializable{
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    private String id;
    private String tagName;
    private String articleId;
}
