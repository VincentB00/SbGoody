package com.ecommerce.used_good.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.used_good.bean.UserRole;

public interface UserRoleDao extends JpaRepository<UserRole, Integer> 
{
    public UserRole findByType(String type);
}
