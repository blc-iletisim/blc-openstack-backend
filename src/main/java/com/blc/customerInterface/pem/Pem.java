package com.blc.customerInterface.pem;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pem")
public class Pem extends BaseDomain {

    private String name;
    private String pem_url;
    private Instance instance;
    private User user;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    @Column(nullable = false)
    public String getPem_url() {
        return pem_url;
    }

    public void setPem_url(String pem_url) {
        this.pem_url = pem_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "pem",cascade = CascadeType.ALL)
    @JsonIgnore
    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Pem{" +
                "name='" + name + '\'' +
                ", pem_url='" + pem_url + '\'' +
                ", instance=" + instance +
                ", user=" + user +
                '}';
    }
}
