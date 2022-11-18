package com.blc.customerInterface.graphql.instance.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseUpdateInput;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstanceUpdateInput extends BaseUpdateInput {
    private String name;
    private UUID flavor;
    private List<UUID> categories= new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getFlavor() {
        return flavor;
    }

    public void setFlavor(UUID flavor) {
        this.flavor = flavor;
    }

    public List<UUID> getCategories() {
        return categories;
    }

    public void setCategories(List<UUID> categories) {
        this.categories = categories;
    }
}
