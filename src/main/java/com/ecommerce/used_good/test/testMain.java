package com.ecommerce.used_good.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;

import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.util.ReplacementUtils;

public class testMain 
{
    public static void main(String[] args) throws ParseException, URISyntaxException, IOException 
    {
        // User user1 = new User(1, "Vincent", "Vincent", "Vincent", "Vincent", "Vincent", "Vincent@gmail.com", "NORMAL", null, null);    
        // User user2 = new User(10, "Bob", "Bob", "Bob", "Bob", "Bob", "Bob@gmail.com", "NORMAL", null, null);    
        // user2.setUserRoles(new ArrayList<>());
        
        // // UserRole userRole = new UserRole(1, "type", "description"); 

        // System.out.println(ReplacementUtils.replaceValue(user1, user2)); 
        
        // System.out.println(user1);

        String fileName = "so-me/thi\\ng.co.m..exe";

        System.out.println(formatFileName(fileName));
    }    

    private static String formatFileName(String fileName)
    {
        StringBuilder stringBuilder = new StringBuilder();

        String fileExtention = getFileExtention(fileName);

        fileName = fileName.substring(0, fileName.indexOf(fileExtention));

        System.out.println(fileName);

        for(int size = fileName.length(), i = 0; i < size; i++) 
        {
            char c = fileName.charAt(i);
            if(c == '.' || c == '/' || c == '\\' || c == '-' || c == '_')
            {
                if((i + 1) < size)
                {
                    stringBuilder.append((fileName.charAt(i + 1) + "").toUpperCase());
                    i++;
                }
            }
            else
                stringBuilder.append(c);
        }

        stringBuilder.append(fileExtention);

        return stringBuilder.toString();
    }

    private static String getFileExtention(String fileName)
    {
        String extention = "";
        int index = 0;

        for (int i = 0; i < fileName.length(); i++) 
        {
            if(fileName.charAt(i) == '.')
                index = i;
        }

        if(index > 0)
        {
            extention = fileName.substring(index);
            return extention;
        }
        else
            return extention; 
    }   
}
