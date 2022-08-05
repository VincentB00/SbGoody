package com.ecommerce.used_good.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Category;
import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.CategoryDao;
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
    private CategoryDao categoryDao;

    public List<Item> getAllItems()
    {
        return itemDao.findAll();
    }

    public Item getItem(int id)
    {
        Optional<Item> item = itemDao.findById(id);
        
        if(!item.isPresent())
            HttpResponseThrowers.throwBadRequest("invalid or missing item id");

        return item.get();
    }

    public Item createItem(Item item, User user)
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

        item.setCategories(categories);
        item.setUser(user);

        item = itemDao.save(item);

        return item;
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

        ReplacementUtils.replaceValue(originalItem, targetItem);
        originalItem.setCategories(categories);

        itemDao.save(originalItem);

        return new Response(true, "item modify success");
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
        return isOwner(this.getItem(itemID), user);
    }

    public void checkIsOwner(int itemID, User user)
    {
        if(!isOwner(itemID, user))
            HttpResponseThrowers.throwBadRequest("Item does not belong to user");
    }

    public Response deleteById(int id)
    {
        this.itemDao.deleteById(id);
        return new Response(true, "Delete item: " + id + " success");
    }
}
