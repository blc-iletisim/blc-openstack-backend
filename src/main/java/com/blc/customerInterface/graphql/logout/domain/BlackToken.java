package com.blc.customerInterface.graphql.logout.domain;


import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "black_token")
public class BlackToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String accessToken;

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

    public BlackToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public BlackToken() {
    }

    
}
