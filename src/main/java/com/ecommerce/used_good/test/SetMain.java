package com.ecommerce.used_good.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import com.ecommerce.used_good.service.tool.SimpleHttpClient;

public class SetMain 
{
    public static void main(String[] args) throws ParseException, URISyntaxException, IOException 
    {
        setUpDefault();
    }    

    private static void setUpDefault() throws ParseException, URISyntaxException, IOException
	{
		// String url = "vincentprivatenas.mynetgear.com:7070/reGood/rest";
		String url = "localhost:8080/tools";
		
		SimpleHttpClient client = new SimpleHttpClient(url);
		
		// String body = "{\"ip\":\"192.168.1.6\",\"port\":3309,\"username\":\"vbui\",\"password\":\"123456\",\"schema\":\"Regood\"}";
		
		// System.out.println(client.makePutRequest("/sql", null, body));
		
		String body = "{\"ip\":\"192.168.1.6\",\"port\":21,\"username\":\"frontEnd\",\"password\":\"123456\",\"defaultFolderPath\":\"/FrontEndServer/Public/file_manager/reGood\"}";
		
		System.out.println(client.makePutRequest("/ftp", null, body));
	}
}
