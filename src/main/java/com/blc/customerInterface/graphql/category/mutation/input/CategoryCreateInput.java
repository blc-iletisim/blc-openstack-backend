package com.blc.customerInterface.graphql.category.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseCreateInput;

import java.util.UUID;


public class CategoryCreateInput extends BaseCreateInput {
    private String name;
    private UUID instance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getInstance() {
        return instance;
    }

    public void setInstance(UUID instance) {
        this.instance = instance;
    }
}
