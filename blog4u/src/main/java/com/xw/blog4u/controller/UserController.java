package com.xw.blog4u.controller;

import com.xw.blog4u.common.CommResp;
import com.xw.blog4u.common.annotations.Permission;
import com.xw.blog4u.common.enums.RoleEnum;
import com.xw.blog4u.common.utils.CommRespUtil;
import com.xw.blog4u.entity.User;
import com.xw.blog4u.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuwei
 * @date 2018/4/12
 */
@RestController
@RequestMapping("/users")
@Api(value = "user controller", tags = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/currentUserName")
    @ApiOperation(value = "get current username")
    public CommResp getCurrentUser() {
        return CommRespUtil.responseOk(SecurityUtils.getSubject().getPrincipal());
    }

    @GetMapping("/isAdmin")
    @ApiOperation(value = "if the current user is admin")
    public CommResp isAdmin() {
        return CommRespUtil.responseOk(userService.isAdmin());
    }

    @GetMapping("/currentUserEmail")
    @ApiOperation(value = "get current user's email")
    public CommResp getCurrentUserEmail() {
        return CommRespUtil.responseOk(userService.currentEmail());
    }

    @GetMapping("/")
    @ApiOperation(value = "get all users")
    public CommResp getAll() {
        return CommRespUtil.responseOk(userService.findAll());
    }

    @GetMapping("/{nickname}")
    @ApiOperation(value = "find by nickname")
    public CommResp getByNickname(@PathVariable String nickname) {
        return CommRespUtil.responseOk(userService.findByNickName(nickname));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get a user by id")
    public CommResp getById(@PathVariable String id) {
        return CommRespUtil.responseOk(userService.findOne(id));
    }

    @GetMapping("/roles")
    @ApiOperation(value = "get all roles")
    public CommResp getAllRoles(){
        return CommRespUtil.responseOk(userService.getAllRoles());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "change a user state.")
    @Permission(permission = RoleEnum.ADMIN)
    public CommResp enabledRole(@PathVariable String id){
        return CommRespUtil.responseOk(userService.enabledRole(id));
    }

    @PutMapping("/{id}/role")
    @ApiOperation(value = "change user's role")
    @Permission(permission = RoleEnum.ADMIN)
    public CommResp updateRole(@PathVariable String id,@RequestBody String[] rids){
        return CommRespUtil.responseOk(userService.updateRole(id,rids));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete a user")
    public CommResp deleteUser(@PathVariable String id){
        return CommRespUtil.responseOk(userService.deleteUser(id));
    }

}
