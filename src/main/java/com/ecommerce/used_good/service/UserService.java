package com.ecommerce.used_good.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.bean.UserRole;
import com.ecommerce.used_good.dao.UserDao;
import com.ecommerce.used_good.dao.UserRoleDao;
import com.ecommerce.used_good.security.Sha256PasswordEncoder;
import com.ecommerce.used_good.util.ReplacementUtils;

@Service
public class UserService 
{
    @Autowired
    UserDao userDao;

    @Autowired
    Sha256PasswordEncoder sha256PasswordEncoder;

    @Autowired
    UserRoleDao userRoleDao;

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

    public User getUser(int id)
    {
        return userDao.findById(id).get();
    }

    public void registerUser(User user)
    {
        user.setPassword(sha256PasswordEncoder.encode(user.getPassword()));
        
        UserRole userRole = userRoleDao.findByType("NORMAL");

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);
        
        user.setUserRoles(userRoles);

        userDao.save(user);
    }

    public boolean modifyUser(User originUser, User targetUser)
    {
        int originId = originUser.getId();

        User dbUser = userDao.findById(originId).get();

        Boolean success = ReplacementUtils.replaceValue(dbUser, targetUser);

        if(success)
        {
            dbUser.setId(originId);

            if(targetUser.getPassword() != null)
                dbUser.setPassword(sha256PasswordEncoder.encode(targetUser.getPassword()));

            userDao.save(dbUser);
        }

        return success;
    }
}
