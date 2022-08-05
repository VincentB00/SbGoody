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

    @Column
    private String file_name;

    @Column
    private long size = 0;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;


    public Image() {
    }

    public Image(int id, String url, String file_name, long size, Item item) {
        this.id = id;
        this.url = url;
        this.file_name = file_name;
        this.size = size;
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

    public String getFile_name() {
        return this.file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
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

    public Image file_name(String file_name) {
        setFile_name(file_name);
        return this;
    }

    public Image size(long size) {
        setSize(size);
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
        return id == image.id && Objects.equals(url, image.url) && Objects.equals(file_name, image.file_name) && size == image.size && Objects.equals(item, image.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, file_name, size, item);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", url='" + getUrl() + "'" +
            ", file_name='" + getFile_name() + "'" +
            ", size='" + getSize() + "'" +
            ", item='" + getItem() + "'" +
            "}";
    }
    
}
