package com.ecommerce.used_good.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Shipping;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.ShippingDao;
import com.ecommerce.used_good.http.HttpResponseThrowers;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.util.ReplacementUtils;

@Service
public class ShippingService 
{
    @Autowired
    private ShippingDao shippingDao;

    public List<Shipping> getAllShipping(User user)
    {
        return shippingDao.findAllByUser(user);
    }

    public Response saveShipping(Shipping shipping, User user)
    {
        shipping.setUser(user);

        shippingDao.save(shipping);
        
        return new Response(true, "new Shipping address save to current user");
    }

    public Response modifyShipping(Shipping origin, Shipping target)
    {
        Optional<Shipping> optional = shippingDao.findById(origin.getId());
        if(!optional.isPresent())
            return (Response) HttpResponseThrowers.throwBadRequest("invalid or missing shipping origin id");

        origin = optional.get();

        target.setUser(null);

        ReplacementUtils.replaceValue(origin, target);

        shippingDao.save(origin);

        return new Response(true, "Current shipping have been modified");
    }

    public Response modifyShipping(int originID, Shipping target)
    {
        Optional<Shipping> optional = shippingDao.findById(originID);
        if(!optional.isPresent())
            return (Response) HttpResponseThrowers.throwBadRequest("invalid or missing shipping origin id");

        Shipping origin = optional.get();

        return modifyShipping(origin, target);
    }

    public Response deleteShipping(int id)
    {
        shippingDao.deleteById(id);

        return new Response(true, "shipping address remove");
    }
}
