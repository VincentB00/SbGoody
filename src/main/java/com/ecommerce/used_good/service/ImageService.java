package com.ecommerce.used_good.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.dao.ImageDao;

@Service
public class ImageService 
{
    @Autowired
    ImageDao imageDao;

    public void deleteByUrl(String url)
    {
        this.imageDao.deleteByUrl(url);
    }

    public void deleteById(int id)
    {
        this.imageDao.deleteById(id);
    }
}
