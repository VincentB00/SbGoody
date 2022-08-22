package com.ecommerce.used_good.bean;

import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerce.used_good.util.ConstantType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item")
public class Item 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column
    private int id;
    
    @Column
    private String name;
    
    @Column
    private String item_condition;
    
    @Column
    private String description;
    
    @Column
    private int stock;
    
    @Column
    private double price;
    
    @Column
    private double discount_price;
    
    @Column
    private double shipping_price;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "item_category_group",
        joinColumns = {
            @JoinColumn(name = "item_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "category_id", referencedColumnName = "id")
        }
    )
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "item")
    private List<Image> images;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column
    private String status = ConstantType.ITEM_STATUS_UNSOLD;

    public Item() {
    }

    public Item(int id, String name, String item_condition, String description, int stock, double price, double discount_price, double shipping_price, List<Category> categories, List<Image> images, User user, String status) {
        this.id = id;
        this.name = name;
        this.item_condition = item_condition;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.discount_price = discount_price;
        this.shipping_price = shipping_price;
        this.categories = categories;
        this.images = images;
        this.user = user;
        this.status = status;
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

    public String getItem_condition() {
        return this.item_condition;
    }

    public void setItem_condition(String item_condition) {
        this.item_condition = item_condition;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount_price() {
        return this.discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }

    public double getShipping_price() {
        return this.shipping_price;
    }

    public void setShipping_price(double shipping_price) {
        this.shipping_price = shipping_price;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item id(int id) {
        setId(id);
        return this;
    }

    public Item name(String name) {
        setName(name);
        return this;
    }

    public Item item_condition(String item_condition) {
        setItem_condition(item_condition);
        return this;
    }

    public Item description(String description) {
        setDescription(description);
        return this;
    }

    public Item stock(int stock) {
        setStock(stock);
        return this;
    }

    public Item price(double price) {
        setPrice(price);
        return this;
    }

    public Item discount_price(double discount_price) {
        setDiscount_price(discount_price);
        return this;
    }

    public Item shipping_price(double shipping_price) {
        setShipping_price(shipping_price);
        return this;
    }

    public Item categories(List<Category> categories) {
        setCategories(categories);
        return this;
    }

    public Item images(List<Image> images) {
        setImages(images);
        return this;
    }

    public Item user(User user) {
        setUser(user);
        return this;
    }

    public Item status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id && Objects.equals(name, item.name) && Objects.equals(item_condition, item.item_condition) && Objects.equals(description, item.description) && stock == item.stock && price == item.price && discount_price == item.discount_price && shipping_price == item.shipping_price && Objects.equals(categories, item.categories) && Objects.equals(images, item.images) && Objects.equals(user, item.user) && Objects.equals(status, item.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, item_condition, description, stock, price, discount_price, shipping_price, categories, images, user, status);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", item_condition='" + getItem_condition() + "'" +
            ", description='" + getDescription() + "'" +
            ", stock='" + getStock() + "'" +
            ", price='" + getPrice() + "'" +
            ", discount_price='" + getDiscount_price() + "'" +
            ", shipping_price='" + getShipping_price() + "'" +
            ", categories='" + getCategories() + "'" +
            ", images='" + getImages() + "'" +
            ", user='" + getUser() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
