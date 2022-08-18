package com.ecommerce.used_good.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.Category;
import com.ecommerce.used_good.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController 
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll()
    {
        return this.categoryService.getAll();
    }
}
