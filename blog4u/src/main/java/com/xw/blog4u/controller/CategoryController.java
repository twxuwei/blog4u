package com.xw.blog4u.controller;

import com.xw.blog4u.common.CommResp;
import com.xw.blog4u.common.annotations.Permission;
import com.xw.blog4u.common.enums.RoleEnum;
import com.xw.blog4u.common.utils.CommRespUtil;
import com.xw.blog4u.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuwei
 * @date 2018/4/12
 */
@RestController
@RequestMapping("/categories")
@Api(value = "category controller", tags = "category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @ApiOperation(value = "get all dates")
    public CommResp getAllCategories() {
        return CommRespUtil.responseOk(categoryService.getAllCategories());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete a category")
    @Permission(permission = RoleEnum.ADMIN)
    public CommResp deleteCategory(@PathVariable String id) {
        return CommRespUtil.responseOk(categoryService.deleteCategory(id));
    }

    @PostMapping("/")
    @ApiOperation(value = "add a category")
    @Permission(permission = RoleEnum.ADMIN)
    public CommResp addCategory(@RequestBody String cateName) {
        return CommRespUtil.responseOk(categoryService.addCategory(cateName));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update a category")
    @Permission(permission = RoleEnum.ADMIN)
    public CommResp updateCategory(@PathVariable String id,@RequestBody String name) {
        return CommRespUtil.responseOk(categoryService.updateCategory(id,name));
    }
}
