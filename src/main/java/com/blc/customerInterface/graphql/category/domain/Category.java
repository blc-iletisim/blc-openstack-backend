package com.blc.customerInterface.graphql.category.domain;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category extends BaseDomain {
    private String name;
    private Instance instance;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
