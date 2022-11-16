package com.blc.customerInterface.graphql.pem.domain;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;

import javax.persistence.*;

@Entity
@Table(name = "pem")
public class Pem extends BaseDomain {

    private String name;
    private String pem_url;
    private Instance instance;

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

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "pem")
    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
