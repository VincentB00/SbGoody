package com.ecommerce.used_good.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.used_good.bean.Image;
import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.AWSService;
import com.ecommerce.used_good.service.AuthService;
import com.ecommerce.used_good.service.ImageService;
import com.ecommerce.used_good.service.ItemService;
import com.ecommerce.used_good.util.ConstantType;

@RestController
@RequestMapping("/images")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class ImageController 
{
    @Autowired
    private ImageService imageService;

    @Autowired
    private AWSService awsService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ItemService itemService;

    @PostMapping("{itemID}")
    public List<Image> uploadImage(@PathVariable int itemID, @RequestPart("images") List<MultipartFile> images, Authentication authentication)
    {
        awsService.checkSetup();
        User user = authService.getCurrentLoginUser(authentication);
        Item item = itemService.getItem(itemID);

        return imageService.uploadImage(images, user, item);
    }

    @PutMapping("{imageID}")
    public Response modifyImage(@PathVariable int imageID, @RequestBody Image image)
    {
        awsService.checkSetup();
        return this.imageService.modifyImage(imageID, image);
    }

    @DeleteMapping("{imageID}")
    public Response deleteImage(@PathVariable int imageID)
    {
        awsService.checkSetup();
        return imageService.deleteById(imageID);
    }
}
