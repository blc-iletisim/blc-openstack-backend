package com.blc.customerInterface.graphql.flavor.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseUpdateInput;

public class FlavorUpdateInput extends BaseUpdateInput {
    private String name;
    private int cpu_size;
    private int ram_size;
    private int root_disk;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpu_size() {
        return cpu_size;
    }

    public void setCpu_size(int cpu_size) {
        this.cpu_size = cpu_size;
    }

    public int getRam_size() {
        return ram_size;
    }

    public void setRam_size(int ram_size) {
        this.ram_size = ram_size;
    }

    public int getRoot_disk() {
        return root_disk;
    }

    public void setRoot_disk(int root_disk) {
        this.root_disk = root_disk;
    }
}
