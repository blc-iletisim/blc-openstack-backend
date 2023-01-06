package com.blc.customerInterface.jwt.logout;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import javax.persistence.*;

@Entity
@Table(name = "logout_token")
public class LogoutToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String accessToken;

    public LogoutToken() {
    }

    public LogoutToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
