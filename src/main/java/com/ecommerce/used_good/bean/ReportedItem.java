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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "item_reported")
public class ReportedItem 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private Item item;
    
    @ManyToOne
    private User user;
    
    @Column
    private String reason;
    
    @Column
    private String message;

    @Column
    private String status = "PENDING";

    public ReportedItem() {
    }

    public ReportedItem(int id, Item item, User user, String reason, String message, String status) {
        this.id = id;
        this.item = item;
        this.user = user;
        this.reason = reason;
        this.message = message;
        this.status = status;
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

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public ReportedItem id(int id) {
        setId(id);
        return this;
    }

    public ReportedItem item(Item item) {
        setItem(item);
        return this;
    }

    public ReportedItem user(User user) {
        setUser(user);
        return this;
    }

    public ReportedItem reason(String reason) {
        setReason(reason);
        return this;
    }

    public ReportedItem message(String message) {
        setMessage(message);
        return this;
    }

    public ReportedItem status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ReportedItem)) {
            return false;
        }
        ReportedItem reportedItem = (ReportedItem) o;
        return id == reportedItem.id && Objects.equals(item, reportedItem.item) && Objects.equals(user, reportedItem.user) && Objects.equals(reason, reportedItem.reason) && Objects.equals(message, reportedItem.message) && Objects.equals(status, reportedItem.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, user, reason, message, status);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", item='" + this.item + "'" +
            ", user='" + this.user + "'" +
            ", reason='" + getReason() + "'" +
            ", message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
    

}
