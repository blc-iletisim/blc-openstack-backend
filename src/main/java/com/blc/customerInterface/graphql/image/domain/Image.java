package com.blc.customerInterface.graphql.image.domain;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "image")
@Builder
@AllArgsConstructor
public class Image extends BaseDomain {
    private String name;
    private List<Instance> instances= new ArrayList<>();

    public Image() {

    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "image",fetch = FetchType.EAGER)
    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }
}
