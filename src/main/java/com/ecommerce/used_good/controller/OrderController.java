package com.ecommerce.used_good.controller;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.Offer;
import com.ecommerce.used_good.bean.Shipping;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.AuthService;
import com.ecommerce.used_good.service.OfferService;
import com.ecommerce.used_good.service.OrderService;
import com.ecommerce.used_good.util.ConstantType;

@RestController
@RequestMapping("/orders")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class OrderController 
{
    @Autowired
    private OfferService offerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthService authService;
    
    @PostMapping("/offer/{offerID}")
    public Response createOrder(@PathVariable int offerID, @RequestBody Shipping shipping, Authentication authentication)
    {
        User user = authService.getCurrentLoginUser(authentication);
        Offer offer = offerService.getById(offerID);

        offerService.checkOwnOffer(offer, user);

        return orderService.createOrder(offer, shipping);
    }
}
