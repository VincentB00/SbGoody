package com.ecommerce.used_good.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.used_good.bean.ReportedItem;

public interface ReportedItemDao extends JpaRepository<ReportedItem, Integer>
{
    
}
