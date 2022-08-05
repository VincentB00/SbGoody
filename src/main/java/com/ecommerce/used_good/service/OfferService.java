package com.ecommerce.used_good.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.Offer;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.ItemDao;
import com.ecommerce.used_good.dao.OfferDao;
import com.ecommerce.used_good.http.HttpResponseThrowers;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.util.ReplacementUtils;

@Service
public class OfferService 
{
    @Autowired
    private OfferDao offerDao;
    
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemService itemService;

    public List<Offer> getAllOffer(int itemID)
    {
        return offerDao.getAllByItemID(itemID);
    }

    public Offer getById(int id)
    {
        Optional<Offer> optional = offerDao.findById(id);
        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("invalid or missing offer id");

        return optional.get();
    }

    public Response createOffer(User user, Item item, Offer offer)
    {
        offer.setItem(item);
        offer.setUser(user);

        offerDao.save(offer);

        return new Response(true);
    }

    public Response createOffer(User user, int itemID, Offer offer)
    {
        return createOffer(user, itemService.getItem(itemID), offer);
    }

    public Response modifyOffer(Offer originOffer, Offer targetOffer)
    {
        Optional<Offer> optional = offerDao.findById(originOffer.getId());
        if(!optional.isPresent())
            return (Response) HttpResponseThrowers.throwBadRequest("invalid or unable to find origin offer");

        Offer origin = optional.get();

        targetOffer.setItem(null);
        targetOffer.setUser(null);

        ReplacementUtils.replaceValue(origin, targetOffer);

        offerDao.save(origin);

        return new Response(true);
    }

    public Response modifyOffer(int offerID, Offer targetOffer)
    {
        Optional<Offer> optional = offerDao.findById(offerID);
        if(!optional.isPresent())
            return (Response) HttpResponseThrowers.throwBadRequest("invalid or unable to find origin offer");

        Offer origin = optional.get();

        targetOffer.setItem(null);
        targetOffer.setUser(null);

        ReplacementUtils.replaceValue(origin, targetOffer);

        offerDao.save(origin);

        return new Response(true);
    }

    public boolean isOwnOffer(Offer offer, User user)
    {
        return offer.getUser().getId() == user.getId();
    }

    public boolean isOwnOffer(int offerID, User user)
    {
        Optional<Offer> optional = offerDao.findById(offerID);

        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("invalid or missing offer id");

        return isOwnOffer(optional.get(), user);
    }

    public void checkOwnOffer(Offer offer, User user)
    {
        if(!isOwnOffer(offer, user))
            HttpResponseThrowers.throwForbidden("Offer does not belong to user");
    }
}
