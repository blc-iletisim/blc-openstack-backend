package com.blc.customerInterface.graphql.auth.mutation.input;

import com.blc.customerInterface.graphql.logout.domain.BlackToken;

import java.util.UUID;

public class AuthLogoutInput {

    private UUID userId;

    private String accessToken;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
