package com.ecommerce.used_good.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.used_good.bean.Image;

public interface ImageDao extends JpaRepository<Image, Integer> 
{
    public Image findByUrl(String url);
    public void deleteByUrl(String url);
    
    @Query(value = "select * from image i where i.item_id = :itemID", nativeQuery = true)
    public List<Image> getAllByItemID(@Param("itemID") int id);
}
