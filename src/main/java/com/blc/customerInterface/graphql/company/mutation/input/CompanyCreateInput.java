package com.blc.customerInterface.graphql.company.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseCreateInput;

public class CompanyCreateInput extends BaseCreateInput {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
