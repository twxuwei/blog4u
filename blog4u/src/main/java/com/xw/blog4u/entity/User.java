package com.xw.blog4u.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author xw
 * @date 2018/4/12
 */
@Data
@Entity
public class User {
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    private String id;

    @Column(unique = true)
    private String username;

    private String password;

    /**
     * 加密密码的盐
     */
    private String salt;

    private String nickname;
    private boolean enabled;
    private String email;
    private String userFace;
    private Timestamp regTime;

    @JsonIgnoreProperties(value = { "users" })
    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "roleUser", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    /**
     * 用户-角色 多对多
     */
    private List<Role> roles;

}
