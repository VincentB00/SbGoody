package com.ecommerce.used_good.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.Offer;
import com.ecommerce.used_good.bean.Order;
import com.ecommerce.used_good.bean.Shipping;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.ItemDao;
import com.ecommerce.used_good.dao.OfferDao;
import com.ecommerce.used_good.dao.OrderDao;
import com.ecommerce.used_good.http.HttpResponseThrowers;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.security.Sha256PasswordEncoder;
import com.ecommerce.used_good.util.ConstantType;

@Service
public class OrderService 
{
    @Autowired
    private Sha256PasswordEncoder sha256PasswordEncoder;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OfferService offerService;

    @Autowired
    private OfferDao offerDao;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDao itemDao;

    public Order getOrder(int orderID)
    {
        Optional<Order> optional = this.orderDao.findById(orderID);
        
        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("Missing or invalid order id");

        return optional.get();
    }

    public List<Order> getAllByUserID(int userID)
    {
        return this.orderDao.getAllByUserID(userID);
    }

    public List<Order> getAllSellingOrder(int userID)
    {
        return this.orderDao.getAllSellingOrder(userID);
    }

    public Order createOrder(Offer offer, Shipping shipping, User user)
    {
        String shippingLabel = createShippingLabel(offer);

        Order order = new Order();
        order.setItem(offer.getItem());
        order.setUser(user);
        order.setPrice(offer.getPrice());
        order.setQuantity(offer.getQuantity());
        order.setReciever_name(shipping.getReciever_name());
        order.setPhone_number(shipping.getPhone_number());
        order.setShipping_price(offer.getItem().getShipping_price());
        order.setShippingLabel(shippingLabel);
        order.setShipping_address(shipping.getAddress());
        order.setShipping_city(shipping.getCity());
        order.setShipping_state(shipping.getState());
        order.setShipping_zip(shipping.getZip());
        order.setCurrent_shipping_location("Wearhouse");
        order.setStatus(ConstantType.SHIPPING_STATUS_PEDNING);
        order.setTotal((order.getPrice() * order.getQuantity()) + order.getShipping_price());
        
        order = this.orderDao.save(order);

        Offer newOffer = offerService.getById(offer.getId());
        newOffer.setStatus(ConstantType.OFFER_STATUS_FULLFILLED);
        this.offerDao.save(newOffer);

        Item item = itemService.getItem(offer.getItem().getId());
        item.setStock(item.getStock() - 1);
        itemDao.save(item);

        return order;
    }    

    public String createShippingLabel(Offer offer)
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(offer.getCreate_time());
        stringBuilder.append(offer.getItem().getName());
        stringBuilder.append(offer.getUser().getUsername());

        return sha256PasswordEncoder.encode(stringBuilder.toString());
    }

    public boolean isAssociatedWithUser(int userID, int orderID)
    {
        Order order = this.getOrder(orderID);

        List<Order> buyingOrders = this.getAllByUserID(userID);

        if(buyingOrders.parallelStream().anyMatch((orderT) -> orderT.getUser().getId() == userID && orderT.getId() == order.getId()))
            return true;

        List<Order> sellingOrders = this.getAllSellingOrder(userID);

        if(sellingOrders.parallelStream().anyMatch((orderT) -> orderT.getItem().getId() == order.getItem().getId()))
            return true;

        return false;
    }

    public Response checkAssociateWithUser(int userID, int orderID)
    {
        if(!this.isAssociatedWithUser(userID, orderID))
            HttpResponseThrowers.throwBadRequest("this order is not associate with current user");
            
        return new Response(true, "this order is associate with current user");
    }
}
