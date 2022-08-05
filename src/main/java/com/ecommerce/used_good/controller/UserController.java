package com.ecommerce.used_good.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;

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

import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.AuthService;
import com.ecommerce.used_good.service.UserService;
import com.ecommerce.used_good.util.ConstantType;
import com.ecommerce.used_good.util.SecurityUtils;

@RestController
@RequestMapping("/users")
public class UserController 
{
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PreAuthorize("hasAnyAuthority('" + ConstantType.USER_ROLE_OWNER + "', '" + ConstantType.USER_ROLE_ADMIN + "')")
    @GetMapping("all")
    public List<User> getAllUser()
    {
        return userService.getAll();
    }

    @GetMapping
    @PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
    public User getCurrentLoginUser(HttpServletResponse response, Authentication authentication)
    {
        User user = authService.getCurrentLoginUser(authentication);
        
        if(user == null)
            return (User) SecurityUtils.sendResponse(response, 401, "unauthoried", null);

        return user;
    }

    @GetMapping("/{username}")
    public Response isUsernameExist(@PathVariable String username)
    {
        if(userService.isUsernameExist(username))
            return new Response(true, "username already exist");
        else
            return new Response(true, 400, "username does not exist");
    }

    @PostMapping
    public Response registerUser(HttpServletResponse response, @RequestBody User user)
    {
        if(!userService.isValidUsername(user.getUsername()))
        {
            return (Response) SecurityUtils.sendResponse(response, 400, "username does not pass check", null);
        }
        else
        {
            userService.registerUser(user);
            return new Response(true);
        }
    }

    @PutMapping
    @PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
    public Response modifyCurrentUser(HttpServletResponse response, Authentication authentication, @RequestBody User user)
    {
        User loginUser = authService.getCurrentLoginUser(authentication);
        
        if(loginUser == null)
            return (Response) SecurityUtils.sendResponse(response, 401, "unauthoried", null);

        return new Response(userService.modifyUser(loginUser, user));
    }

    @PreAuthorize("hasAnyAuthority('" + ConstantType.USER_ROLE_OWNER + "', '" + ConstantType.USER_ROLE_ADMIN + "')")
    @PutMapping("/{id}")
    public Response modifyUser(@PathVariable int id, @RequestBody User user)
    {
        User origin = userService.getUser(id);
        
        if(origin == null)
            return new Response(false, "no origin user found");

        return new Response(userService.modifyUser(origin, user));
    }
}
