package com.blc.customerInterface.graphql.category.domain;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends BaseDomain {
    private String name;
    private Set<Instance> instances= new HashSet<>();

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "categories")
    public Set<Instance> getInstances() {
        return instances;
    }

    public void setInstances(Set<Instance> instances) {
        this.instances = instances;
    }
}
