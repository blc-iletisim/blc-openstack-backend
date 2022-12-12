package com.blc.customerInterface.graphql.company.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseUpdateInput;

public class CompanyUpdateInput extends BaseUpdateInput {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
