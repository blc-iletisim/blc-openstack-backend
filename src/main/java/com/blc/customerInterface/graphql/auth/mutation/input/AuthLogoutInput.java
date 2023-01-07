package com.blc.customerInterface.graphql.auth.mutation.input;

import java.util.UUID;

public class AuthLogoutInput {

    private String accessToken;

    private UUID userId;
    public AuthLogoutInput() {
    }
    public AuthLogoutInput(String accessToken, UUID userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
