package com.ecommerce.used_good.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Offer;
import com.ecommerce.used_good.bean.Order;
import com.ecommerce.used_good.bean.Shipping;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.security.Sha256PasswordEncoder;
import com.ecommerce.used_good.util.ConstantType;

@Service
public class OrderService 
{
    @Autowired
    private Sha256PasswordEncoder sha256PasswordEncoder;

    public Response createOrder(Offer offer, Shipping shipping)
    {
        String shippingLabel = createShippingLabel(offer);

        Order order = new Order();
        order.setItem(offer.getItem());
        order.setUser(offer.getUser());
        order.setPrice(offer.getPrice());
        order.setQuantity(offer.getQuantity());
        order.setShipping_price(offer.getItem().getShipping_price());
        order.setShippingLabel(shippingLabel);
        order.setShipping_address(shipping.getAddress());
        order.setShipping_city(shipping.getCity());
        order.setShipping_state(shipping.getState());
        order.setShipping_zip(shipping.getZip());
        order.setCurrent_shipping_location("Wearhouse");
        order.setStatus(ConstantType.SHIPPING_STATUS_PEDNING);

        return new Response(true, "Order created with Shipping Label: " + shippingLabel);
    }    

    public String createShippingLabel(Offer offer)
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(offer.getCreate_time());
        stringBuilder.append(offer.getItem().getName());
        stringBuilder.append(offer.getUser().getUsername());

        return sha256PasswordEncoder.encode(stringBuilder.toString());
    }
}
