package com.ecommerce.used_good.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.ecommerce.used_good.service.AWSService;
import com.ecommerce.used_good.service.AuthService;
import com.ecommerce.used_good.service.ImageService;
import com.ecommerce.used_good.service.ItemService;
import com.ecommerce.used_good.util.ConstantType;
import com.ecommerce.used_good.util.ViewPackers;

@RestController
@RequestMapping("/items")
public class ItemController 
{
    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AWSService awsService;

    @GetMapping
    public List<Item> getAllItems()
    {
        return this.itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable int id, HttpServletResponse res)
    {
        return this.itemService.getItem(id);
    }

    @GetMapping("/is_owner/{itemID}")
    @PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
    public String isOwner(@PathVariable int itemID, Authentication authentication)
    {
        User user = authService.getCurrentLoginUser(authentication);
        boolean isOwner = itemService.isOwner(itemID, user);
        return ViewPackers.toJson("is_owner", isOwner);
    }

    @PostMapping
    @PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
    public Item createItem(@RequestBody Item item, Authentication authentication)
    {
        User user = authService.getCurrentLoginUser(authentication);
        return this.itemService.createItem(item, user);
    }

    @PutMapping("{itemID}")
    @PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
    public Response modifyItem(@PathVariable int itemID, @RequestBody Item item)
    {
        Response response = this.itemService.modifyItem(itemID, item);
        return response;
    }

    @DeleteMapping("{itemID}")
    @PreAuthorize("hasAnyAuthority('ADMIN','OWNER')")
    public Response deleteItem(@PathVariable int itemID)
    {
        awsService.checkSetup();
        this.imageService.deleteAllByItemID(itemID);
        return this.itemService.deleteById(itemID);
    }
}