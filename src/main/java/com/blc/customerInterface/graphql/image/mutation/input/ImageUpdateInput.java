package com.blc.customerInterface.graphql.image.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseUpdateInput;

public class ImageUpdateInput extends BaseUpdateInput {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
