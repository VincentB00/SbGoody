package com.ecommerce.used_good.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.used_good.bean.Image;

public interface ImageDao extends JpaRepository<Image, Integer> 
{
    public Image findByUrl(String url);
    public void deleteByUrl(String url);
}
