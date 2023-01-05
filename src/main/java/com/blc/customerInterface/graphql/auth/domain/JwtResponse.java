package com.blc.customerInterface.graphql.auth.domain;



import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
public class JwtResponse extends BaseDomain {
    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";
    private UUID uid;
    private String role ;
    private String email;
    private User user;

    public JwtResponse(String accessToken, String refreshToken, UUID uid, String role, String email, User user) {
        //this.setId(UUID.randomUUID());
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.uid = uid;
        this.role = role;
        this.email = email;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
