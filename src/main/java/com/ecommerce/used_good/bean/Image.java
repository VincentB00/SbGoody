package com.ecommerce.used_good.bean;

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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "image")
public class Image 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String url;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;


    public Image() {
    }

    public Image(int id, String url, Item item) {
        this.id = id;
        this.url = url;
        this.item = item;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Image id(int id) {
        setId(id);
        return this;
    }

    public Image url(String url) {
        setUrl(url);
        return this;
    }

    public Image item(Item item) {
        setItem(item);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Image)) {
            return false;
        }
        Image image = (Image) o;
        return id == image.id && Objects.equals(url, image.url) && Objects.equals(item, image.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, item);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", url='" + getUrl() + "'" +
            ", item_id='" + this.item.getId() + "'" +
            "}";
    }
}
