package com.ecommerce.used_good.util;

import java.util.List;

import com.google.gson.Gson;

import javafx.util.Pair;

public class ViewPackers
{
    public static final String BEARER_TOKEN = "Authorization";
    public static final String DOMAIN_NAME = "Domain-name";

    public static final Gson gson = new Gson();

    private ViewPackers() {};

    public static String toJson(Object object)
    {
        return gson.toJson(object);
    }

    public static String toJson(Object object, Gson gson)
    {
        return gson.toJson(object);
    }

    public static String toJson(Object key, Object value)
    {
        if(isNumber(value))
            return String.format("{\"%s\":%s}", key, value);
        else
            return String.format("{\"%s\":\"%s\"}", key, value);
    }

    public static String toJson(List<Pair<Object, Object>> list)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for(int count = 0; count < list.size(); count++)
        {	
            String key = list.get(count).getKey().toString();
            String value = list.get(count).getValue().toString();
            if(count >= (list.size() - 1))
            {
                if(isNumber(value))
                    stringBuilder.append(String.format("\"%s\":%s", key, value));
                else
                    stringBuilder.append(String.format("\"%s\":\"%s\"", key, value));
            }
            else
            {
                if(isNumber(value))
                    stringBuilder.append(String.format("\"%s\":%s,", key, value));
                else
                    stringBuilder.append(String.format("\"%s\":\"%s\",", key, value));
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static boolean isNumber(Object object)
    {
        try
        {
            Double.parseDouble((String) object);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}
