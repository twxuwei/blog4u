package com.xw.blog4u.controller;

import com.xw.blog4u.common.CommResp;
import com.xw.blog4u.common.reqs.ArticleReq;
import com.xw.blog4u.common.utils.CommRespUtil;
import com.xw.blog4u.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xuwei
 * @date 2018/4/12
 */
@RestController
@RequestMapping("/articles")
@Api(value = "article controller", tags = "article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/")
    @ApiOperation(value = "add an article")
    public CommResp add(@RequestBody ArticleReq req){
        return CommRespUtil.responseOk(articleService.add(req));
    }

    @GetMapping("/{page}/{count}")
    @ApiOperation(value = "get pageable articles")
    public CommResp getAllArticles(@PathVariable int page,@PathVariable int count){
        return CommRespUtil.responseOk(articleService.getAllArticles(page,count));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get an article")
    public CommResp getOneArticle(@PathVariable String id){
        return CommRespUtil.responseOk(articleService.getOneArticle(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update an article")
    public CommResp deleteArticle(@PathVariable String id){
        return CommRespUtil.responseOk(articleService.deleteArticle(id));
    }

    @PostMapping("/img")
    @ApiOperation(value = "upload a image")
    public CommResp uploadImage(@RequestParam MultipartFile image){
        return CommRespUtil.responseOk(articleService.uploadFile(image));
    }
}
