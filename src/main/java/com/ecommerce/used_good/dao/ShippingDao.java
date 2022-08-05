package com.ecommerce.used_good.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.used_good.bean.Shipping;
import com.ecommerce.used_good.bean.User;

public interface ShippingDao extends JpaRepository<Shipping, Integer> 
{
    public List<Shipping> findAllByUser(User user);
}
