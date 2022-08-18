package com.ecommerce.used_good.bean;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.ecommerce.used_good.util.ConstantType;

@Entity
@Table(name = "offer")
public class Offer 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User user;

    @Column
    private double price;

    @Column
    private int quantity;

    @Column
    private String message;

    @Column
    private String status = ConstantType.OFFER_STATUS_PEDNING;

    @Column
    @CreationTimestamp
    private Date create_time;


    public Offer() {
    }

    public Offer(int id, Item item, User user, double price, int quantity, String message, String status, Date create_time) {
        this.id = id;
        this.item = item;
        this.user = user;
        this.price = price;
        this.quantity = quantity;
        this.message = message;
        this.status = status;
        this.create_time = create_time;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return this.create_time.toString();
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Offer id(int id) {
        setId(id);
        return this;
    }

    public Offer item(Item item) {
        setItem(item);
        return this;
    }

    public Offer user(User user) {
        setUser(user);
        return this;
    }

    public Offer price(double price) {
        setPrice(price);
        return this;
    }

    public Offer quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public Offer message(String message) {
        setMessage(message);
        return this;
    }

    public Offer status(String status) {
        setStatus(status);
        return this;
    }

    public Offer create_time(Date create_time) {
        setCreate_time(create_time);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Offer)) {
            return false;
        }
        Offer offer = (Offer) o;
        return id == offer.id && Objects.equals(item, offer.item) && Objects.equals(user, offer.user) && price == offer.price && quantity == offer.quantity && Objects.equals(message, offer.message) && Objects.equals(status, offer.status) && Objects.equals(create_time, offer.create_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, user, price, quantity, message, status, create_time);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", item='" + getItem() + "'" +
            ", user='" + getUser() + "'" +
            ", price='" + getPrice() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            ", create_time='" + getCreate_time() + "'" +
            "}";
    }
}
