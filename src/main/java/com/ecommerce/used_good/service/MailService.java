package com.ecommerce.used_good.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.EmailData;
import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.Offer;
import com.ecommerce.used_good.bean.Order;
import com.ecommerce.used_good.bean.User;

@Service
public class MailService 
{
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OfferService offerService;

    @Autowired
    private OrderService orderService;

    public void sendEmails(String toEmail, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendEmails(EmailData emailData)
    {
        this.sendEmails(emailData.getToEmail(), emailData.getSubject(), emailData.getBody());
    }

    public void sendAcceptOfferEmail(int offerID)
    {
        Offer offer = this.offerService.getById(offerID);
        String userFullName = String.format("%s %s", offer.getUser().getFirst_name(), offer.getUser().getLast_name());
        EmailData emailData = new EmailData();
        emailData.setToEmail(offer.getUser().getEmail());
        emailData.setSubject("Congrat an offer on Goody have been Accepted");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Congratulation " + userFullName + "\n");
        stringBuilder.append("An offer in your offer list on Goody have been accepted\n");
        stringBuilder.append(String.format("Item name: %s\n", offer.getItem().getName()));
        stringBuilder.append(String.format("Accepted Price: $%s\n", offer.getPrice()));
        stringBuilder.append("For more detail please go to Goody.com\n");
        stringBuilder.append("Thank you for trusting Goody\n");
        emailData.setBody(stringBuilder.toString());
        this.sendEmails(emailData);
    }

    public void sendDeclineOfferEmail(int offerID)
    {
        Offer offer = this.offerService.getById(offerID);
        String userFullName = String.format("%s %s", offer.getUser().getFirst_name(), offer.getUser().getLast_name());
        EmailData emailData = new EmailData();
        emailData.setToEmail(offer.getUser().getEmail());
        emailData.setSubject("An offer on Goody have been Decline");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello, " + userFullName + "\n");
        stringBuilder.append("An offer in your offer list on Goody have been Decline\n");
        stringBuilder.append(String.format("Item name: %s\n", offer.getItem().getName()));
        stringBuilder.append(String.format("Decline Price: $%s\n", offer.getPrice()));
        stringBuilder.append("For more detail please go to Goody.com\n");
        stringBuilder.append("Thank you for trusting Goody\n");
        emailData.setBody(stringBuilder.toString());
        this.sendEmails(emailData);
    }

    public void sendOrderHaveBeenPlaceEmail(int orderID)
    {
        Order order = this.orderService.getOrder(orderID);
        User buyer = order.getUser();
        Item item = order.getItem();
        User seller = item.getUser();

        String buyerFullName = String.format("%s %s", buyer.getFirst_name(), buyer.getLast_name());
        String sellerFullName = String.format("%s %s", seller.getFirst_name(), seller.getLast_name());

        EmailData emailData = new EmailData();
        emailData.setToEmail(buyer.getEmail());
        emailData.setSubject("An order from your offer have been place");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello, " + buyerFullName + "\n");
        stringBuilder.append("An order have been place base on your offer have been accepted\n");
        stringBuilder.append(String.format("Item name: %s\n", item.getName()));
        stringBuilder.append(String.format("Item price: %s\n", order.getPrice()));
        stringBuilder.append(String.format("Quantity: %s\n", order.getQuantity()));
        stringBuilder.append(String.format("Order total: $%s\n", order.getTotal()));
        stringBuilder.append("Delivering to:\n");
        stringBuilder.append(String.format("Name: ", order.getReciever_name()));
        stringBuilder.append(String.format("Phone Number: ", order.getPhone_number()));
        stringBuilder.append(String.format("Shipping Label: ", order.getShippingLabel()));
        stringBuilder.append("For more detail please go to Goody.com\n");
        stringBuilder.append("Thank you for trusting Goody\n");
        emailData.setBody(stringBuilder.toString());
        this.sendEmails(emailData);


        emailData = new EmailData();
        emailData.setToEmail(seller.getEmail());
        emailData.setSubject("An order from your Item list have been place");
        stringBuilder = new StringBuilder();
        stringBuilder.append("Hello, " + sellerFullName + "\n");
        stringBuilder.append("An order have been place base on your accepted offer\n");
        stringBuilder.append(String.format("Item name: %s\n", item.getName()));
        stringBuilder.append(String.format("Item price: %s\n", order.getPrice()));
        stringBuilder.append(String.format("Quantity: %s\n", order.getQuantity()));
        stringBuilder.append(String.format("Order total: $%s\n", order.getTotal()));
        stringBuilder.append("Delivering to:\n");
        stringBuilder.append(String.format("Name: ", order.getReciever_name()));
        stringBuilder.append(String.format("Phone Number: ", order.getPhone_number()));
        stringBuilder.append(String.format("Shipping Label: ", order.getShippingLabel()));
        stringBuilder.append("Please make sure to update shipping information as soon as possible\n");
        stringBuilder.append("For more detail please go to Goody.com\n");
        stringBuilder.append("Thank you for trusting Goody\n");
        emailData.setBody(stringBuilder.toString());
        this.sendEmails(emailData);
    }
}
