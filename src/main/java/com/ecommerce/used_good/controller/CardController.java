package com.ecommerce.used_good.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.Card;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.AuthService;
import com.ecommerce.used_good.service.CardService;
import com.ecommerce.used_good.util.ConstantType;

@RestController
@RequestMapping("/cards")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class CardController 
{
    @Autowired
    private CardService cardService;
    
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Card> getAllCardByUser(Authentication authentication)
    {
        User user = this.authService.getCurrentLoginUser(authentication);

        return this.cardService.getAllByUser(user.getId());
    }

    @PostMapping
    public Response saveCard(@RequestBody Card card ,Authentication authentication)
    {
        User user = this.authService.getCurrentLoginUser(authentication);
        return this.cardService.saveCard(card, user);
    }

    @PutMapping
    public Response modifyCard(@RequestBody Card card ,Authentication authentication)
    {
        User user = this.authService.getCurrentLoginUser(authentication);
        return this.cardService.modifyCard(card, user);
    }

    @DeleteMapping("/{cardID}")
    public Response deleteCard(@PathVariable int cardID ,Authentication authentication)
    {
        User user = this.authService.getCurrentLoginUser(authentication);
        return this.cardService.deleteCard(cardID, user);
    }
}
