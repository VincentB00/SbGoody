package com.ecommerce.electronic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.electronic.bean.User;
import com.ecommerce.electronic.dao.UserDao;
import com.ecommerce.electronic.security.Sha256PasswordEncoder;

@Service
public class UserService 
{
    @Autowired
    UserDao userDao;

    @Autowired
    Sha256PasswordEncoder sha256PasswordEncoder;

    /**
     * this method will check if username is meet all requirement
     * username will check for
     * 1. does not exist
     * 2. ...
     * @param username username to be check
     * @return true if meet all requirement else false
     */
    public boolean isValidUsername(String username)
    {
        return !isUsernameExist(username);
    }

    /**
     * this method will check if username already exist in database or not
     * @param username username to be check
     * @return return true if exist else false
     */
    public boolean isUsernameExist(String username)
    {   
        List<User> users = userDao.findAllByUsername(username);
        
        return users != null && users.parallelStream().anyMatch(user -> user.getUsername().equals(username));
    }
    
    public List<User> getAll()
    {
        return userDao.findAll();
    }

    public void registerUser(User user)
    {
        user.setPassword(sha256PasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
}
