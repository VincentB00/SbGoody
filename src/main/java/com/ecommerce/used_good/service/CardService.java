package com.ecommerce.used_good.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Card;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.CardDao;
import com.ecommerce.used_good.http.HttpResponseThrowers;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.util.ReplacementUtils;

@Service
public class CardService 
{
    @Autowired
    private CardDao cardDao;

    public List<Card> getAllByUser(int userID)
    {
        return this.cardDao.getAllByUserID(userID);
    }

    public Response saveCard(Card card, User user)
    {
        card.setUser(user);

        card.setId(0);

        this.cardDao.save(card);

        return new Response(true, "New Card have been save for user");
    }

    public Response modifyCard(Card card, User user)
    {
        Optional<Card> optional = this.cardDao.findById(card.getId());

        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("Can't find current card");

        Card cardM = optional.get();

        if(cardM.getUser().getId() != user.getId())
            HttpResponseThrowers.throwBadRequest("This is not your own card");
        
        card.setUser(null);
        ReplacementUtils.replaceValue(cardM, card);
        
        this.cardDao.save(cardM);

        return new Response(true, "Card have been modified");
    }

    public Response deleteCard(int CardID, User user)
    {
        Optional<Card> optional = this.cardDao.findById(CardID);

        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("Can't find current card");

        Card card = optional.get();

        if(card.getUser().getId() != user.getId())
            HttpResponseThrowers.throwBadRequest("This is not your own card");

        this.cardDao.delete(card);

        return new Response(true, "Card have been deleted");
    }
}
