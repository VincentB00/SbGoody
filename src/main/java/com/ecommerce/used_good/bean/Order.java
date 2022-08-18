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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "item_order")
public class Order 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private double price;

    @Column
    private int quantity;

    @Column
    private double total;

    @Column
    private String reciever_name;

    @Column
    private String phone_number;

    @Column
    private double shipping_price;

    @Column(name = "shipping_label")
    private String shippingLabel;

    @Column
    private String shipping_address;

    @Column
    private String shipping_city;
    
    @Column
    private String shipping_state;
    
    @Column
    private String shipping_zip;
    
    @Column
    private String current_shipping_location;
    
    @Column
    private String status;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date purchase_date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User user;



    public Order() {
    }

    public Order(int id, double price, int quantity, int total, String reciever_name, String phone_number, double shipping_price, String shippingLabel, String shipping_address, String shipping_city, String shipping_state, String shipping_zip, String current_shipping_location, String status, Date purchase_date, Item item, User user) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.reciever_name = reciever_name;
        this.phone_number = phone_number;
        this.shipping_price = shipping_price;
        this.shippingLabel = shippingLabel;
        this.shipping_address = shipping_address;
        this.shipping_city = shipping_city;
        this.shipping_state = shipping_state;
        this.shipping_zip = shipping_zip;
        this.current_shipping_location = current_shipping_location;
        this.status = status;
        this.purchase_date = purchase_date;
        this.item = item;
        this.user = user;
    }


    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public Order total(double total) {
        setTotal(total);
        return this;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getReciever_name() {
        return this.reciever_name;
    }

    public void setReciever_name(String reciever_name) {
        this.reciever_name = reciever_name;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public double getShipping_price() {
        return this.shipping_price;
    }

    public void setShipping_price(double shipping_price) {
        this.shipping_price = shipping_price;
    }

    public String getShippingLabel() {
        return this.shippingLabel;
    }

    public void setShippingLabel(String shippingLabel) {
        this.shippingLabel = shippingLabel;
    }

    public String getShipping_address() {
        return this.shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getShipping_city() {
        return this.shipping_city;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public String getShipping_state() {
        return this.shipping_state;
    }

    public void setShipping_state(String shipping_state) {
        this.shipping_state = shipping_state;
    }

    public String getShipping_zip() {
        return this.shipping_zip;
    }

    public void setShipping_zip(String shipping_zip) {
        this.shipping_zip = shipping_zip;
    }

    public String getCurrent_shipping_location() {
        return this.current_shipping_location;
    }

    public void setCurrent_shipping_location(String current_shipping_location) {
        this.current_shipping_location = current_shipping_location;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchase_date() {
        return this.purchase_date.toString();
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order id(int id) {
        setId(id);
        return this;
    }

    public Order price(double price) {
        setPrice(price);
        return this;
    }

    public Order quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public Order reciever_name(String reciever_name) {
        setReciever_name(reciever_name);
        return this;
    }

    public Order phone_number(String phone_number) {
        setPhone_number(phone_number);
        return this;
    }

    public Order shipping_price(double shipping_price) {
        setShipping_price(shipping_price);
        return this;
    }

    public Order shippingLabel(String shippingLabel) {
        setShippingLabel(shippingLabel);
        return this;
    }

    public Order shipping_address(String shipping_address) {
        setShipping_address(shipping_address);
        return this;
    }

    public Order shipping_city(String shipping_city) {
        setShipping_city(shipping_city);
        return this;
    }

    public Order shipping_state(String shipping_state) {
        setShipping_state(shipping_state);
        return this;
    }

    public Order shipping_zip(String shipping_zip) {
        setShipping_zip(shipping_zip);
        return this;
    }

    public Order current_shipping_location(String current_shipping_location) {
        setCurrent_shipping_location(current_shipping_location);
        return this;
    }

    public Order status(String status) {
        setStatus(status);
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

    public Order user(User user) {
        setUser(user);
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
        return id == order.id && price == order.price && quantity == order.quantity && total == order.total && Objects.equals(reciever_name, order.reciever_name) && Objects.equals(phone_number, order.phone_number) && shipping_price == order.shipping_price && Objects.equals(shippingLabel, order.shippingLabel) && Objects.equals(shipping_address, order.shipping_address) && Objects.equals(shipping_city, order.shipping_city) && Objects.equals(shipping_state, order.shipping_state) && Objects.equals(shipping_zip, order.shipping_zip) && Objects.equals(current_shipping_location, order.current_shipping_location) && Objects.equals(status, order.status) && Objects.equals(purchase_date, order.purchase_date) && Objects.equals(item, order.item) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, quantity, total, reciever_name, phone_number, shipping_price, shippingLabel, shipping_address, shipping_city, shipping_state, shipping_zip, current_shipping_location, status, purchase_date, item, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", price='" + getPrice() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", total='" + getTotal() + "'" +
            ", reciever_name='" + getReciever_name() + "'" +
            ", phone_number='" + getPhone_number() + "'" +
            ", shipping_price='" + getShipping_price() + "'" +
            ", shippingLabel='" + getShippingLabel() + "'" +
            ", shipping_address='" + getShipping_address() + "'" +
            ", shipping_city='" + getShipping_city() + "'" +
            ", shipping_state='" + getShipping_state() + "'" +
            ", shipping_zip='" + getShipping_zip() + "'" +
            ", current_shipping_location='" + getCurrent_shipping_location() + "'" +
            ", status='" + getStatus() + "'" +
            ", purchase_date='" + getPurchase_date() + "'" +
            ", item='" + getItem() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
}
