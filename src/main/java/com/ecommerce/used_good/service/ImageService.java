package com.ecommerce.used_good.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.codecommit.model.File;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.ecommerce.used_good.bean.Image;
import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.ImageDao;
import com.ecommerce.used_good.http.HttpResponseThrowers;
import com.ecommerce.used_good.http.Response;

@Service
public class ImageService 
{
    @Autowired
    private ImageDao imageDao;

    @Autowired
    private AWSService awsService;

    public Image getById(int id)
    {
        Optional<Image> optional = imageDao.findById(id);

        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("invalid or missing image id");

        return optional.get();
    }

    public List<Image> uploadImage(List<MultipartFile> images, User user, Item item)
    {
        List<Image> imageList = new ArrayList<>();

        for (MultipartFile image : images) 
        {
            String fileExtention = getFileExtention(image.getOriginalFilename()).toLowerCase();

            switch(fileExtention)
            {
                case ".jpg":
                case ".png":
                case ".jpeg":
                    break;
                default:
                    HttpResponseThrowers.throwBadRequest("images are not in image format, current image format:" + fileExtention);
            }
            
            // if(!fileExtention.equals(".jpg") || !fileExtention.equals(".png") || !fileExtention.equals(".jpeg"))
            //     HttpResponseThrowers.throwBadRequest("images are not in image format, current image format:" + fileExtention);

        }

        for (MultipartFile image : images) 
        {
            String fileName = String.format("user%s-%s", user.getId(), formatFileName(image.getOriginalFilename()));
            String absoluteLocation = String.format("files/%s", fileName);

            try 
            {
                awsService.uploadObject(absoluteLocation, image.getInputStream());

                Image imageD = new Image();
                imageD.setFile_name(fileName);
                imageD.setUrl(awsService.getURL(absoluteLocation));
                imageD.setItem(item);
                imageD.setSize(image.getSize());

                imageDao.save(imageD);

                imageList.add(imageD);
            } 
            catch (Exception e) 
            {
                HttpResponseThrowers.throwServerError(e.getMessage());
            }
        }

        

        return imageList;
    }

    private String formatFileName(String fileName)
    {
        StringBuilder stringBuilder = new StringBuilder();

        String fileExtention = getFileExtention(fileName);

        fileName = fileName.substring(0, fileName.indexOf(fileExtention));

        System.out.println(fileName);

        for(int size = fileName.length(), i = 0; i < size; i++) 
        {
            char c = fileName.charAt(i);
            if(c == '.' || c == '/' || c == '\\' || c == '-' || c == '_' || c == ' ')
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

    private String getFileExtention(String fileName)
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

    public Response modifyImage(int imageID, Image target)
    {
        return this.modifyImage(this.getById(imageID), target);
    }

    public Response modifyImage(Image originImage, Image target)
    {
        originImage = this.getById(originImage.getId());

        if(!target.getFile_name().equals(originImage.getFile_name()))
        {
            String absoluteLocation = String.format("files/%s", originImage.getFile_name());
            String newAbsoluteLocation = String.format("files/%s", target.getFile_name());
            awsService.renameObject(absoluteLocation, newAbsoluteLocation);
            originImage.setFile_name(target.getFile_name());
            originImage.setUrl(awsService.getURL(newAbsoluteLocation));

            imageDao.save(originImage);

            return new Response(true, "image have been modified");
        }

        return new Response(false, "image have not been modified");
    }

    public Response deleteById(int imageID)
    {
        Image image = this.getById(imageID);

        String absoluteLocation = String.format("files/%s", image.getFile_name());

        awsService.deleteObject(absoluteLocation);

        imageDao.deleteById(imageID);

        return new Response(true, "delete success");
    }

    public Response deleteAllByItemID(int itemID)
    {
        List<Image> images = imageDao.getAllByItemID(itemID);

        imageDao.flush();

        for (Image image : images) 
        {
            this.deleteById(image.getId());
        }

        return new Response(true, "Delete all image from item success");
    }
}
