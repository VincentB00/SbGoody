package com.ecommerce.used_good.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.EmailData;
import com.ecommerce.used_good.bean.Offer;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.MailService;
import com.ecommerce.used_good.util.ConstantType;

@RestController
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
@RequestMapping("/mails")
public class MailController 
{
    @Autowired
    private MailService mailService;

    @PostMapping
    public Response sendEmail(@RequestBody EmailData emailData)
    {
        this.mailService.sendEmails(emailData);
        return new Response(true, "send email success with: \n" + emailData);
    }

    @PostMapping("/accept_offer/{offerID}")
    public Response sendAcceptOfferEmail(@PathVariable int offerID)
    {
        this.mailService.sendAcceptOfferEmail(offerID);
        return new Response(true, "send email success");
    }

    @PostMapping("/decline_offer/{offerID}")
    public Response sendDeclineOfferEmail(@PathVariable int offerID)
    {
        this.mailService.sendDeclineOfferEmail(offerID);
        return new Response(true, "send email success");
    }

    @PostMapping("/place_order/{orderID}")
    public Response sendPlaceOrderEmail(@PathVariable int orderID)
    {
        this.mailService.sendOrderHaveBeenPlaceEmail(orderID);
        return new Response(true, "send email success");
    }
}
