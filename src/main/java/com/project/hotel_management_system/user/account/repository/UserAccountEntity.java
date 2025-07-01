package com.project.hotel_management_system.user.account.repository;

import com.project.hotel_management_system.utils.ShortId;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_account")
public class UserAccountEntity {

    @Id
    @ShortId
    private String id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    public UserAccountEntity() {
    }

    public UserAccountEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getCustomId() {
//        return customId;
//    }
//
//    public void setCustomId(String customId) {
//        this.customId = customId;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
