package com.ecommerce.used_good.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.used_good.bean.Offer;

public interface OfferDao extends JpaRepository<Offer, Integer> 
{
    @Query(value = "select * from offer o where o.item_id = :itemID", nativeQuery = true)
    public List<Offer> getAllByItemID(@Param("itemID") int id);
}
