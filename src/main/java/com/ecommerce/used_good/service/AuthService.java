package com.ecommerce.used_good.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.UserDao;
import com.ecommerce.used_good.http.AuthenticationSuccessResponse;
import com.ecommerce.used_good.http.Response;

@Service
public class AuthService 
{
    @Autowired
    UserDao userDao;

    public Response isLogin(Authentication authentication) {
		if (authentication != null) {
			Response response = new AuthenticationSuccessResponse(true, 200, "Logged In!", userDao.findByUsername(authentication.getName()));
			return response;
		} 
        else 
        {
			return new Response(false);
		}
	}

    public User getCurrentLoginUser(Authentication authentication)
    {
        if (authentication != null) {
			return userDao.findByUsername(authentication.getName());
		} 
        else 
        {
			return null;
		}
    }
}
