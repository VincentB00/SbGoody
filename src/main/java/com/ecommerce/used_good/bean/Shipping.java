package com.ecommerce.used_good.bean;

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

@Entity
@Table(name = "shipping")
public class Shipping 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String label;

    @Column
    private String reciever_name;

    @Column
    private String phone_number;

    @Column
    private String address;
    
    @Column
    private String city;
    
    @Column
    private String state;
    
    @Column
    private String zip;  
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User user;  


    public Shipping() {
    }

    public Shipping(int id, String label, String reciever_name, String phone_number, String address, String city, String state, String zip, User user) {
        this.id = id;
        this.label = label;
        this.reciever_name = reciever_name;
        this.phone_number = phone_number;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.user = user;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shipping id(int id) {
        setId(id);
        return this;
    }

    public Shipping label(String label) {
        setLabel(label);
        return this;
    }

    public Shipping reciever_name(String reciever_name) {
        setReciever_name(reciever_name);
        return this;
    }

    public Shipping phone_number(String phone_number) {
        setPhone_number(phone_number);
        return this;
    }

    public Shipping address(String address) {
        setAddress(address);
        return this;
    }

    public Shipping city(String city) {
        setCity(city);
        return this;
    }

    public Shipping state(String state) {
        setState(state);
        return this;
    }

    public Shipping zip(String zip) {
        setZip(zip);
        return this;
    }

    public Shipping user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Shipping)) {
            return false;
        }
        Shipping shipping = (Shipping) o;
        return id == shipping.id && Objects.equals(label, shipping.label) && Objects.equals(reciever_name, shipping.reciever_name) && Objects.equals(phone_number, shipping.phone_number) && Objects.equals(address, shipping.address) && Objects.equals(city, shipping.city) && Objects.equals(state, shipping.state) && Objects.equals(zip, shipping.zip) && Objects.equals(user, shipping.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, reciever_name, phone_number, address, city, state, zip, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", label='" + getLabel() + "'" +
            ", reciever_name='" + getReciever_name() + "'" +
            ", phone_number='" + getPhone_number() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zip='" + getZip() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }


}
