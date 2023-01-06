package com.blc.customerInterface.graphql.auth.mutation.input;

import javax.persistence.Column;
import java.util.UUID;

public class AuthLogoutInput {

    @Column(length = 1000)
    private String AccessToken;
    private UUID userId;

    public AuthLogoutInput(String accessToken, UUID userId) {
        AccessToken = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
