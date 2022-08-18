package com.ecommerce.used_good.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.used_good.bean.Item;

public interface ItemDao extends JpaRepository<Item, Integer> 
{
    @Query(value = "select * from item i where i.user_id = :userID", nativeQuery = true)
    public List<Item> getAllByUserID(@Param("userID") int id);
}
