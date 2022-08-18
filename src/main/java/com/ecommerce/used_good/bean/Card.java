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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "card")
public class Card 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @Column
    private int number;
    
    @Column
    private int valid_month;
    
    @Column
    private int valid_year;
    
    @Column
    private int CVC;
    
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonIgnore
    private User user;


    public Card() {
    }

    public Card(int id, int number, int valid_month, int valid_year, int CVC, String name, User user) {
        this.id = id;
        this.number = number;
        this.valid_month = valid_month;
        this.valid_year = valid_year;
        this.CVC = CVC;
        this.name = name;
        this.user = user;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getValid_month() {
        return this.valid_month;
    }

    public void setValid_month(int valid_month) {
        this.valid_month = valid_month;
    }

    public int getValid_year() {
        return this.valid_year;
    }

    public void setValid_year(int valid_year) {
        this.valid_year = valid_year;
    }

    public int getCVC() {
        return this.CVC;
    }

    public void setCVC(int CVC) {
        this.CVC = CVC;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card id(int id) {
        setId(id);
        return this;
    }

    public Card number(int number) {
        setNumber(number);
        return this;
    }

    public Card valid_month(int valid_month) {
        setValid_month(valid_month);
        return this;
    }

    public Card valid_year(int valid_year) {
        setValid_year(valid_year);
        return this;
    }

    public Card CVC(int CVC) {
        setCVC(CVC);
        return this;
    }

    public Card name(String name) {
        setName(name);
        return this;
    }

    public Card user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return id == card.id && number == card.number && valid_month == card.valid_month && valid_year == card.valid_year && CVC == card.CVC && Objects.equals(name, card.name) && Objects.equals(user, card.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, valid_month, valid_year, CVC, name, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", number='" + getNumber() + "'" +
            ", valid_month='" + getValid_month() + "'" +
            ", valid_year='" + getValid_year() + "'" +
            ", CVC='" + getCVC() + "'" +
            ", name='" + getName() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

}
