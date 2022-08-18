package com.ecommerce.used_good.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.used_good.bean.Card;

public interface CardDao extends JpaRepository<Card, Integer> 
{
    @Query(value = "select * from card c where c.user_id = :userID", nativeQuery = true)
    public List<Card> getAllByUserID(@Param("userID") int id);
}
