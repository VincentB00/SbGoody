package com.ecommerce.used_good.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Category;
import com.ecommerce.used_good.bean.Image;
import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.CategoryDao;
import com.ecommerce.used_good.dao.ImageDao;
import com.ecommerce.used_good.dao.ItemDao;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.util.ReplacementUtils;

@Service
public class ItemService 
{
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private CategoryDao categoryDao;

    public List<Item> getAllItems()
    {
        return itemDao.findAll();
    }

    public Response createItem(Item item, User user)
    {
        List<Category> categories = new ArrayList<>();
        for (Category categoryT : item.getCategories()) 
        {
            Category category = categoryDao.findByName(categoryT.getName());
            
            if(category != null)
            {
                categories.add(category);
            }
            else
            {
                category = categoryDao.save(categoryT);
                categories.add(category);
            }
        }

        List<Image> images = new ArrayList<>();
        for(Image imageT : item.getImages())
        {
            Image image = imageDao.findByUrl(imageT.getUrl());
            
            if(image != null)
            {
                images.add(image);
            }
            else
            {
                image = imageDao.save(imageT);
                images.add(image);
            }
        }

        item.setCategories(categories);
        item.setImages(images);
        item.setUser(user);

        System.out.println(item);
        item = itemDao.save(item);
        
        for(Image image : images)
        {
            image.setItem(item);

            imageDao.save(image);
        }

        return new Response(true);
    }

    public Response modifyItem(Item originalItem, Item targetItem)
    {
        List<Category> categories = new ArrayList<>();
        for (Category categoryT : targetItem.getCategories()) 
        {
            Category category = categoryDao.findByName(categoryT.getName());
            
            if(category != null)
            {
                categories.add(category);
            }
            else
            {
                category = categoryDao.save(categoryT);
                categories.add(category);
            }
        }

        List<Image> images = new ArrayList<>();
        for(Image imageT : targetItem.getImages())
        {
            Image image = imageDao.findByUrl(imageT.getUrl());
            
            if(image != null)
            {
                images.add(image);
            }
            else
            {
                image = imageDao.save(imageT);
                images.add(image);
            }
        }

        

        for(Image image : images)
        {
            image.setItem(originalItem);

            imageDao.save(image);
        }

        imageDao.flush();
        itemDao.flush();
        categoryDao.flush();

        for(Image image : originalItem.getImages())
        {
            if(!images.parallelStream().anyMatch((i) -> i.getUrl().equals(image.getUrl())))
            {
                System.out.println(image);
                imageDao.deleteById(image.getId());
            }
        }

        ReplacementUtils.replaceValue(originalItem, targetItem);
        originalItem.setImages(images);
        originalItem.setCategories(categories);

        itemDao.save(originalItem);

        

        return new Response(true);
    }

    public Response modifyItem(int originalItemID, Item targetItem)
    {
        Item item = itemDao.findById(originalItemID).get();

        if(item != null)
            return this.modifyItem(item, targetItem);
        else
            return new Response(false, "Invalid origin item");
    }
}
