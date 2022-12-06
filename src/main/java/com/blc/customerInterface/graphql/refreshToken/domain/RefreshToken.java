package com.blc.customerInterface.graphql.refreshToken.domain;


import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "refreshtoken")
public class RefreshToken extends BaseDomain {

    private User user;
    private String token;
    private Instant expiryDate;


    @OneToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false, unique = true)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(nullable = false)
    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}
