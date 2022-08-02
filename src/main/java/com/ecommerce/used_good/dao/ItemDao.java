package com.ecommerce.used_good.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.used_good.bean.Item;

public interface ItemDao extends JpaRepository<Item, Integer> 
{
    
}
