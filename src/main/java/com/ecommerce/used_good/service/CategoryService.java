package com.ecommerce.used_good.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Category;
import com.ecommerce.used_good.dao.CategoryDao;

@Service
public class CategoryService 
{   
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getAll()
    {
        return this.categoryDao.findAll();
    }
}
