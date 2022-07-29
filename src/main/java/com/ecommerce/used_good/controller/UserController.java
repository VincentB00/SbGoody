package com.ecommerce.used_good.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.UserService;
import com.ecommerce.used_good.util.SecurityUtils;

@RestController
@RequestMapping("/users")
public class UserController 
{
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUser()
    {
        return userService.getAll();
    }

    @GetMapping("/{username}")
    public Response isUsernameExist(@PathVariable String username)
    {
        if(userService.isUsernameExist(username))
            return new Response(true, "username already exist");
        else
            return new Response(false, 200, "username does not exist");
    }

    @PostMapping
    public void registerUser(HttpServletResponse response, @RequestBody User user)
    {
        if(!userService.isValidUsername(user.getUsername()))
        {
            SecurityUtils.sendResponse(response, 400, "username does not pass check", null);
            return;
        }
        
        userService.registerUser(user);
    }
}
