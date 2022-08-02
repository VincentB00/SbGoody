package com.ecommerce.used_good.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.AuthService;
import com.ecommerce.used_good.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController 
{
    @Autowired
    ItemService itemService;

    @Autowired
    AuthService authService;

    @GetMapping
    public List<Item> getAllItems()
    {
        return this.itemService.getAllItems();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('NORMAL', 'ADMIN', 'OWNER')")
    public Response createItem(@RequestBody Item item, Authentication authentication)
    {
        User user = authService.getCurrentLoginUser(authentication);
        Response response = this.itemService.createItem(item, user);
        return response;
    }

    @PutMapping("{itemID}")
    @PreAuthorize("hasAnyAuthority('NORMAL', 'ADMIN', 'OWNER')")
    public Response modifyItem(@PathVariable int itemID, @RequestBody Item item)
    {
        Response response = this.itemService.modifyItem(itemID, item);
        return response;
    }
}