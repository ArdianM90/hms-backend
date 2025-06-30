package com.project.hotel_management_system.user.account.repository;

import com.project.hotel_management_system.utils.ShortId;
import jakarta.persistence.*;

@Entity
@Table(name = "user_account")
public class UserAccountEntity {

    @Id
    @ShortId
    private String id;

    @Column(unique = true, nullable = false)
    private String nickname;

    private String password;

    public UserAccountEntity() {
    }

    public UserAccountEntity(String nickname, String password) {
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
