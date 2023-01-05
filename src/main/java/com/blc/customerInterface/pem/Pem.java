package com.blc.customerInterface.pem;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pem")
@Builder
@AllArgsConstructor
public class Pem extends BaseDomain {

    private String name;
    private String pem_url;
    private List<Instance> instances = new ArrayList<>();
    private User user;

    public Pem() {

    }

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

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "pem")
    @JsonIgnore
    public List<Instance> getInstance() {
        return instances;
    }

    public void setInstance(List<Instance> instances) {
        this.instances = instances;
    }

    @ManyToOne(fetch = FetchType.EAGER)
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
                ", instances=" + instances +
                ", user=" + user +
                '}';
    }
}
