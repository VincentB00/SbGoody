package com.ecommerce.used_good.bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.used_good.util.ConstantType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;
    
    @Column
    private String password;
    
    @Column
    private String first_name;
    
    @Column
    private String last_name;
    
    @Column
    private String middle_name;
    
    @Column
    private String email;

    @Column
    private String status = ConstantType.USER_STATUS_NORMAL;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "privilege",
        joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "user_role_id", referencedColumnName = "id")
        }        
    )
    private Set<UserRole> userRoles;

    // @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    // private List<Item> items;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date create_time;

    public User() {
    }


    public User(int id, String username, String password, String first_name, String last_name, String middle_name, String email, String status, Set<UserRole> userRoles, Date create_time) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.email = email;
        this.status = status;
        this.userRoles = userRoles;
        this.create_time = create_time;
    }
    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return this.middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
    

    public String getCreate_time() {
        return this.create_time.toString();
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public User id(int id) {
        setId(id);
        return this;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User first_name(String first_name) {
        setFirst_name(first_name);
        return this;
    }

    public User last_name(String last_name) {
        setLast_name(last_name);
        return this;
    }

    public User middle_name(String middle_name) {
        setMiddle_name(middle_name);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User status(String status) {
        setStatus(status);
        return this;
    }


    public User userRoles(Set<UserRole> userRoles) {
        setUserRoles(userRoles);
        return this;
    }
    

    public User create_time(Date create_time) {
        setCreate_time(create_time);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(middle_name, user.middle_name) && Objects.equals(email, user.email) && Objects.equals(status, user.status) && Objects.equals(userRoles, user.userRoles) && Objects.equals(create_time, user.create_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, first_name, last_name, middle_name, email, status, userRoles, create_time);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", first_name='" + getFirst_name() + "'" +
            ", last_name='" + getLast_name() + "'" +
            ", middle_name='" + getMiddle_name() + "'" +
            ", email='" + getEmail() + "'" +
            ", status='" + getStatus() + "'" +
            ", userRoles='" + getUserRoles() + "'" +
            ", create_time='" + getCreate_time() + "'" +
            "}";
    }
    
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.status.equals(ConstantType.USER_STATUS_LOCK);
    }

    @Override
    public boolean isCredentialsNonExpired() 
    {
        return true;
    }

    @Override
    public boolean isEnabled() 
    {
        return true;
    }

}
