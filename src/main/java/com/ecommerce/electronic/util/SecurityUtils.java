package com.ecommerce.electronic.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.electronic.http.Response;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SecurityUtils {
	
	private static final ObjectMapper mapper = new ObjectMapper();

    public static void sendResponse(HttpServletResponse httpResponse, int status, String message, Exception exception) {
        try
        {
            httpResponse.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = httpResponse.getWriter();
            writer.write(mapper.writeValueAsString(new Response(exception == null ? true : false, status, message)));
            httpResponse.setStatus(status);
            writer.flush();
            writer.close();
        }
        catch(IOException ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "IO exception");
        }
    }
	
}
