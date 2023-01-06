package com.blc.customerInterface.graphql.blackToken.domain;

import javax.persistence.*;

@Entity
@Table(name = "black_token")
public class BlackToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String accessToken;

    public BlackToken() {
    }

    public BlackToken(String accessToken) {
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
