package com.ecommerce.used_good.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Category;
import com.ecommerce.used_good.bean.Image;
import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.CategoryDao;
import com.ecommerce.used_good.dao.ImageDao;
import com.ecommerce.used_good.dao.ItemDao;
import com.ecommerce.used_good.http.HttpResponseThrowers;
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

    public Item getItem(int id)
    {
        Optional<Item> item = itemDao.findById(id);
        
        if(!item.isPresent())
            HttpResponseThrowers.throwBadRequest("invalid/missing item id");

        return item.get();
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

        List<Integer> deleteImageIDs = new ArrayList<>();

        for(Image image : originalItem.getImages())
        {
            if(!images.parallelStream().anyMatch((i) -> i.getUrl().equals(image.getUrl())))
            {
                deleteImageIDs.add(image.getId());
            }
        }

        ReplacementUtils.replaceValue(originalItem, targetItem);
        originalItem.setImages(images);
        originalItem.setCategories(categories);

        itemDao.save(originalItem);

        imageDao.flush();
        itemDao.flush();
        categoryDao.flush();

        for(int id: deleteImageIDs)
        {
            imageDao.deleteById(id);
        }

        return new Response(true);
    }

    public Response modifyItem(int originalItemID, Item targetItem)
    {
        Item item = itemDao.findById(originalItemID).get();

        if(item != null)
            return this.modifyItem(item, targetItem);
        else
        {
            return (Response) HttpResponseThrowers.throwBadRequest("Invalid origin item");
        }
    }

    public boolean isOwner(Item item, User user)
    {
        if(item == null)
            return false;
        else
            return item.getUser().getId() == user.getId();
    }

    public boolean isOwner(int itemID, User user)
    {
        Optional<Item> optional = itemDao.findById(itemID);

        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("invalid/missing item id");

        return isOwner(optional.get(), user);
    }
}
