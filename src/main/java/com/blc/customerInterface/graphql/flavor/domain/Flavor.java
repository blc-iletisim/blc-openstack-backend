package com.blc.customerInterface.graphql.flavor.domain;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "flavor")
@Builder
@AllArgsConstructor
public class Flavor extends BaseDomain {
    private String name;
    private int cpu_size;
    private int ram_size;
    private int root_disk;
    private List<Instance> instances = new ArrayList<>();

    public Flavor() {

    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public int getCpu_size() {
        return cpu_size;
    }

    public void setCpu_size(int cpu_size) {
        this.cpu_size = cpu_size;
    }

    @Column(nullable = false)
    public int getRam_size() {
        return ram_size;
    }

    public void setRam_size(int ram_size) {
        this.ram_size = ram_size;
    }

    @Column(nullable = false)
    public int getRoot_disk() {
        return root_disk;
    }

    public void setRoot_disk(int root_disk) {
        this.root_disk = root_disk;
    }

    @OneToMany(mappedBy = "flavor",fetch = FetchType.EAGER)
    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }
}
