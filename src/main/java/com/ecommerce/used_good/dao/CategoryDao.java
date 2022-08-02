package com.ecommerce.used_good.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.used_good.bean.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>
{
    public Category findByName(String name);
}
