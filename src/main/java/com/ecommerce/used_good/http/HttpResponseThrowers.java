package com.ecommerce.used_good.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HttpResponseThrowers 
{
    public static Object throwBadRequest(String message)
    {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }    
}
