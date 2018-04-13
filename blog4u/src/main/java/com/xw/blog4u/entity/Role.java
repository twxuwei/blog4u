package com.xw.blog4u.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author xw
 * @date 2018/4/12
 */
@Data
@Entity
public class Role {
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    private String id;
    private String name;

    @JsonIgnoreProperties(value = { "roles" })
    @ManyToMany
    @JoinTable(name="roleUser",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<User> users;
}
