package com.xw.blog4u.service;

import com.xw.blog4u.dao.CategoryDao;
import com.xw.blog4u.entity.Category;
import com.xw.blog4u.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author xuwei
 * @date 2018/4/12
 */
@CacheConfig(cacheNames = "category")
@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    private final static String COMMA = ",";

    /**
     * 获取所有栏目
     *
     * @return
     */
    @Cacheable(key="'categories'")
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    /**
     * 根据id删除一个栏目
     *
     * @param id 栏目id
     * @return
     */
    public String deleteCategory(String id) {
        if(id.contains(COMMA)){
            deleteCategories(id);
        }
        categoryDao.deleteById(id);
        return "success";
    }

    /**
     * 删除多个栏目
     *
     * @param ids 多个栏目 id,id,id
     * @return
     */
    public String deleteCategories(String ids) {
        String[] strings = ids.split(",");
        for (int i =0;i<strings.length;i++) {
            categoryDao.deleteById(strings[i]);
        }
        return "success";
    }

    /**
     * 添加栏目
     *
     * @param name 名字
     * @return
     */
    public String addCategory(String name) {
        Category inner = categoryDao.findByCateName(name);
        if (inner != null) {
            throw new ServiceException("栏目已存在");
        }
        Category category = new Category();
        category.setCateName(name);
        category.setDate(new Timestamp(System.currentTimeMillis()));
        categoryDao.save(category);
        return "success";
    }

    /**
     * 更新栏目
     * @param id id
     * @param name 名字
     * @return
     */
    public String updateCategory(String id,String name){
        Category inner = categoryDao.findByCateName(name);
        if (inner != null) {
            throw new ServiceException("栏目已存在");
        }
        Optional<Category> optionalCategory = categoryDao.findById(id);
        Category category = optionalCategory.get();
        category.setCateName(name);
        category.setDate(new Timestamp(System.currentTimeMillis()));
        categoryDao.save(category);
        return "success";
    }

}
