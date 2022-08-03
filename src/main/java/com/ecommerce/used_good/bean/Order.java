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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "order")
public class Order 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private User user;

    @Column
    private double price;

    @Column
    private double shipping_price;

    @Column
    private int quantity;

    @Column
    private String shipping_tag;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date purchase_date;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "purchase",
        joinColumns = {
            @JoinColumn(name = "order_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "item_id", referencedColumnName = "id")
        }
    )
    private Item item;


    public Order() {
    }

    public Order(int id, User user, double price, double shipping_price, int quantity, String shipping_tag, Date purchase_date, Item item) {
        this.id = id;
        this.user = user;
        this.price = price;
        this.shipping_price = shipping_price;
        this.quantity = quantity;
        this.shipping_tag = shipping_tag;
        this.purchase_date = purchase_date;
        this.item = item;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getShipping_price() {
        return this.shipping_price;
    }

    public void setShipping_price(double shipping_price) {
        this.shipping_price = shipping_price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShipping_tag() {
        return this.shipping_tag;
    }

    public void setShipping_tag(String shipping_tag) {
        this.shipping_tag = shipping_tag;
    }

    public Date getPurchase_date() {
        return this.purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order id(int id) {
        setId(id);
        return this;
    }

    public Order user(User user) {
        setUser(user);
        return this;
    }

    public Order price(double price) {
        setPrice(price);
        return this;
    }

    public Order shipping_price(double shipping_price) {
        setShipping_price(shipping_price);
        return this;
    }

    public Order quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public Order shipping_tag(String shipping_tag) {
        setShipping_tag(shipping_tag);
        return this;
    }

    public Order purchase_date(Date purchase_date) {
        setPurchase_date(purchase_date);
        return this;
    }

    public Order item(Item item) {
        setItem(item);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id && Objects.equals(user, order.user) && price == order.price && shipping_price == order.shipping_price && quantity == order.quantity && Objects.equals(shipping_tag, order.shipping_tag) && Objects.equals(purchase_date, order.purchase_date) && Objects.equals(item, order.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, price, shipping_price, quantity, shipping_tag, purchase_date, item);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", price='" + getPrice() + "'" +
            ", shipping_price='" + getShipping_price() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", shipping_tag='" + getShipping_tag() + "'" +
            ", purchase_date='" + getPurchase_date() + "'" +
            ", item='" + getItem() + "'" +
            "}";
    }

}
