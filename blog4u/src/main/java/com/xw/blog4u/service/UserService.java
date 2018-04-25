package com.xw.blog4u.service;

import com.xw.blog4u.dao.RoleDao;
import com.xw.blog4u.dao.UserDao;
import com.xw.blog4u.entity.Role;
import com.xw.blog4u.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuwei
 * @date 2018/4/12
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }

    public boolean isAdmin() {
        if (SecurityUtils.getSubject().getPrincipal() == null) {
            return false;
        }
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = findByUsername(username);
        for (Role role : user.getRoles()) {
            if ("Admin".equals(role.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 当前用户Email
     *
     * @return
     */
    public String currentEmail() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = findByUsername(username);
        return user.getEmail();
    }

    /**
     * 根据昵称查找用户
     *
     * @param nickname
     * @return
     */
    public List<User> findByNickName(String nickname) {
        if ("".equals(nickname) || null == nickname) {
            findAll();
        }
        List<User> users = userDao.findByNickname(nickname);
        return users;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 获取一个用户信息
     *
     * @param id
     * @return
     */
    public User findOne(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 获取所有的角色
     *
     * @return
     */
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    /**
     * 禁用/启动账户
     *
     * @return
     */
    public String enabledRole(String id) {
        User user = findOne(id);
        if (user.isEnabled()) {
            user.setEnabled(false);
            userDao.save(user);
            return "success";
        }
        user.setEnabled(true);
        userDao.save(user);
        return "success";
    }

    /**
     * 修改角色
     *
     * @param id
     * @param roles
     * @return
     */
    public String updateRole(String id, String[] roles) {
        User user = findOne(id);
        List<Role> roleList = new ArrayList<>();
        for (int i = 0; i < roles.length; i++) {
            roleList.add(roleDao.findById(roles[i]).get());
        }
        user.setRoles(roleList);
        userDao.save(user);
        return "success";
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public String deleteUser(String id) {
        userDao.deleteById(id);
        return "success";
    }
}
