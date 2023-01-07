package com.blc.customerInterface.jwt.Logout.domain;


import javax.persistence.*;

@Entity(name = "logout_token")
public class LogoutToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true,length = 1000)
    private String logoutToken;

    public LogoutToken() {
    }

    public LogoutToken(String logoutToken) {
        this.logoutToken = logoutToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoutToken() {
        return logoutToken;
    }

    public void setLogoutToken(String logoutToken) {
        this.logoutToken = logoutToken;
    }
}
