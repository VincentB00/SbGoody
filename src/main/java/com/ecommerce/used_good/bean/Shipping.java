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
    private String name;
    
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

    public Shipping(int id, String name, String address, String city, String state, String zip, User user) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Shipping name(String name) {
        setName(name);
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
        return id == shipping.id && Objects.equals(name, shipping.name) && Objects.equals(address, shipping.address) && Objects.equals(city, shipping.city) && Objects.equals(state, shipping.state) && Objects.equals(zip, shipping.zip) && Objects.equals(user, shipping.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, city, state, zip, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zip='" + getZip() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }


}
