package com.ecommerce.used_good.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.Shipping;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.AuthService;
import com.ecommerce.used_good.service.ShippingService;
import com.ecommerce.used_good.util.ConstantType;

@RestController
@RequestMapping("/shippings")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class ShippingController 
{
    @Autowired
    private ShippingService shippingService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Shipping> getAllShipping(Authentication authentication)
    {
        User user = authService.getCurrentLoginUser(authentication);

        return shippingService.getAllShipping(user);
    }

    @PostMapping
    public Response saveShipping(@RequestBody Shipping shipping,Authentication authentication)
    {
        User user = authService.getCurrentLoginUser(authentication);

        return shippingService.saveShipping(shipping, user);
    }

    @PutMapping("/{shippingID}")
    public Response modifyShipping(@PathVariable int shippingID,@RequestBody Shipping shipping)
    {
        return shippingService.modifyShipping(shippingID, shipping);
    }

    @DeleteMapping("/{shippingID}")
    public Response deleteShipping(@PathVariable int shippingID)
    {
        return shippingService.deleteShipping(shippingID);
    }
}
