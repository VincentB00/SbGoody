package com.ecommerce.used_good.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.used_good.bean.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>
{
    public User findByUsername(String username);
    public List<User> findAllByUsername(String username);
}