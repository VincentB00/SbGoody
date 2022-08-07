package com.ecommerce.used_good.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.Offer;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.AuthService;
import com.ecommerce.used_good.service.OfferService;
import com.ecommerce.used_good.util.ConstantType;

@RestController
@RequestMapping("/offers")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class OfferController 
{
    @Autowired
    private OfferService offerService;

    @Autowired
    private AuthService authService;

    
    @GetMapping("{itemID}")
    public List<Offer> getAllOffer(@PathVariable int itemID)
    {
        return this.offerService.getAllOffer(itemID);
    }

    @PostMapping("{itemID}")
    public Response createOffer(@PathVariable int itemID, @RequestBody Offer offer, Authentication authentication)
    {
        User user = authService.getCurrentLoginUser(authentication);
    
        return offerService.createOffer(user, itemID, offer);
    }

    @PutMapping("{offerID}")
    public Response modifyOffer(@PathVariable int offerID, @RequestBody Offer offer)
    {
        return offerService.modifyOffer(offerID, offer);
    }
}
