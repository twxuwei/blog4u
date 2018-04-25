package com.xw.blog4u.service;

import com.xw.blog4u.common.reqs.ArticleReq;
import com.xw.blog4u.dao.ArticleDao;
import com.xw.blog4u.dao.CategoryDao;
import com.xw.blog4u.dao.TagDao;
import com.xw.blog4u.dao.UserDao;
import com.xw.blog4u.entity.Article;
import com.xw.blog4u.entity.Category;
import com.xw.blog4u.entity.Tag;
import com.xw.blog4u.entity.User;
import com.xw.blog4u.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author xuwei
 * @date 2018/4/12
 */
@CacheConfig(cacheNames = "article")
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private TagDao tagDao;

    @Value("${images.url}")
    private String url;

    @Value("${images.path}")
    private String filepath;

    private final static String COMMA = ",";

    /**
     * 保存文章
     *
     * @param req
     * @return
     */
    public String add(ArticleReq req) {
        Article article;
        if (!articleDao.findById(req.getId()).isPresent()) {
            article = new Article();
        } else {
            article = articleDao.findById(req.getId()).get();
        }

        if (req.getSummary() == null || "".equals(req.getSummary())) {
            //直接截取
            String stripHtml = stripHtml(req.getHtmlContent());
            article.setSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
        }
        //添加操作
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (req.getState() == 1) {
            //设置发表日期
            article.setPublishDate(timestamp);
        }
        article.setEditTime(timestamp);
        //设置当前用户
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userDao.findByUsername(username);
        //获取栏目
        String categoryId = req.getCategoryId();
        Category category = categoryDao.findById(categoryId).get();

        article.setUserId(username);
        article.setHtmlContent(req.getHtmlContent());
        article.setMdContent(req.getMdContent());
        article.setTitle(req.getTitle());
        article.setNickname(user.getNickname());
        article.setCateName(category.getCateName());
        article.setCategoryId(categoryId);
        article.setState(req.getState());
        article.setStateStr(req.getState().toString());
        article.setDynamicTags(req.getDynamicTags());
        articleDao.save(article);
        saveTags(article.getId(), req.getDynamicTags());
        return "success";
    }

    private String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    /**
     * 保存tag
     *
     * @param tags
     */
    @Transactional(rollbackFor = ServiceException.class)
    public void saveTags(String articleId, String[] tags) {
        tagDao.deleteAllByArticleId(articleId);
        for (int i = 0; i < tags.length; i++) {
            if (tagDao.findByTagName(tags[i]) == null) {
                Tag tag = new Tag();
                tag.setTagName(tags[i]);
                tag.setArticleId(articleId);
                tagDao.save(tag);
            }
        }
    }

    /**
     * 分页查找所有文章
     *
     * @param page  页码
     * @param count 数量
     * @return
     */
    public List<Article> getPageableArticles(int page, int count) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, count, sort);
        List<Article> result = articleDao.findByState(pageable, 1).getContent();
        for (Article article : result) {
            article.setTags(tagDao.findByArticleId(article.getId()));
        }
        return result;
    }

    /**
     * 根据栏目名查找
     *
     * @param page
     * @param count
     * @param categoryName
     * @return
     */
    @Cacheable
    public List<Article> getPageableArticlesByCategory(int page, int count, String categoryName) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, count, sort);
        List<Article> result = articleDao.findByStateAndCateName(pageable, 1, categoryName).getContent();
        for (Article article : result) {
            article.setTags(tagDao.findByArticleId(article.getId()));
        }
        return result;
    }

    /**
     * 展示给博客前端页面
     *
     * @return
     */
    @Cacheable
    public List<Article> getAllArticles() {
        return articleDao.findAllByState(1);
    }

    /**
     * 获取一篇文章
     *
     * @param id id
     * @return
     */
    @Cacheable
    public Article getOneArticle(String id) {
        Article article = articleDao.findById(id).get();
        article.setTags(tagDao.findByArticleId(id));
        article.setPageView(article.getPageView() + 1);
        articleDao.save(article);
        return article;
    }

    /**
     * 删除文章 批量/单个删除
     *
     * @param id
     * @return
     */
    public String deleteArticle(String id) {
        if (id.contains(COMMA)) {
            String[] ids = id.split(COMMA);
            for (int i = 0; i < ids.length; i++) {
                Article article = getOneArticle(ids[i]);
                article.setState(2);
                article.setStateStr(2 + "");
                articleDao.save(article);
            }
            return "success";
        }
        Article article = articleDao.findById(id).get();
        article.setState(2);
        article.setStateStr(2 + "");
        articleDao.save(article);
        return "success";
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public String uploadFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String filename = file.getOriginalFilename();
            Path path = Paths.get(filepath + filename);
            Files.write(path, bytes);
            return url + filename;

        } catch (Exception e) {
            throw new ServiceException("upload failed");
        }
    }

    /**
     * 图片下载
     *
     * @param filename
     * @param resp
     */
    public void downloadFile(String filename, HttpServletResponse resp) {
        File file = new File(filepath + filename);
        resp.setHeader("content-type", "application/octet-stream");
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment;filename=" + filename);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<Tag> getAllTags() {
        return tagDao.findAll();
    }

}
