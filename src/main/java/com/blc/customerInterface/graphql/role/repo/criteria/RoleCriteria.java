package com.blc.customerInterface.graphql.role.repo.criteria;


import com.blc.customerInterface.lib.dao.repo.criteria.BaseCriteria;

public class RoleCriteria extends BaseCriteria {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
