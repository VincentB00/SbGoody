package com.ecommerce.used_good.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController 
{
    @Autowired
    ImageService imageService;

    @DeleteMapping("{imageID}")
    public Response deleteImage(@PathVariable int imageID)
    {
        imageService.deleteById(imageID);
        return new Response(true);
    }
}
