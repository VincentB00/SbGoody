package com.ecommerce.used_good.test;

import java.util.ArrayList;

import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.bean.UserRole;
import com.ecommerce.used_good.util.ReplacementUtils;

public class testMain 
{
    public static void main(String[] args) 
    {
        User user1 = new User(1, "Vincent", "Vincent", "Vincent", "Vincent", "Vincent", "Vincent@gmail.com", "NORMAL", null, null);    
        User user2 = new User(0, "Bob", "Bob", "Bob", "Bob", "Bob", "Bob@gmail.com", "NORMAL", null, null);    
        user2.setUserRoles(new ArrayList<>());
        
        // UserRole userRole = new UserRole(1, "type", "description"); 

        System.out.println(ReplacementUtils.replaceValue(user1, user2)); 
        
        

        System.out.println(user1);
    }    
}
