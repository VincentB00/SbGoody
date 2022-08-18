package com.ecommerce.used_good.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.used_good.bean.Order;

public interface OrderDao extends JpaRepository<Order, Integer> 
{
    public Order findByShippingLabel(String shipping_label);

    @Query(value = "select * from item_order o where o.user_id = :userID", nativeQuery = true)
    public List<Order> getAllByUserID(@Param("userID") int user_id);

    @Query(value = "call getAllSellingOrder(:userID);", nativeQuery = true)
    public List<Order> getAllSellingOrder(@Param("userID") int user_id);
}
