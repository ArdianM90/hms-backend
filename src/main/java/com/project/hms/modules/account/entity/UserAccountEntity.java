package com.project.hms.modules.account.entity;

import com.project.hms.common.utils.ShortId;
import jakarta.persistence.*;

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
